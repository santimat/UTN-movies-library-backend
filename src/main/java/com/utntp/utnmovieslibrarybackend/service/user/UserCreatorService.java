package com.utntp.utnmovieslibrarybackend.service.user;

import com.utntp.utnmovieslibrarybackend.dto.request.user.UserRequest;
import com.utntp.utnmovieslibrarybackend.mapper.user.UserMapper;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import org.springframework.stereotype.Service;
import com.utntp.utnmovieslibrarybackend.repository.user.JpaUserRepository;

@Service
public class UserCreatorService {
    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    public UserCreatorService(JpaUserRepository jpaUserRepository){
        this.jpaUserRepository = jpaUserRepository;
        this.userMapper = new UserMapper();
    }

    public User create(UserRequest userRequest){
        User user = userMapper.toEntity(userRequest);
        return jpaUserRepository.save(user);
    }
}
