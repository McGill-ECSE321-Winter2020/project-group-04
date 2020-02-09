package ca.mcgill.ecse321.petadoptionsystem;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
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
    private AccountRepository accountRepository;

    @AfterEach
    public void clearDataBase(){
        accountRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadRegistration(){

        Account account = new Account();
        String userName = "JohnDoe361";
        String email = "johndoe1955@gmail.com";
        //int idea = 1111;
        //String passwordHash = "868345C2BF61B5BA913D578B674838CB08A3575B"; //greenFurryPuppies123, SHA1

        account.setUsername(userName);
        account.setEmail(email);
        //account.setPetAdoptionSystem();
        //account.setPasswordHash(passwordHash);
        accountRepository.save(account);
    }

    @Test
    public void testLoadPetAdoptionSystemId(){
        Account account = new Account();
        account.getPetAdoptionSystem();
        accountRepository.save(account);
    }
   
}