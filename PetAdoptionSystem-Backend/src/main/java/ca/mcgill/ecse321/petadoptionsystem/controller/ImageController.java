package ca.mcgill.ecse321.petadoptionsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ca.mcgill.ecse321.petadoptionsystem.dto.UploadImageDTO;
import ca.mcgill.ecse321.petadoptionsystem.model.Image;
import ca.mcgill.ecse321.petadoptionsystem.service.ImageService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping(value="/uploadImage/")
    public UploadImageDTO postMethodName(String description, @RequestParam("file") MultipartFile file) throws IllegalArgumentException {
        //TODO: process POST request
        Image img = imageService.createImage(file, description, null);

        String imageDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/downloadFile/")
        .path(img.getId() + "")
        .toUriString();

        
        UploadImageDTO response = new UploadImageDTO(img.getImageName(), imageDownloadUri,
        file.getContentType(), file.getSize());

        return response;

    }

    @GetMapping("/getImage/{imageName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String imageName) {
        // Load file from database
        Image dbFile = imageService.getImage(imageName);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getImageType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getImageName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }
    
   

}