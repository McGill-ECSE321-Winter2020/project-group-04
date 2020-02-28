package ca.mcgill.ecse321.petadoptionsystem.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import ca.mcgill.ecse321.petadoptionsystem.dao.ImageRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.PetProfileRepository;
import ca.mcgill.ecse321.petadoptionsystem.exception.FileStorageException;
import ca.mcgill.ecse321.petadoptionsystem.model.*;

@Service
public class ImageService {
    @Autowired
    private PetProfileRepository petProfileRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Transactional
    public void createImage(MultipartFile file, String Description){
        String imageName = StringUtils.cleanPath(file.getOriginalFilename());
        try{
            if(imageName==null || imageName.trim().length()==0){
                throw new FileStorageException("Imagename contains invalid path sequence " + imageName);
            }
            //PetProfile petProfile = petProfileRepository.
            Image image = new Image();
            image.setData(file.getBytes());
            image.setImageName(imageName);
            image.setImageType(file.getContentType());
            
            
        } catch(IOException ex){
            String error = "Could not store file " + imageName + ". Please try again!";
            throw new FileStorageException(error, ex);
        }

    }

}