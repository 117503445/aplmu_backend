package com.wizzstudio.aplmu.security.rest;

import com.wizzstudio.aplmu.error.CustomException;
import com.wizzstudio.aplmu.security.model.User;
import com.wizzstudio.aplmu.security.repository.UserRepository;
import com.wizzstudio.aplmu.security.rest.dto.RegisterDto;
import com.wizzstudio.aplmu.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@Api(tags = {"用户"})
public class UserRestController {

    private final UserRepository userRepository;

    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ApiOperation("根据 id 查看信息 ROLE_ADMIN")
    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public Optional<User> getOne(@PathVariable("id") long id) {
        return userRepository.findById(id);
    }

    @ApiOperation("查看自己的信息 ROLE_USER")
    @GetMapping("/me")
    @Secured("ROLE_USER")
    public Optional<User> getMe() {
        var oUserId = SecurityUtil.getCurrentUserId();
        assert oUserId.isPresent();

        return userRepository.findById(oUserId.get());
    }

    @ApiOperation("查看所有用户信息 ROLE_ADMIN")
    @GetMapping()
    @Secured("ROLE_ADMIN")
    public Page<User> pageQuery(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        return userRepository.findAll(PageRequest.of(pageNum - 1, pageSize));
    }

    @ApiOperation("删除某个非管理员用户 ROLE_ADMIN")
    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public void delete(@PathVariable("id") long id) throws CustomException {
        if (SecurityUtil.isAdmin()) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "无法删除管理员账户");
        }

        userRepository.deleteById(id);
    }

    @ApiOperation("更新某个用户信息 ROLE_USER")
    @PutMapping("/{id}")
    @Secured("ROLE_USER")
    public User update(@PathVariable("id") long id, @RequestBody RegisterDto registerDto) throws CustomException {
        var oCurrentUserId = SecurityUtil.getCurrentUserId();
        assert oCurrentUserId.isPresent();

        if (!SecurityUtil.isAdmin() && oCurrentUserId.get() != id) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "无法更新他人的账户信息,除非你有管理员权限");
        }

        var oUser = userRepository.findById(id);
        if (oUser.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "未找到要删除的用户");
        }
        var user = oUser.get();

        RegisterDto.toUser(registerDto, user);

        return userRepository.save(user);
    }
}