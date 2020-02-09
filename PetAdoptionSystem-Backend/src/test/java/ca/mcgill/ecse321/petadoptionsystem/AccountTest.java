package ca.mcgill.ecse321.petadoptionsystem;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.PetAdoptionSystemRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountTest {
    @Autowired
    private PetAdoptionSystemRepository petAdoptionSystemRepository;
    @Autowired
    private AccountRepository accountRepository;

    @AfterEach
    public void clearDataBase(){
        accountRepository.deleteAll();
        petAdoptionSystemRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadAccount(){
        int sysId = 123;
        PetAdoptionSystem system = TestingUtility.initPetAdoptionSystem(sysId);
        petAdoptionSystemRepository.save(system);

        String username = "JohnDoe361";
        String email = "johndoe1955@gmail.com";
        Account account = TestingUtility.initAccount(username, email, system);
        //String passwordHash = "868345C2BF61B5BA913D578B674838CB08A3575B"; //greenFurryPuppies123, SHA1

        //account.setUsername(username);
        //account.setEmail(email);
        //account.setPetAdoptionSystem();
        //account.setPasswordHash(passwordHash);
        accountRepository.save(account);

//        system = null;
//        system = petAdoptionSystemRepository.findPetAdoptionSystemById(sysId);
//        account = null;
//        account = accountRepository.findAccountByUsernameAndPetAdoptionSystem(username, system);
        account = null;
        account = accountRepository.findAccountByUsername(username);

    }

//    @Test
//    public void testLoadPetAdoptionSystemId(){
//        Account account = new Account();
//        account.getPetAdoptionSystem();
//        accountRepository.save(account);
//    }
   
}