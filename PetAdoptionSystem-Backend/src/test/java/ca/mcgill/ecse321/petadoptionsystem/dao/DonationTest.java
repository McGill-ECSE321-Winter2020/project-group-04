package ca.mcgill.ecse321.petadoptionsystem.dao;

import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.Donation;
import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;
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

    @BeforeEach
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
        
        RegularUser petAdopter = TestingUtility.initRegularUser(acc, pas);// user who is adopting pet
        regularUserRepository.save(petAdopter);
        
        Donation donation = TestingUtility.initDonation(id3, petAdopter);
        donation.setAmount(500);
        donation.setDate(date);
        donation.setTime(postTime);

        donationRepository.save(donation);

        donation = null;

        donation = donationRepository.findDonationById(id3);

        assertNotNull(donation);
        assertEquals(id3, donation.getId());
        assertEquals(date, donation.getDate());
        assertEquals(postTime, donation.getTime());

    }
   
}