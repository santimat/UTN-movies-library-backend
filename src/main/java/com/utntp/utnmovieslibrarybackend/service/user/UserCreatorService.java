package com.utntp.utnmovieslibrarybackend.service.user;

import com.utntp.utnmovieslibrarybackend.model.user.User;
import org.springframework.stereotype.Service;
import com.utntp.utnmovieslibrarybackend.repository.user.JpaUserRepository;

@Service
public class UserCreatorService {
    private final JpaUserRepository jpaUserRepository;

    public UserCreatorService(JpaUserRepository jpaUserRepository){
        this.jpaUserRepository = jpaUserRepository;
    }

    public User create(UserRequest request){
        return jpaUserRepository.save(request);
    }
}
