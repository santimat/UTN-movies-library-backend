package com.utntp.utnmovieslibrarybackend.service.user;

import com.utntp.utnmovieslibrarybackend.dto.request.auth.AuthRegisterRequest;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import com.utntp.utnmovieslibrarybackend.repository.user.JpaUserRepository;
import com.utntp.utnmovieslibrarybackend.service.file.FileManagerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserUpdaterService {
    private final JpaUserRepository jpaUserRepository;
    private final UserFinderService userFinderService;
    private final FileManagerService fileManagerService;


    public UserUpdaterService(JpaUserRepository jpaUserRepository,  UserFinderService userFinderService, FileManagerService fileManagerService) {
        this.jpaUserRepository = jpaUserRepository;
        this.userFinderService = userFinderService;
        this.fileManagerService = fileManagerService;
    }

    @Transactional
    public User updateById(Long id, AuthRegisterRequest userRequest){
        User toUpdate = userFinderService.findById(id);

        boolean hasPfpFile = userRequest.getPfpFile() != null && !userRequest.getPfpFile().isEmpty();

        toUpdate.setEmail(userRequest.getEmail());
        toUpdate.setName(userRequest.getName());
        toUpdate.setPassword(userRequest.getPassword());

        if(hasPfpFile){
            String urlToSave = fileManagerService.createFile(userRequest.getPfpFile());
            toUpdate.setPfpUrl(urlToSave);
        }
        return jpaUserRepository.save(toUpdate);
    }
}
