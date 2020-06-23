package com.wizzstudio.aplmu.util;

import com.wizzstudio.aplmu.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
