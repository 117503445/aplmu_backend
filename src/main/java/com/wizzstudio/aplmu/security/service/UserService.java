package com.wizzstudio.aplmu.security.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wizzstudio.aplmu.security.SecurityUtils;
import com.wizzstudio.aplmu.security.model.User;
import com.wizzstudio.aplmu.security.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional
public class UserService {

   private final UserRepository userRepository;

   public UserService(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @Transactional(readOnly = true)
   public Optional<User> getUserWithAuthorities() {
      return SecurityUtils.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
   }

}
