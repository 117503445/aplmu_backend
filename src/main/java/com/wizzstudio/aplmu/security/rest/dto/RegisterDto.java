package com.wizzstudio.aplmu.security.rest.dto;

import com.wizzstudio.aplmu.security.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterDto {
    @NotNull
    @Size(min = 4, max = 50, message = "the size of UserName should between [4,50]. Error valid RegisterLoginDto.")
    private String username;
    @NotNull
    @Size(min = 4, max = 50, message = "the size of password should between [4,50]. Error valid From RegisterLoginDto.")
    private String password;

    private String avatar;//头像 url

    @NotNull
    @Size(min = 1, max = 50, message = "the size of lastname should between [1,50]. Error valid From RegisterLoginDto.")
    private String lastname;
    @NotNull
    @Size(min = 1, max = 50, message = "the size of email should between [1,50]. Error valid From RegisterLoginDto.")
    private String email;

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


    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public static void toUser(RegisterDto dto, User user) {
        if (dto.getAvatar() != null) {
            user.setAvatar(dto.getAvatar());
        }

        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getPassword() != null) {
            user.setPassword(new
                    BCryptPasswordEncoder().
                    encode(dto.getPassword()));
        }

        if (dto.getLastname() != null) {
            user.setLastname(dto.getLastname());
        }

        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }
    }
}
