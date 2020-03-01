package ca.mcgill.ecse321.petadoptionsystem.controller;

import ca.mcgill.ecse321.petadoptionsystem.dto.PetProfileDTO;
import ca.mcgill.ecse321.petadoptionsystem.dto.UserRoleDTO;
import ca.mcgill.ecse321.petadoptionsystem.model.*;
import ca.mcgill.ecse321.petadoptionsystem.service.PetProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController

public class PetProfileRestController {

    @Autowired
    private PetProfileService petProfileService;


//TODO Need to change the Classes to be DTO Classes and not the normal ones



    /**
     *
     * @param petProfileDTO union of both classes RegularUser and PetProfile
     * @param date date of posting
     * @param postTime time of posting
     * @return the newly created profile
     * @throws IllegalArgumentException error
     */
    @PostMapping(value = { "/petprofile/{username}", "/petprofile/{username}/" })
    public PetProfileDTO createPetProfile(
            @PathVariable("username") String name,
            @RequestBody PetProfileDTO petProfileDTO,
            @RequestParam Date date,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime postTime)
            throws IllegalArgumentException {


        PetProfile pet = petProfileService.createPetProfile(petProfileDTO.getBreed(), petProfileDTO.getDescription(), petProfileDTO.getName(),
                petProfileDTO.getPetType(), Time.valueOf(postTime), date, name,
                petProfileDTO.getReasonForPosting(), petProfileDTO.getIsAvailable(), petProfileDTO.getImages());


        return convertToDto(pet);

    }


    /**
     *
     * @param petProfileDTO union of both classes RegularUser and PetProfile
     * @return returns the updated value
     * @throws IllegalArgumentException error message
     */
    @PutMapping( value = {"/updatePetProfile/{username}/{petname}", "/updatePetProfile/{petname}/"})
    public PetProfileDTO updatePetProfile(
            @RequestBody PetProfileDTO petProfileDTO,
            @PathVariable("username") String name,
            @PathVariable("petname") String petname)
            throws IllegalArgumentException{


        PetProfile pet = petProfileService.updatePetProfile(name, petProfileDTO.getBreed(), petProfileDTO.getDescription(),
                petProfileDTO.getReasonForPosting(), petProfileDTO.getPetType(), petname, petProfileDTO.getIsAvailable(), petProfileDTO.getImages());


       return convertToDto(pet);

    }

    /**
     *
     * @return all pet profiles
     * @throws IllegalArgumentException error
     */
    @GetMapping(value = { "/petprofiles", "/petprofiles/" })
    public List<PetProfileDTO> getAllPetProfiles() throws IllegalArgumentException {
        List<PetProfileDTO> petDtos = new ArrayList<>();
        for (PetProfile pet : petProfileService.getAllPetProfiles()) {
            petDtos.add(convertToDto(pet));
        }
        return petDtos;
    }

    // TODO should be AccountDTO, not Account



    /**
     *
     * @param username username that we are looking for
     * @return th list of petprofiles of a singular user
     * @throws IllegalArgumentException errors
     */
    @GetMapping(value = { "/petprofiles/{username}", "/petprofiles/{username}/" })
    public List<PetProfileDTO> getAllPetProfilesOfUser(@PathVariable("username") String username) throws IllegalArgumentException {

        List<PetProfileDTO> petDtos = new ArrayList<>();
        for (PetProfile pet : petProfileService.getAllPetProfilesByUsername(username)) {
            petDtos.add(convertToDto(pet));
        }
        return petDtos;
    }

    @GetMapping(value = { "/petprofiles/{available}", "/petprofiles/{available}/" })
    public List<PetProfileDTO> getAllPetProfilesIsAvailable(@PathVariable("available") boolean available)
            throws IllegalArgumentException {

        List<PetProfileDTO> petDtos = new ArrayList<>();
        for (PetProfile pet : petProfileService.getAllPetProfilesByIsAvailable(available)) {
            petDtos.add(convertToDto(pet));
        }
        return petDtos;
    }

    /**
     *
     * @param breed to be looked for
     * @return a list with the needed pets
     * @throws IllegalArgumentException error
     */
    @GetMapping(value = { "/petprofiles/{breed}", "/petprofiles/{breed}/" })
    public List<PetProfileDTO> getAllPetProfilesOfBreed(@PathVariable("breed") String breed) throws IllegalArgumentException {

        List<PetProfileDTO> petDtos = new ArrayList<>();
        for (PetProfile pet : petProfileService.getAllPetProfilesByBreed(breed)) {
            petDtos.add(convertToDto(pet));
        }
        return petDtos;
    }


    /**
     *
     * @param type of pet to be looked for
     * @return a list of all pets of this type
     * @throws IllegalArgumentException error
     */
    @GetMapping(value = { "/petprofiles/{type}", "/petprofiles/{type}/" })
    public List<PetProfileDTO> getAllPetProfilesOfType(@PathVariable("type") PetType type) throws IllegalArgumentException {

        List<PetProfileDTO> petDtos = new ArrayList<>();
        for (PetProfile pet : petProfileService.getAllPetProfilesByPetType(type)) {
            petDtos.add(convertToDto(pet));
        }
        return petDtos;
    }


    // TODO should be AccountDTO, not Account

    /**
     *
     * @param username of user associated with
     * @param petname of pet to be deleted
     * @throws IllegalArgumentException error
     */
    @DeleteMapping(value = {"/deletePetProfile/{username}&{name}", "/deletePetProfile/{username}&{name}/"})
    public void deletePetProfile(
            @PathVariable("username") String username,
            @PathVariable("petname") String petname)
        throws IllegalArgumentException {


       petProfileService.deletePetProfile(username, petname);

    }

    private PetProfileDTO convertToDto(PetProfile pet) {
        if (pet == null) {
            throw new IllegalArgumentException("There is no such Pet!");
        }

        PetProfileDTO petDto = new PetProfileDTO(pet.getPoster(), pet.getImages(), pet.getApplication(), pet.getName(), pet.getPetType(),
                pet.getBreed(), pet.getDescription(), pet.getId(), pet.getReasonForPosting(), pet.getPostDate(), pet.getPostTime(), pet.isIsAvailable());

        return petDto;
    }


}
