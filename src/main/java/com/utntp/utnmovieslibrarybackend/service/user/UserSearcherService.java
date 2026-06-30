package com.utntp.utnmovieslibrarybackend.service.user;

import com.utntp.utnmovieslibrarybackend.enums.UserRoleEnum;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import com.utntp.utnmovieslibrarybackend.repository.user.JpaUserRepository;
import com.utntp.utnmovieslibrarybackend.utils.BlankToNullUtility;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserSearcherService {
    private final JpaUserRepository jpaUserRepository;
    private final BlankToNullUtility blankToNullUtility;

    public UserSearcherService(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
        this.blankToNullUtility = new BlankToNullUtility();
    }

    public Page<User> findAll(@Nullable String searchText, @Nullable String role, Pageable pageable) {
        UserRoleEnum roleToSend = role != null ? UserRoleEnum.valueOf(role) : null;
        return jpaUserRepository.findAllWithFilters(blankToNullUtility.blankToNull(searchText), roleToSend, pageable);
    }

}
