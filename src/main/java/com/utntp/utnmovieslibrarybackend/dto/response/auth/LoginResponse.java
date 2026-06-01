package com.utntp.utnmovieslibrarybackend.dto.response.auth;

public class LoginResponse {
    public String token;

    public LoginResponse(){}
    public LoginResponse(String token){this.token = token;}

    public String getToken(){return token;}
    public void setToken(String token){this.token = token;}
}
