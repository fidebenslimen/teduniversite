package com.example.teduniversite.services;

import com.example.teduniversite.entities.Image;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public interface IFileLocationService {
    public Image save(MultipartFile file) throws Exception ;
    public FileSystemResource find(Long imageId) ;
}
