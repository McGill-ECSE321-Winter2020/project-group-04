package ca.mcgill.ecse321.petadoptionsystem;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.PetAdoptionSystemRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        PetAdoptionSystem system = TestingUtility.initPetAdoptionSystem(123);
        petAdoptionSystemRepository.save(system);

        String username = "JohnDoe361";
        String email = "johndoe1955@gmail.com";
        Account account = TestingUtility.initAccount(username, email, system);
        accountRepository.save(account);

        //test Account not null
        assertNotNull(account);

        //test Account data persistence
        account = null;
        account = accountRepository.findAccountByUsername(username);
        assertEquals(username, account.getUsername());
        assertEquals(email, account.getEmail());

    }

}