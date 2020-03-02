package ca.mcgill.ecse321.petadoptionsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.petadoptionsystem.dto.PetAdoptionSystemDto;
import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;
import ca.mcgill.ecse321.petadoptionsystem.service.PetAdoptionSystemService;

/**
 * @author Edem Nuviadenu
 */
@CrossOrigin(origins = "*")
@RestController
public class PetAdoptionSystemRestController {

    @Autowired
    private PetAdoptionSystemService service;

    /**
     * 
     * @return DTO of the created PetAdoptionSystem.
     * @throws IllegalArgumentException
     */
    @PostMapping(value = {"/createSystem", "/createSystem"})
    public PetAdoptionSystemDto createPetAdoptionSystem()throws IllegalArgumentException{
         PetAdoptionSystem pas = service.createPetAdoptionSystem();
         return convertToDto(pas);
    }

    /**
     * 
     * @return The DTO of the existing PetAdoptionSystem.
     * @throws IllegalArgumentException
     */
    @GetMapping(value = {"/getSystem", "/getSystem/"})
    public PetAdoptionSystemDto getPetAdoptionSystem() throws IllegalArgumentException {
        PetAdoptionSystem pas = service.getPetAdoptionSystem();
        return convertToDto(pas);
    }

    /**
     * 
     * @param pas The given PetAdoptionSystem.
     * @return The DTO of the given PetAdoptionSystem.
     */
    private PetAdoptionSystemDto convertToDto(PetAdoptionSystem pas) {
        return new PetAdoptionSystemDto(pas.getId());
    }


}