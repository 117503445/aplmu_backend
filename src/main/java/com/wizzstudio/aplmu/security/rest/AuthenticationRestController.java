package com.wizzstudio.aplmu.security.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wizzstudio.aplmu.error.CustomException;
import com.wizzstudio.aplmu.security.model.Authority;
import com.wizzstudio.aplmu.security.model.User;
import com.wizzstudio.aplmu.security.repository.UserRepository;
import com.wizzstudio.aplmu.security.rest.dto.RegisterDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wizzstudio.aplmu.security.rest.dto.LoginDto;
import com.wizzstudio.aplmu.security.jwt.JWTFilter;
import com.wizzstudio.aplmu.security.jwt.TokenProvider;

import javax.validation.Valid;
import java.awt.desktop.SystemEventListener;
import java.util.HashSet;
import java.util.Set;

/**
 * Controller to authenticate users.
 */
@Api(tags = {"用户注册登录"})
@RestController
@RequestMapping("/api")
public class AuthenticationRestController {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthenticationRestController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userRepository = userRepository;
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ResponseEntity<JWTToken> login(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        boolean rememberMe = (loginDto.isRememberMe() == null) ? false : loginDto.isRememberMe();
        String jwt = tokenProvider.createToken(authentication, rememberMe);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterDto registerDto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                System.out.println(fieldError.getDefaultMessage());
                throw new CustomException(HttpStatus.BAD_REQUEST, fieldError.getDefaultMessage());
            }
        }

        if (userRepository.findOneByUsername(registerDto.getUsername()).isPresent()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "UserName has existed!");
        }

        User u = new User();

        Authority a = new Authority();
        a.setName("ROLE_USER");
        var h = new HashSet<Authority>();
        h.add(a);
        u.setAuthorities(h);

        u.setUsername(registerDto.getUsername());
        u.setFirstname(registerDto.getFirstname());
        u.setLastname(registerDto.getLastname());
        u.setEmail(registerDto.getEmail());
        //u.setId(4L);
        u.setActivated(true);

        u.setPassword(new
                BCryptPasswordEncoder().
                encode(registerDto.getPassword()));

        userRepository.save(u);
        //var u1 = userRepository.findById(3L).get();
        //userRepository.delete(u1);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
