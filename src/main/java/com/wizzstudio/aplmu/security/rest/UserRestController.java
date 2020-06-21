package com.wizzstudio.aplmu.security.rest;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wizzstudio.aplmu.security.model.User;
import com.wizzstudio.aplmu.security.service.UserService;

@RestController
@RequestMapping("/api")
@Api(tags = {"单个用户信息"})
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @Secured("ROLE_USER")
    @GetMapping("/user")
    public ResponseEntity<User> getActualUser() {
        var auth = userService.getUserWithAuthorities();
        return auth.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>((User) null, HttpStatus.NOT_FOUND));
    }
}
