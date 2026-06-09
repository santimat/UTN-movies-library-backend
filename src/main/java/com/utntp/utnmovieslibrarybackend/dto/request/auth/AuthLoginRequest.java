package com.utntp.utnmovieslibrarybackend.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

public class AuthLoginRequest {
    @Email(message = "Email should be valid")
    private String email;
    @Min(value = 6, message = "Password should be at least 6 characters")
    private String password;
    private boolean remember;

    public AuthLoginRequest() {
    }

    public AuthLoginRequest(String email, String password, boolean remember) {
        this.email = email;
        this.password = password;
        this.remember = remember;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }
}
