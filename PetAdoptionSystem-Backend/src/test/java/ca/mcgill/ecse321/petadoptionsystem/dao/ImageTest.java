package ca.mcgill.ecse321.petadoptionsystem.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.AdminRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.DonationRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.ImageRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.PetAdoptionSystemRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.PetProfileRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;

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

    }


}