package ca.mcgill.ecse321.petadoptionsystem.dao;
import org.springframework.beans.factory.annotation.Autowired;


import ca.mcgill.ecse321.petadoptionsystem.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;


@ExtendWith(SpringExtension.class)

@SpringBootTest
public class PetProfileTest {


    @Autowired
    private PetProfileRepository petProfilerepository;

    @Autowired
    private PetAdoptionSystemRepository petAdoptionSystemRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RegularUserRepository regularUserRepository;


    /**
     * Testing Persistance of the Database for the PetProfile
     */

    @Test
    public void TestPersistencePetProfile() {
        PetAdoptionSystem pas = TestingUtility.initPetAdoptionSystem(1);
        //Need to initialize the PetAdoptionSystem class before initializing lower classes
        petAdoptionSystemRepository.save(pas);

        Account act = TestingUtility.initAccount("Pedro", "pedro@gmail.com", pas);
        //Initializing Account
        accountRepository.save(act);
        // act = null;
        // act = accountRepository.findAccountByUsername("Pedro");

        RegularUser regUser = TestingUtility.initRegularUser(act, pas);
        //Initializing the User
        regularUserRepository.save(regUser);
        // regUser = null;
        // regUser = regularUserRepository.findRegularUserById(1111);

        PetProfile petProf = TestingUtility.initPetProfile(regUser, pas);
        //Initializing the PetProfile and setting the attributes that we want to test for persistence
        String name = "doggy";
        petProf.setBreed("chihuahua");
        petProf.setName(name);
        petProf.setDescription("fat and tired");
        petProf.setReasonForPosting("very ugly");
        petProf.setPetType(PetType.DOG);
        Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.FEBRUARY, 9));
        Time time = java.sql.Time.valueOf(LocalTime.of(11, 35));
        petProf.setPostDate(date);
        petProf.setPostTime(time);

        petProfilerepository.save(petProf);

        petProf = null;
        petProf = petProfilerepository.findPetProfileByNameAndPoster(name, regUser);

        //
        assertNotNull(petProf);
        assertEquals("chihuahua", petProf.getBreed());
        assertEquals("doggy", petProf.getName());
        assertEquals("fat and tired", petProf.getDescription());
        assertEquals("very ugly", petProf.getReasonForPosting());
        assertEquals(PetType.DOG, petProf.getPetType());
        assertEquals(date, petProf.getPostDate());
        assertEquals(time, petProf.getPostTime());

    }

    @AfterEach
    public void clearDatabase() {

        petProfilerepository.deleteAll();
        regularUserRepository.deleteAll();
        accountRepository.deleteAll();
        petAdoptionSystemRepository.deleteAll();

    }
}




