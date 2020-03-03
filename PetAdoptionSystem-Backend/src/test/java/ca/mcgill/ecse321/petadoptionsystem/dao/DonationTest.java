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
import java.util.List;

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

        String donor = "Bill Gate";
        
        Donation donation = TestingUtility.initDonation(id3, donor);
        donation.setAmount(500);
        donation.setDate(date);
        donation.setTime(postTime);
        donation.setDonorName(donor);

        donationRepository.save(donation);

        List<Donation> donations = donationRepository.findDonationsByDonorName(donor);
        donations.add(donation);
        donation = null;

        assertNotNull(donations);
        assertEquals(date, donations.get(0).getDate());
        assertEquals(postTime, donations.get(0).getTime());

    }
   
}