package com.javaKava.SpringProject.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;


import static java.nio.file.StandardOpenOption.*;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Value("D:\\SpringProject\\images")
    private String bucket;

    @SneakyThrows
    public void upload(String image, InputStream inputStream) {
        Path fullPath = Path.of(bucket, image);

        try (inputStream) {
            Files.createDirectories(fullPath.getParent());
            Files.write(fullPath, inputStream.readAllBytes(), CREATE, TRUNCATE_EXISTING);
        }
    }

    @SneakyThrows
    public void upload(MultipartFile image) {
        if (!image.isEmpty()) {
            upload(image.getOriginalFilename(), image.getInputStream());

        }
    }

    @SneakyThrows
    public Optional<byte[]> getImage(String image) {
        Path fullPath = Path.of(bucket, image);

        return Files.exists(fullPath)
                ? Optional.of(Files.readAllBytes(fullPath))
                : Optional.empty();
    }
}
