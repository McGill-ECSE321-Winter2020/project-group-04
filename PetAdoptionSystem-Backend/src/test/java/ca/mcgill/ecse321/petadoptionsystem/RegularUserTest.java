package ca.mcgill.ecse321.petadoptionsystem;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.PetAdoptionSystemRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RegularUserTest {
    @Autowired
    private PetAdoptionSystemRepository petAdoptionSystemRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RegularUserRepository regularUserRepository;

    @AfterEach
    public void clearDataBase(){
        regularUserRepository.deleteAll();
        accountRepository.deleteAll();
        petAdoptionSystemRepository.deleteAll();
    }

    @Test
    public void persistAndLoadRegularUser(){
        PetAdoptionSystem system = TestingUtility.initPetAdoptionSystem(123);
        petAdoptionSystemRepository.save(system);

        Account account = TestingUtility.initAccount("JohnDoe361", "johndoe1955@gmail.com", system);
        accountRepository.save(account);

        RegularUser regUsr = new RegularUser();
        regUsr.setId(789);
        regUsr.setUser(account);
        account.setUserRole(regUsr);
        regularUserRepository.save(regUsr);

        assertNotNull(regUsr);

        regUsr = null;
        regUsr = regularUserRepository.findRegularUserById(789);
        assertEquals(789, regUsr.getId());
    }
}