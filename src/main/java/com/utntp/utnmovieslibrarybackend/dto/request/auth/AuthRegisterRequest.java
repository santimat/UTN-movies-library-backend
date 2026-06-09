package com.utntp.utnmovieslibrarybackend.dto.request.auth;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class AuthRegisterRequest {
    @NotBlank
    @Min(value = 3, message = "Name should be at least 2 characters")
    @Max(value = 50, message = "Name should be at most 50 characters")
    private String name;
    @NotBlank
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank
    @Min(value = 6, message = "Password should be at least 6 characters")
    private String password;

    public AuthRegisterRequest() {
    }

    public AuthRegisterRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
