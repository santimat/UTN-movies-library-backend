package com.utntp.utnmovieslibrarybackend.service.user;

import com.utntp.utnmovieslibrarybackend.dto.request.user.UserRequest;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import com.utntp.utnmovieslibrarybackend.repository.user.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserUpdaterService {
    private final JpaUserRepository jpaUserRepository;
    private final UserFinderService userFinderService;


    public UserUpdaterService(JpaUserRepository jpaUserRepository,  UserFinderService userFinderService) {
        this.jpaUserRepository = jpaUserRepository;
        this.userFinderService = userFinderService;
    }

    public User update(Long id, UserRequest userRequest){
        User toUpdate = userFinderService.find(id);
        toUpdate.setEmail(userRequest.getEmail());
        toUpdate.setName(userRequest.getName());
        toUpdate.setPassword(userRequest.getPassword());
        return jpaUserRepository.save(toUpdate);
    }
}
