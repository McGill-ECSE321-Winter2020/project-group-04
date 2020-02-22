package ca.mcgill.ecse321.petadoptionsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.petadoptionsystem.dao.PetAdoptionSystemRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;

@Service
public class PetAdoptionSystemService {

    private int systemID = 4;

    @Autowired
    PetAdoptionSystemRepository pasRepository;

    public PetAdoptionSystem createPetAdoptionSystem(){

        PetAdoptionSystem pas = new PetAdoptionSystem();
        pas.setId(systemID);
        pasRepository.save(pas);
        return pas;

    }



}