package com.wizzstudio.aplmu.util;

import com.wizzstudio.aplmu.security.model.User;
import com.wizzstudio.aplmu.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityUtil {

    private static UserRepository userRepository;

    @Autowired
    public SecurityUtil(UserRepository userRepository) {
        SecurityUtil.userRepository = userRepository;
    }

    public static Optional<Long> FromUserNameGetId(String userName) {
        var oUser = userRepository.findOneByUsername(userName);
        if (oUser.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(oUser.get().getId());
    }

    public static String FromIdGetUserName(long id) {
        var oUser = userRepository.findById(id);
        if (oUser.isEmpty()) {
            return "已注销";
        }
        return oUser.get().getUsername();
    }

    public static Authentication getCurrentAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Optional<String> getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return Optional.empty();
        }

        return Optional.of(authentication.getName());
    }

    public static Optional<Long> getCurrentUserId() {

        var oName = getCurrentUserName();
        if (oName.isEmpty()) {
            return Optional.empty();
        }
        String name = oName.get();
        return FromUserNameGetId(name);
    }

    public static Optional<User> getCurrentUser() {
        var oUserId = getCurrentUserId();
        if (oUserId.isEmpty()) {
            return Optional.empty();
        }
        return userRepository.findById(oUserId.get());
    }

    //检查当前用户是否有 roleName 权限
    public static boolean hasRoleName(String roleName) {

        var oUser = getCurrentUser();
        if (oUser.isEmpty()) {
            return false;
        }
        var user = oUser.get();

        for (var authority : user.getAuthorities()) {
            if (authority.getName().equals(roleName)) {
                return true;
            }
        }
        return false;
    }

    //检查当前用户是否有 Admin 权限
    public static boolean isAdmin() {
        return hasRoleName("ROLE_ADMIN");
    }
}
