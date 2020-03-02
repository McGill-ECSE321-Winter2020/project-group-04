package ca.mcgill.ecse321.petadoptionsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.petadoptionsystem.dto.PetAdoptionSystemDto;
import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;
import ca.mcgill.ecse321.petadoptionsystem.service.PetAdoptionSystemService;

@CrossOrigin(origins = "*")
@RestController
public class PetAdoptionSystemRestController {

    @Autowired
    private PetAdoptionSystemService service;

    @PostMapping(value = {"/createSystem", "/createSystem"})
    public PetAdoptionSystemDto createPetAdoptionSystem()throws IllegalArgumentException{
         PetAdoptionSystem pas = service.createPetAdoptionSystem();
         return convertToDto(pas);
    }

    @GetMapping(value = {"/getSystem", "/getSystem/"})
    public PetAdoptionSystemDto getPetAdoptionSystem() throws IllegalArgumentException {
        PetAdoptionSystem pas = service.getPetAdoptionSystem();
        return convertToDto(pas);
    }

    private PetAdoptionSystemDto convertToDto(PetAdoptionSystem pas) {
        return new PetAdoptionSystemDto(pas.getId());
    }


}