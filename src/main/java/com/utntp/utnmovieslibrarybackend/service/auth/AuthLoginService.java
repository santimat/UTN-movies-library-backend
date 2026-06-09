package com.utntp.utnmovieslibrarybackend.service.auth;

import com.utntp.utnmovieslibrarybackend.dto.request.auth.AuthLoginRequest;
import com.utntp.utnmovieslibrarybackend.exception.ResourceNotFoundException;
import com.utntp.utnmovieslibrarybackend.exception.WrongPasswordException;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import com.utntp.utnmovieslibrarybackend.repository.user.JpaUserRepository;
import com.utntp.utnmovieslibrarybackend.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthLoginService {
    private final JpaUserRepository jpaUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthLoginService(JpaUserRepository jpaUserRepository, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.jpaUserRepository = jpaUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public String login(AuthLoginRequest loginRequest){
        User user = jpaUserRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + loginRequest.getEmail() + " not found"));

        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
            throw new WrongPasswordException();
        }

        return jwtService.generateToken(user.getEmail(),user.getRole(),user.getId(), user.getName());
    }
}
