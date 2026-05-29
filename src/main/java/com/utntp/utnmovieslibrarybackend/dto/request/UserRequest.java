package com.utntp.utnmovieslibrarybackend.dto.request;


import jakarta.validation.constraints.NotBlank;

public class UserRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
