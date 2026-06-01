package com.utntp.utnmovieslibrarybackend.service.auth;

import com.utntp.utnmovieslibrarybackend.dto.request.auth.LoginRequest;
import com.utntp.utnmovieslibrarybackend.dto.response.auth.LoginResponse;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import com.utntp.utnmovieslibrarybackend.repository.user.JpaUserRepository;
import com.utntp.utnmovieslibrarybackend.service.jwt.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final JpaUserRepository jpaUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginService(JpaUserRepository jpaUserRepository, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.jpaUserRepository = jpaUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public LoginResponse login(LoginRequest loginRequest){
        User user = jpaUserRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundByEmail(loginRequest.getEmail()));

        if(!passwordEncoder.matches(user.getPassword(), loginRequest.getPassword())){
            throw new WrongPassowrdException();
        }

        String token = jwtService.generateToken(user.getEmail());
        return new LoginResponse(token);
    }
}
