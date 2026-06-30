package com.utntp.utnmovieslibrarybackend.service.user;

import com.utntp.utnmovieslibrarybackend.dto.request.user.UserRoleRequest;
import com.utntp.utnmovieslibrarybackend.enums.UserRoleEnum;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import com.utntp.utnmovieslibrarybackend.repository.user.JpaUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserUpdaterRoleService {
    private final JpaUserRepository jpaUserRepository;
    private final UserFinderService userFinderService;

    public UserUpdaterRoleService(JpaUserRepository jpaUserRepository, UserFinderService userFinderService) {
        this.jpaUserRepository = jpaUserRepository;
        this.userFinderService = userFinderService;
    }

    public User updateRole(Long userId, UserRoleRequest userRoleRequest) {
        User userToUpdate = userFinderService.findById(userId);
        userToUpdate.setRole(UserRoleEnum.valueOf(userRoleRequest.getRole()));
        return jpaUserRepository.save(userToUpdate);
    }
}
