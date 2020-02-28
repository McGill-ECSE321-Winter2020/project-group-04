package ca.mcgill.ecse321.petadoptionsystem;

import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.AdminRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.DonationRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.ImageRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.PetAdoptionSystemRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.PetProfileRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.Image;
import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;
import ca.mcgill.ecse321.petadoptionsystem.model.PetProfile;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;

@SpringBootTest
public class ImageTest {

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
    public void testPersistAndLoadImage() {
        PetAdoptionSystem pas = TestingUtility.initPetAdoptionSystem(1);
        petAdoptionSystemRepository.save(pas);

        Account act = TestingUtility.initAccount("test", "ODHD", pas);

        accountRepository.save(act);

        RegularUser regUser = TestingUtility.initRegularUser(act, pas);
        regularUserRepository.save(regUser);

        PetProfile petProf = TestingUtility.initPetProfile(regUser, pas);

        petProfileRepository.save(petProf);

        Image img = TestingUtility.initImage(1023, petProf);

        img.setDescription("I am trying");
        imageRepository.save(img);

        img = null;
        img = imageRepository.findImageById(1023);

        assertNotNull(img);

        assertEquals(1023, img.getId());

    }

}