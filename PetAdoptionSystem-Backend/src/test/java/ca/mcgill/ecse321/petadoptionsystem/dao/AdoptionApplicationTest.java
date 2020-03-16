package ca.mcgill.ecse321.petadoptionsystem.dao;

import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.AdoptionApplication;
import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;
import ca.mcgill.ecse321.petadoptionsystem.model.PetProfile;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdoptionApplicationTest {

    @Autowired
    private AdoptionApplicationRepository adoptionRepository;
    @Autowired
    private PetAdoptionSystemRepository petAdoptionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RegularUserRepository regularUserRepository;
    @Autowired
    private PetProfileRepository petProfileRepository;

    private int id1 = 123;
    private int id2 = 456;
    private int id3 = 789;
    private Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
    private Time postTime = java.sql.Time.valueOf(LocalTime.of(11, 35));

    @BeforeEach
    @AfterEach
    public void clearDatabase() {

        adoptionRepository.deleteAll();
        petProfileRepository.deleteAll();
        regularUserRepository.deleteAll();
        accountRepository.deleteAll();
        petAdoptionRepository.deleteAll();

    }

    /**
     * AdoptionApplication persistence test
     */
    @Test
    public void testPersistandLoadAdoptionApplication() {

        PetAdoptionSystem pas = TestingUtility.initPetAdoptionSystem(id1);
        petAdoptionRepository.save(pas);

        Account petOwnerAcc = TestingUtility.initAccount("billy", "billy@gmail.com", pas);
        accountRepository.save(petOwnerAcc);

        Account petAdopterAcc = TestingUtility.initAccount("joe", "joe@gmail.com", pas);
        accountRepository.save(petAdopterAcc);

        // user who posts a pet up for adoption
        RegularUser petOwner = TestingUtility.initRegularUser(petOwnerAcc, pas);
        regularUserRepository.save(petOwner);

        // user who is adopting pet
        RegularUser petAdopter = TestingUtility.initRegularUser(petAdopterAcc, pas);
        regularUserRepository.save(petAdopter);

        PetProfile petProf = TestingUtility.initPetProfile(petOwner, pas);
        petProfileRepository.save(petProf);

        AdoptionApplication adoptApp = TestingUtility.initAdoptionApplication(petAdopter, petProf);
        adoptApp.setPostDate(date);
        adoptApp.setPostTime(postTime);

        adoptionRepository.save(adoptApp);

        adoptApp = null;

        // adoptApp = adoptionRepository.findAdoptionById(id5);
        adoptApp = adoptionRepository.findByApplicantAndPetProfile(petAdopter, petProf);

        assertNotNull(adoptApp);
        assertEquals(date, adoptApp.getPostDate());
        assertEquals(postTime, adoptApp.getPostTime());

    }
}