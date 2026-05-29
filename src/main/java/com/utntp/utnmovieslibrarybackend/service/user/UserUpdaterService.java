package com.utntp.utnmovieslibrarybackend.service.user;

import com.utntp.utnmovieslibrarybackend.dto.request.user.UserRequest;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import com.utntp.utnmovieslibrarybackend.repository.user.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserUpdaterService {
    private final JpaUserRepository jpaUserRepository;
    private final UserFinderService finder;


    public UserUpdaterService(JpaUserRepository jpaUserRepository,  UserFinderService finder) {
        this.jpaUserRepository = jpaUserRepository;
        this.finder = finder;
    }

    public User update(Long id, UserRequest request){
        User toUpdate = finder.find(id);
        toUpdate.setEmail(request.getEmail());
        toUpdate.setName(request.getName());
        toUpdate.setPassword(request.getPassword());
        return jpaUserRepository.save(toUpdate);
    }
}
