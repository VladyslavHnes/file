package com.eco.monitor.file.resource;

import com.eco.monitor.file.dto.FileResponse;
import com.eco.monitor.file.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * File Resource.
 *
 * @author Vladyslav Hnes
 */
@Validated
@RestController
@RequestMapping(value = "/files")
public class FileResource {

    @Autowired
    private S3Service s3Service;

    @PostMapping
    public ResponseEntity uploadFile(@RequestPart(value = "file") MultipartFile multipartFile) throws Exception {

        if (multipartFile.isEmpty()) {
            throw new Exception();
        }

        UUID generatedName = UUID.randomUUID();

        File file = File.createTempFile("temp_file" + generatedName.toString(), multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        int lastDotIndex = multipartFile.getOriginalFilename().lastIndexOf('.');
        String name = multipartFile.getOriginalFilename().substring(0, lastDotIndex ) + "-" + generatedName
                + multipartFile.getOriginalFilename().substring(lastDotIndex);
        String url = s3Service.upload(name, file);
        System.out.println("UPLOADED: " + url);
        return ResponseEntity.ok().header("url", url).build();
    }
//    https://monitor-app-images.s3.eu-central-1.amazonaws.com/monitor-app-images/2a.png7b9a4959-03b5-4fbd-b1d4-20f771f1bdba
}
