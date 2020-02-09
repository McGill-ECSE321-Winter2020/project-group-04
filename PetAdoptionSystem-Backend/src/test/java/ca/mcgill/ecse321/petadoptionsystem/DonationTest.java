package ca.mcgill.ecse321.petadoptionsystem;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.DonationRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.PetAdoptionSystemRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.Donation;
import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;

import org.springframework.web.bind.annotation.RequestMapping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DonationTest {
    @Autowired
    private PetAdoptionSystemRepository petAdoptionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired 
    private RegularUserRepository regularUserRepository;
    @Autowired
    private DonationRepository donationRepository;

    private int id1 = 123;
    private int id2 = 456;
    private int id3 = 789;
    private Date date = java.sql.Date.valueOf(LocalDate.of(2020, Month.JANUARY, 31));
    private Time postTime = java.sql.Time.valueOf(LocalTime.of(11, 35));

    @AfterEach
    public void clearDatabase(){

        donationRepository.deleteAll();
        regularUserRepository.deleteAll();
        accountRepository.deleteAll();
        petAdoptionRepository.deleteAll();    

    }

    @Test
    public void testPersistandLoadDonation(){

        PetAdoptionSystem pas = TestingUtility.initPetAdoptionSystem(id1);
        petAdoptionRepository.save(pas);

        Account acc = TestingUtility.initAccount("billy","billy@gmail.com" , pas);
        accountRepository.save(acc);
        
        RegularUser petAdopter = TestingUtility.initRegularUser(id2, acc, pas);// user who is adopting pet
        regularUserRepository.save(petAdopter);
        
        Donation donation = TestingUtility.initDonation(id3, petAdopter);
        donation.setAmount(500);
        donation.setDate(date);
        donation.setTime(postTime);

        donationRepository.save(donation);

        donation = null;

        donation = donationRepository.findDonatitonById(id3);

        assertNotNull(donation);
        assertEquals(id3, donation.getId());
        assertEquals(date, donation.getDate());
        assertEquals(postTime, donation.getTime());

    }
   
}