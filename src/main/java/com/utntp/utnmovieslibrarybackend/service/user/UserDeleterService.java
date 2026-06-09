package com.utntp.utnmovieslibrarybackend.service.user;

import com.utntp.utnmovieslibrarybackend.repository.user.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDeleterService {
    private final JpaUserRepository jpaUserRepository;
    private final UserFinderService userFinderService;


    public UserDeleterService(JpaUserRepository jpaUserRepository, UserFinderService userFinderService) {
        this.jpaUserRepository = jpaUserRepository;
        this.userFinderService = userFinderService;
    }

    public void deleteById(Long id){
        userFinderService.findById(id);
        jpaUserRepository.deleteById(id);
    }
}
