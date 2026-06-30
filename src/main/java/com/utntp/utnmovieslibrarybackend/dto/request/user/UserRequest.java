package com.utntp.utnmovieslibrarybackend.dto.request.user;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class UserRequest {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String name;

    @Nullable
    private MultipartFile pfpFile;
    @Nullable
    private String pfpUrl;

    public UserRequest() {
    }

    public UserRequest(String email, String name, @Nullable MultipartFile pfpFile,
                       @Nullable String pfpUrl) {
        this.email = email;
        this.name = name;
        this.pfpFile = pfpFile;
        this.pfpUrl = pfpUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public MultipartFile getPfpFile() {
        return pfpFile;
    }

    public void setPfpFile(@Nullable MultipartFile pfpFile) {
        this.pfpFile = pfpFile;
    }

    @Nullable
    public String getPfpUrl() {
        return pfpUrl;
    }

    public void setPfpUrl(@Nullable String pfpUrl) {
        this.pfpUrl = pfpUrl;
    }
}
