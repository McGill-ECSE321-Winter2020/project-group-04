package ca.mcgill.ecse321.petadoptionsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.petadoptionsystem.dao.PetAdoptionSystemRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;

/**
 * @author eknuviad
 */

@Service
public class PetAdoptionSystemService {

    private int systemID = 4;

    @Autowired
    PetAdoptionSystemRepository pasRepository;

    /**
     * 
     * @return the created PetAdoptionSystem
     */
    @Transactional
    public PetAdoptionSystem createPetAdoptionSystem() {

        PetAdoptionSystem pas = pasRepository.findPetAdoptionSystemById(systemID);

        if (pas == null) {
            pas = new PetAdoptionSystem();
            pas.setId(systemID);
            pasRepository.save(pas);
        }

        return pas;

    }

    /**
     * 
     * @return the one existing PetAdoptionSystem
     */
    @Transactional
    public PetAdoptionSystem getPetAdoptionSystem() {

        PetAdoptionSystem pas = pasRepository.findPetAdoptionSystemById(systemID);
        if (pas == null) {
            throw new NullPointerException("No system has been created.");
        }
        return pas;
    }

}