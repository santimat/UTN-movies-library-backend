package com.utntp.utnmovieslibrarybackend.service.user;

import com.utntp.utnmovieslibrarybackend.repository.user.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserExistingValidatorService {
    private final JpaUserRepository jpaUserRepository;

    public UserExistingValidatorService(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }
    public boolean existsByEmail(String email){
        return jpaUserRepository.findByEmail(email).isPresent();
    }
}
