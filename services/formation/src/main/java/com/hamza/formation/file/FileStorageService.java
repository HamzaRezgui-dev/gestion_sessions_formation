package com.hamza.formation.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {
    private final String uploadDir = "uploads";

    public FileStorageService() {
        // Ensure the directory exists
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public String saveFile(MultipartFile file) {
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, filename);
        try {
            Files.write(filePath, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + filename, e);
        }
        return filename;
    }

    public void deleteFile(String filename) {
        Path filePath = Paths.get(uploadDir, filename);
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file " + filename, e);
        }
    }
}
