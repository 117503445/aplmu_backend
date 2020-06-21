package com.wizzstudio.aplmu.security.rest.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterDto {
    @NotNull
    @Size(min = 4, max = 50)
    private String username;

    @NotNull
    @Size(min = 4, max = 100)
    private String password;
    @NotNull
    @Size(min = 4, max = 50)
    private String firstname;
    @NotNull
    @Size(min = 4, max = 50)
    private String lastname;
    @NotNull
    @Size(min = 4, max = 50)
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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
}
