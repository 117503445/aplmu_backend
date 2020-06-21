package com.wizzstudio.aplmu.security.rest.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO for storing a user's credentials.
 */
public class LoginDto {

   @NotNull
   @Size(min = 4, max = 50, message = "the size of UserName should between [4,50]. Error valid From LoginDto.")
   private String username;

   @NotNull
   @Size(min = 4, max = 50, message = "the size of password should between [4,50]. Error valid From LoginDto.")
   private String password;

   private Boolean rememberMe;

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Boolean isRememberMe() {
      return rememberMe;
   }

   public void setRememberMe(Boolean rememberMe) {
      this.rememberMe = rememberMe;
   }

   @Override
   public String toString() {
      return "LoginVM{" +
         "username='" + username + '\'' +
         ", rememberMe=" + rememberMe +
         '}';
   }
}
