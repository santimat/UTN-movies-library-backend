package com.utntp.utnmovieslibrarybackend.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class AuthLoginRequest {
    @Size(min = 6)
    @Email(message = "Email should be valid")
    private String email;
    @Size(min = 6, max = 100, message = "Password should be between 6 and 100 characters")
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
