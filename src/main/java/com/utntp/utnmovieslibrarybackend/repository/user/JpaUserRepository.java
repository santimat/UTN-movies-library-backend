package com.utntp.utnmovieslibrarybackend.repository.user;

import com.utntp.utnmovieslibrarybackend.model.user.User;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByEmail( String userEmail);

    boolean existsById(@Nonnull Long id);

    boolean existsByEmail(@Nonnull String email);
}
