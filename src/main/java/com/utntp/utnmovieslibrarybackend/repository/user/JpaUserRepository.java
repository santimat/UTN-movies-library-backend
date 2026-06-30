package com.utntp.utnmovieslibrarybackend.repository.user;

import com.utntp.utnmovieslibrarybackend.enums.UserRoleEnum;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByEmail(String userEmail);

    boolean existsById(@Nonnull Long id);

    boolean existsByEmail(@Nonnull String email);

    @Query(
            """
                    SELECT u FROM User u
                    WHERE (:role IS NULL OR u.role = :role)
                    AND (:searchText IS NULL
                        OR LOWER(u.name) LIKE LOWER(CONCAT('%', CAST(:searchText AS string), '%'))
                        OR LOWER(u.email) LIKE LOWER(CONCAT('%', CAST(:searchText AS string), '%')))
                    """
    )
    Page<User> findAllWithFilters(@Param(value = "searchText") String searchText,
                                  @Param(value = "role") UserRoleEnum role, Pageable pageable);
}
