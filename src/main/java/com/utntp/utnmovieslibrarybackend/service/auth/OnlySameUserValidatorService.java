package com.utntp.utnmovieslibrarybackend.service.auth;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OnlySameUserValidatorService {
    public OnlySameUserValidatorService() {
    }

    public boolean isSameUser(Long idFromJWT, Long resourceUserId) {
        return Objects.equals(idFromJWT, resourceUserId);
    }
}
