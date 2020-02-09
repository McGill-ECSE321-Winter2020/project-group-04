package ca.mcgill.ecse321.petadoptionsystem;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.AdminRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.AdoptionApplicationRepository;
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
<<<<<<< HEAD

||||||| merged common ancestors
=======

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
    private AdoptionApplicationRepository adoptionApplicationRepository;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private PetProfileRepository petProfileRepository;

    @AfterEach
    public void clearDataBase(){
        imageRepository.deleteAll();
        accountRepository.deleteAll();
        petAdoptionSystemRepository.deleteAll();
        petProfileRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadImage(){
        PetAdoptionSystem pas = TestingUtility.initPetAdoptionSystem(1);
        petAdoptionSystemRepository.save(pas);

        Account act = TestingUtility.initAccount("test", "ODHD", pas);
       
        accountRepository.save(act);
        act = null;
        act = accountRepository.findAccountByUsername("test");

        RegularUser regUser = TestingUtility.initRegularUser(1234, act, pas);
    
        regularUserRepository.save(regUser);

        regUser = null;
        regUser = regularUserRepository.findRegularUserByID(1234);

        PetProfile petProf = TestingUtility.initPetProfile(4321, regUser, pas);
        
        petProfileRepository.save(petProf);
        
        petProf = null;
        petProf = petProfileRepository.findPetProfileByID(4321);

        Image img = TestingUtility.initImage(1023, petProf);
        
        img.setDescription("I am trying");
        imageRepository.save(img);

        img = null;
        img = imageRepository.findImageById(1023);

        assertNotNull(img);

        //System.out.println(act.getUsername());
        assertEquals(1023, img.getId());
    }

>>>>>>> Users/Obaric
   

}