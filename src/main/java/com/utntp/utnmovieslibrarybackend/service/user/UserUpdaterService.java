package com.utntp.utnmovieslibrarybackend.service.user;

import com.utntp.utnmovieslibrarybackend.dto.request.auth.AuthRegisterRequest;
import com.utntp.utnmovieslibrarybackend.model.user.User;
import com.utntp.utnmovieslibrarybackend.repository.user.JpaUserRepository;
import com.utntp.utnmovieslibrarybackend.service.file.FileManagerService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public User updateById(Long id, AuthRegisterRequest userRequest, MultipartFile file){
        User toUpdate = userFinderService.findById(id);
        toUpdate.setEmail(userRequest.getEmail());
        toUpdate.setName(userRequest.getName());
        toUpdate.setPassword(userRequest.getPassword());

        if(!(file == null) && !file.isEmpty()){
            String filePath = fileManagerService.createFile(file);
            toUpdate.setPfpUrl(filePath);
        }
        return jpaUserRepository.save(toUpdate);
    }
}
