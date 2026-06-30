package com.utntp.utnmovieslibrarybackend.service.file;

import com.utntp.utnmovieslibrarybackend.exception.FileManagerException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileManagerService {

    private final Path uploadsDir;

    public FileManagerService(@Value("${app.uploads.dir}") String uploadsDir) {
        this.uploadsDir = Path.of(uploadsDir);

        try {
            Files.createDirectories(this.uploadsDir);
        } catch (IOException e) {
            throw new FileManagerException("Failed to create upload directory" + e.getMessage());
        }
    }

    public String createFile(MultipartFile file, String subDir) {
        if (file == null || file.isEmpty())
            throw new FileManagerException("File is empty");

        try {
            String originalFileName = file.getOriginalFilename();
            String extension = "";
            if (originalFileName != null && originalFileName.contains("."))
                extension = originalFileName.substring(originalFileName.lastIndexOf("."));

            String nameToSaveFile = UUID.randomUUID() + extension;
            Path pathToSaveFile = this.uploadsDir.resolve(subDir + nameToSaveFile);

            Files.copy(file.getInputStream(), pathToSaveFile, StandardCopyOption.REPLACE_EXISTING);

            return pathToSaveFile.toString().replace('\\', '/');
        } catch (IOException e) {
            throw new FileManagerException("Failed to save file" + e.getMessage());
        }
    }

    public void deleteFile(String path) {
        if (path == null || path.isEmpty())
            throw new FileManagerException("File path is empty");
        try {
            Path pathToDelete = Path.of(path);
            Files.deleteIfExists(pathToDelete);
        } catch (IOException e) {
            throw new FileManagerException("Failed to delete file" + e.getMessage());
        }
    }
}
