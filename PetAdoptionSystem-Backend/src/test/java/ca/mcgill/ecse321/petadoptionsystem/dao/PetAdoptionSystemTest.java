package ca.mcgill.ecse321.petadoptionsystem.dao;
import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.petadoptionsystem.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class PetAdoptionSystemTest {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private PetAdoptionSystemRepository petAdoptionSystemRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RegularUserRepository regularUserRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private PetProfileRepository petProfileRepository;

    @AfterEach
    public void clearDataBase() {
        imageRepository.deleteAll();

        petProfileRepository.deleteAll();

        regularUserRepository.deleteAll();
        adminRepository.deleteAll();
        donationRepository.deleteAll();
        accountRepository.deleteAll();
        petAdoptionSystemRepository.deleteAll();

    }

    
    @Test
    void testPersistAndLoadPetAdoptionSystem() {
         PetAdoptionSystem pas = TestingUtility.initPetAdoptionSystem(1);
         petAdoptionSystemRepository.save(pas);
         pas = null;

         pas = petAdoptionSystemRepository.findPetAdoptionSystemById(1);
         assertNotNull(pas);
         assertEquals(1, pas.getId());

    }
}
