package com.utntp.utnmovieslibrarybackend.repository.user;

import com.utntp.utnmovieslibrarybackend.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Query("SELECT users from users WHERE email = :userEmail")
    User findByEmail(@Param(value = "userEmail") String userEmail);
}
