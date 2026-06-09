package com.utntp.utnmovieslibrarybackend.security;
import com.utntp.utnmovieslibrarybackend.enums.UserRoleEnum;

import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private final Long id;
    private final String email;
    private final String username;
    private final UserRoleEnum role;

    public UserPrincipal(Long id, String email, String username, UserRoleEnum role) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.role = role;
    }

    public Long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public UserRoleEnum getRole() {
        return role;
    }

    @Override
    @NonNull
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public @Nullable String getPassword() {
        return null;
    }

    @Override
    @NonNull
    public String getUsername(){
        return this.username;
    }

}
