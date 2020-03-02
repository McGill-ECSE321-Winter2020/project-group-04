package ca.mcgill.ecse321.petadoptionsystem.controller;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.PetProfileRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;
import ca.mcgill.ecse321.petadoptionsystem.dto.PetProfileDTO;
import ca.mcgill.ecse321.petadoptionsystem.model.*;
import ca.mcgill.ecse321.petadoptionsystem.service.PetProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController

public class PetProfileRestController {

    @Autowired
    private PetProfileService petProfileService;

    @Autowired
    private PetProfileRepository petProfileRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RegularUserRepository regularUserRepository;

    /**
     *
     * @param petProfileDTO union of both classes RegularUser and PetProfile
     * @param date          date of posting
     * @param postTime      time of posting
     * @return the newly created profile
     * @throws IllegalArgumentException error
     */
    @PostMapping(value = { "/petprofile", "/petprofile/" })
    public PetProfileDTO createPetProfile(@RequestParam("username") String username,
            // @RequestBody String[] images,
            @RequestParam("petName") String petName, 
            @RequestParam("petType") String petType,
            @RequestParam("breed") String breed,
            @RequestParam("description") String description, 
            @RequestParam("reasonForPosting") String reasonForPosting,
            @RequestParam("isAvailable") String isAvailable)

            // @RequestParam Date date,
            throws IllegalArgumentException {

       
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        Time time = Time.valueOf(localTime);
        Date date = Date.valueOf(localDate);
        HashSet<String> setImages = new HashSet<String>();
        int i =1;
        for(i = 1; i< 10;){
            setImages.add("dffdfd" + i);
            i++;
        }
        boolean isAv = isAvailable=="1" ? true : false;

        PetProfile pet = petProfileService.createPetProfile(breed, description,
                petName, convertToPetType(petType), time, date, username,
                reasonForPosting, isAv, setImages);

        return convertToDto(pet);

    }

    /**
     *
     * @param username of the user that is updating
     * @param petname  of the pet
     * @return
     * @throws IllegalArgumentException
     */
    @PutMapping(value = { "/updatePetProfile/{username}/{petname}", "/updatePetProfile/{petname}/" })
    public PetProfileDTO updatePetProfile(
            // @RequestBody PetProfileDTO petProfileDTO,
            @PathVariable("username") String username, @PathVariable("petname") String petname)
            throws IllegalArgumentException {

        Account account = accountRepository.findAccountByUsername(username);
        RegularUser userRole = regularUserRepository.findRegularUserByUser(account);

        PetProfile petProfile = petProfileRepository.findPetProfileByNameAndPoster(petname, userRole);

        PetProfileDTO petProfileDTO = convertToDto(petProfile);

        PetProfile pet = petProfileService.updatePetProfile(username, petProfileDTO.getBreed(),
                petProfileDTO.getDescription(), petProfileDTO.getReasonForPosting(), petProfileDTO.getPetType(),
                petname, petProfileDTO.getIsAvailable(), petProfileDTO.getImages());

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

    /**
     *
     * @param username username that we are looking for
     * @return th list of petprofiles of a singular user
     * @throws IllegalArgumentException errors
     */
    @GetMapping(value = { "/petprofiles/{username}", "/petprofiles/{username}/" })
    public List<PetProfileDTO> getAllPetProfilesOfUser(@PathVariable("username") String username)
            throws IllegalArgumentException {

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
    public List<PetProfileDTO> getAllPetProfilesOfBreed(@PathVariable("breed") String breed)
            throws IllegalArgumentException {

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
    public List<PetProfileDTO> getAllPetProfilesOfType(@PathVariable("type") PetType type)
            throws IllegalArgumentException {

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
     * @param petname  of pet to be deleted
     * @throws IllegalArgumentException error
     */
    @DeleteMapping(value = { "/deletePetProfile/{username}&{name}", "/deletePetProfile/{username}&{name}/" })
    public void deletePetProfile(@PathVariable("username") String username, @PathVariable("petname") String petname)
            throws IllegalArgumentException {

        petProfileService.deletePetProfile(username, petname);

    }

    private PetProfileDTO convertToDto(PetProfile pet) {
        if (pet == null) {
            throw new IllegalArgumentException("There is no such Pet!");
        }

        PetProfileDTO petDto = new PetProfileDTO(pet.getPoster(), pet.getImages(), pet.getApplication(), pet.getName(),
                pet.getPetType(), pet.getBreed(), pet.getDescription(), pet.getId(), pet.getReasonForPosting(),
                pet.getPostDate(), pet.getPostTime(), pet.isIsAvailable());

        return petDto;
    }

    private PetType convertToPetType(String petType){
        
        try {
            petType = petType.trim().toUpperCase();
            System.out.println("PETTYPE: " + petType);
            return PetType.valueOf(petType);
           
        } catch(Exception e){
            throw new IllegalArgumentException("Please enter a valid PetType" + PetType.values().toString());
        }
    }

}
