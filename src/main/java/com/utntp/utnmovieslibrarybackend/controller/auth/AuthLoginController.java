package com.utntp.utnmovieslibrarybackend.controller.auth;

import com.utntp.utnmovieslibrarybackend.dto.request.auth.AuthLoginRequest;
import com.utntp.utnmovieslibrarybackend.dto.response.user.UserResponse;
import com.utntp.utnmovieslibrarybackend.service.auth.AuthLoginService;
import com.utntp.utnmovieslibrarybackend.service.jwt.JwtService;
import jakarta.servlet.http.Cookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/login")
public class AuthLoginController {
    private final AuthLoginService authLoginService;
    private final JwtService jwtService;

    public AuthLoginController(AuthLoginService authLoginService, JwtService jwtService) {
        this.authLoginService = authLoginService;
        this.jwtService = jwtService;
    }

    @PostMapping()
    public ResponseEntity<UserResponse> login(@RequestBody AuthLoginRequest loginRequest) {
        try{
            String token = authLoginService.login(loginRequest);
            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(86400);
            UserResponse userResponse = jwtService.getUserData(token);
            return ResponseEntity.ok(userResponse);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
