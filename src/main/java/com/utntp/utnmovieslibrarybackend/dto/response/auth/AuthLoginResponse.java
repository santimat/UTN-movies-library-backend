package com.utntp.utnmovieslibrarybackend.dto.response.auth;

public class AuthLoginResponse {
    public String token;

    public AuthLoginResponse(){}
    public AuthLoginResponse(String token){this.token = token;}

    public String getToken(){return token;}
    public void setToken(String token){this.token = token;}
}
