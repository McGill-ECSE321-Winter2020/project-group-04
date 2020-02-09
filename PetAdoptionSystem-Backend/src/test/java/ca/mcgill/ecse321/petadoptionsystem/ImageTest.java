package ca.mcgill.ecse321.petadoptionsystem;

import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.test.context.ActiveProfiles;

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

    @BeforeEach
    public void clearDataBase(){

        imageRepository.deleteAll();
        imageRepository.flush();
        donationRepository.deleteAll();
        adoptionApplicationRepository.deleteAll();
        /*petProfileRepository.deleteAll();
        /*regularUserRepository.deleteAll();
        adminRepository.deleteAll();
        accountRepository.deleteAll();
        petAdoptionSystemRepository.deleteAll();*/
    }

    @AfterEach
    public void clearDataBase1(){

        imageRepository.deleteAll();
        imageRepository.flush();
        donationRepository.deleteAll();
        adoptionApplicationRepository.deleteAll();
        /*petProfileRepository.deleteAll();
        /*regularUserRepository.deleteAll();
        adminRepository.deleteAll();
        accountRepository.deleteAll();
        petAdoptionSystemRepository.deleteAll();*/
    }

    @Test
    public void testPersistAndLoadImage(){
        /* PetAdoptionSystem pas = TestingUtility.initPetAdoptionSystem(1);
         petAdoptionSystemRepository.save(pas);

         Account act = TestingUtility.initAccount("Juan", "Myemail", pas);
       
         accountRepository.save(act);
         act = null;
         act = accountRepository.findAccountByUsername("Juan");

         RegularUser regUser = TestingUtility.initRegularUser(1111, act, pas);
    
        regularUserRepository.save(regUser);

         regUser = null;
         regUser = regularUserRepository.findRegularUserById(1111);

         PetProfile petProf = TestingUtility.initPetProfile(4444, regUser, pas);
        
         petProfileRepository.save(petProf);
        
         petProf = null;
         petProf = petProfileRepository.findPetProfileById(4444);

         Image img = TestingUtility.initImage(0001, petProf);
        
         img.setDescription("I am trying");
         imageRepository.save(img);
         img = null;
         img = imageRepository.findImageById(0001);

         assertNotNull(img);

         //System.out.println(act.getUsername());
         assertEquals(0001, img.getId());*/
    }

}