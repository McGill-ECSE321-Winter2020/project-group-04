package ca.mcgill.ecse321.petadoptionsystem.controller;

import ca.mcgill.ecse321.petadoptionsystem.dto.PetProfileDTO;
import ca.mcgill.ecse321.petadoptionsystem.dto.RegularUserDTO;
import ca.mcgill.ecse321.petadoptionsystem.model.*;
import ca.mcgill.ecse321.petadoptionsystem.service.PetProfileService;
import ca.mcgill.ecse321.petadoptionsystem.service.RegularUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController

public class PetProfileRestController {

    @Autowired
    private PetProfileService petProfileService;

    @Autowired
    private RegularUserService regularUserService;

//Need to change the Classes to be DTO Classes and not the normal ones

@PostMapping(value = { "/petprofile", "/petprofile/" })
    public PetProfileDTO createPetProfile(

        @RequestParam (name = "username") Account username,
        @RequestParam Date postDate,
        @RequestParam (name = "Breed") String breed,
        @RequestParam (name = "Description") String description,
        @RequestParam (name = "Pet Type") PetType petType,
        @RequestParam (name = "Reason") String reason,
        @RequestParam (name = "Name of Pet") String name,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime postTime)
    throws IllegalArgumentException {

    PetProfile pet = petProfileService.createPetProfile(breed, description, name, petType, Time.valueOf(postTime), postDate, username, reason);


    return convertToDto(pet);
}


//    @GetMapping(value = { "/applications", "/applications/" })
//    public List<PetProfileDTO> getAllPetProfiles() throws IllegalArgumentException {
//        List<PetProfileDTO> appDtos = new ArrayList<>();
//        for (PetProfile app : petProfileService.getAllPetProfiles()) {
//            appDtos.add(convertToDto(app));
//        }
//        return appDtos;
//    }


    private PetProfileDTO convertToDto(PetProfile pet) {
        if (pet == null) {
            throw new IllegalArgumentException("There is no such Pet!");
        }

        PetProfileDTO petDto = new PetProfileDTO(pet.getPoster(), pet.getImage(), pet.getApplication(), pet.getName(), pet.getPetType(),
                pet.getBreed(), pet.getDescription(), pet.getId(), pet.getReasonForPosting(), pet.getPostDate(), pet.getPostTime());

        return petDto;
    }




}
