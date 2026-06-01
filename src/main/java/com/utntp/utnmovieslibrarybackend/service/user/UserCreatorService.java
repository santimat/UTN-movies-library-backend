package com.utntp.utnmovieslibrarybackend.service.user;

import com.utntp.utnmovieslibrarybackend.dto.request.user.UserRequest;
import com.utntp.utnmovieslibrarybackend.mapper.user.UserMapper;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.utntp.utnmovieslibrarybackend.repository.user.JpaUserRepository;

@Service
public class UserCreatorService {
    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserCreatorService(JpaUserRepository jpaUserRepository, UserMapper userMapper, PasswordEncoder passwordEncoder){
        this.jpaUserRepository = jpaUserRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(UserRequest request){
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return jpaUserRepository.save(user);
    }
}
