package com.example.teduniversite.services;

import com.example.teduniversite.entities.Image;
import com.example.teduniversite.repository.FileSystemRepository;
import com.example.teduniversite.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

public class FileLocationServices implements IFileLocationService{

    @Autowired
    FileSystemRepository fileSystemRepository;
    @Autowired
    ImageRepository imageDataRepository;

    public Image save(MultipartFile file) throws Exception {
        String location = fileSystemRepository.save(file);
        return imageDataRepository.save(new Image(file.getOriginalFilename(), location));
    }
    public FileSystemResource find(Long imageId) {
        Image image = imageDataRepository.findById(imageId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return fileSystemRepository.findInFileSystem(image.getLocation());
    }

}
