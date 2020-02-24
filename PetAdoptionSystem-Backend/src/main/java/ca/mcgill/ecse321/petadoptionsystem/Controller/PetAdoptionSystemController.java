package ca.mcgill.ecse321.petadoptionsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.petadoptionsystem.dto.PetAdoptionSystemDTO;
import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;
import ca.mcgill.ecse321.petadoptionsystem.service.PetAdoptionSystemService;

@CrossOrigin(origins = "*")
@RestController
public class PetAdoptionSystemController{

    @Autowired
    private PetAdoptionSystemService service;

    @RequestMapping(value = {"/createSystem", "/createSystem/"})
    public void createPetAdoptionSystem()throws IllegalArgumentException{ 
         service.createPetAdoptionSystem();
    }

    @GetMapping(value = {"/getSystem", "/getSystem/"})
    public PetAdoptionSystemDTO getPetAdoptionSystem()throws IllegalArgumentException{
        PetAdoptionSystem pas = service.getPetAdoptionSystem();
        return convertToDto(pas);
    }

    private PetAdoptionSystemDTO convertToDto(PetAdoptionSystem pas) {
        PetAdoptionSystemDTO pasDto = new PetAdoptionSystemDTO(pas.getId());
        return pasDto;
    }


}