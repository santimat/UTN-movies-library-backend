package com.utntp.utnmovieslibrarybackend.service.user;

import com.utntp.utnmovieslibrarybackend.repository.user.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDeleterService {
    private final JpaUserRepository jpaUserRepository;
    private final UserFinderService finder;


    public UserDeleterService(JpaUserRepository jpaUserRepository, UserFinderService finder) {
        this.jpaUserRepository = jpaUserRepository;
        this.finder = finder;
    }

    public void deleter(Long id){
        finder.find(id);
        jpaUserRepository.deleteById(id);
    }
}
