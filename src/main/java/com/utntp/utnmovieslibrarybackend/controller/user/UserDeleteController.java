package com.utntp.utnmovieslibrarybackend.controller.user;

import com.utntp.utnmovieslibrarybackend.service.user.UserDeleterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserDeleteController {
    private final UserDeleterService userDeleterService;


    public UserDeleteController(UserDeleterService userDeleterService) {
        this.userDeleterService = userDeleterService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        userDeleterService.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
