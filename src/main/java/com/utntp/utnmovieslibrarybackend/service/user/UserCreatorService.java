package com.utntp.utnmovieslibrarybackend.service.user;

import com.utntp.utnmovieslibrarybackend.dto.request.UserRequest;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import org.springframework.stereotype.Service;
import com.utntp.utnmovieslibrarybackend.repository.user.JpaUserRepository;

@Service
public class UserCreatorService {
    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    public UserCreatorService(JpaUserRepository jpaUserRepository, UserMapper userMapper){
        this.jpaUserRepository = jpaUserRepository;
        this.userMapper = userMapper;
    }

    public User create(UserRequest request){
        User user = userMapper.toEntity(request);
        return jpaUserRepository.save(user);
    }
}
