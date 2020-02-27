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
import java.time.LocalDate;
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

            @RequestBody RegularUserDTO regularUserDTO,
            @RequestBody PetProfileDTO petProfileDTO,
            @RequestParam Date date,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime postTime)
            throws IllegalArgumentException {


        PetProfile pet = petProfileService.createPetProfile(petProfileDTO.getBreed(), petProfileDTO.getDescription(), petProfileDTO.getName(),
                petProfileDTO.getPetType(), Time.valueOf(postTime), date, regularUserDTO.getUser(),
                petProfileDTO.getReasonForPosting(), petProfileDTO.getIsAvailable());


        return convertToDto(pet);
    }


    @PutMapping(value = { "/changepetprofile", "/chnagepetprofile/" })
    public PetProfileDTO updatePetProfile(

            @RequestBody UserRole userRole,
            @RequestBody PetProfileDTO petProfileDTO,
            @RequestParam Date date,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime postTime)
            throws IllegalArgumentException {


        PetProfile pet = petProfileService.updatePetProfile(userRole.getUser(), petProfileDTO.getBreed(), petProfileDTO.getDescription(),
                petProfileDTO.getReasonForPosting(), petProfileDTO.getPetType(), petProfileDTO.getName(),petProfileDTO.getIsAvailable());


        return convertToDto(pet);
    }


    @GetMapping(value = { "/petprofiles", "/petprofiles/" })
    public List<PetProfileDTO> getAllPetProfiles() throws IllegalArgumentException {
        List<PetProfileDTO> petDtos = new ArrayList<>();
        for (PetProfile pet : petProfileService.getAllPetProfiles()) {
            petDtos.add(convertToDto(pet));
        }
        return petDtos;
    }


    @GetMapping(value = { "/petprofiles/{username}", "/petprofiles/{username}/" })
    public List<PetProfileDTO> getAllPetProfilesOfUser(@PathVariable("username") Account username) throws IllegalArgumentException {

        List<PetProfileDTO> petDtos = new ArrayList<>();
        for (PetProfile pet : petProfileService.getAllPetProfilesByUsername(username)) {
            petDtos.add(convertToDto(pet));
        }
        return petDtos;
    }


    private PetProfileDTO convertToDto(PetProfile pet) {
        if (pet == null) {
            throw new IllegalArgumentException("There is no such Pet!");
        }

        PetProfileDTO petDto = new PetProfileDTO(pet.getPoster(), pet.getImage(), pet.getApplication(), pet.getName(), pet.getPetType(),
                pet.getBreed(), pet.getDescription(), pet.getId(), pet.getReasonForPosting(), pet.getPostDate(), pet.getPostTime(), pet.isIsAvailable());

        return petDto;
    }




}
