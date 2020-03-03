package ca.mcgill.ecse321.petadoptionsystem.service;

import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.petadoptionsystem.dao.PetAdoptionSystemRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;

import java.util.HashSet;
import java.util.Set;

/**
 * @author eknuviad
 */

@Service
public class PetAdoptionSystemService {

    private int systemID = 4;
    private Set<Account> users = new HashSet<>();

    @Autowired
    PetAdoptionSystemRepository pasRepository;

    /**
     *
     * @return
     */
    @Transactional
    public PetAdoptionSystem createPetAdoptionSystem() {

        PetAdoptionSystem pas = pasRepository.findPetAdoptionSystemById(systemID);

        if (pas == null) {
            pas = new PetAdoptionSystem();
            pas.setId(systemID);
            pas.setClient(users);
            pasRepository.save(pas);
        }

        return pas;

    }

    /**
     *
     * @return
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