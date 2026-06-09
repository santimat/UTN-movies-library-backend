package com.utntp.utnmovieslibrarybackend.service.auth;

import com.utntp.utnmovieslibrarybackend.dto.request.auth.AuthRegisterRequest;
import com.utntp.utnmovieslibrarybackend.mapper.user.UserMapper;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.utntp.utnmovieslibrarybackend.repository.user.JpaUserRepository;

@Service
public class AuthRegisterService {
    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthRegisterService(JpaUserRepository jpaUserRepository, PasswordEncoder passwordEncoder){
        this.jpaUserRepository = jpaUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = new UserMapper();
    }

    public User register(AuthRegisterRequest request){
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return jpaUserRepository.save(user);
    }
}
