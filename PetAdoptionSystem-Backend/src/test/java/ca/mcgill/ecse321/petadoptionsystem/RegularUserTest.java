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


    @Test
    public void testPersistAndLoadRegularUser() {
        PetAdoptionSystem pas = TestingUtility.initPetAdoptionSystem(1);
        petAdoptionSystemRepository.save(pas);

        Account act = TestingUtility.initAccount("Juan", "Myemail", pas);

        accountRepository.save(act);
        act = null;
        act = accountRepository.findAccountByUsername("Juan");

        RegularUser regUser = TestingUtility.initRegularUser(act, pas);

        regUser.setName("Juanito");
        regUser.setHomeDescription("house");
        regUser.setPhoneNumber(514);

        regularUserRepository.save(regUser);

        regUser = null;
        int regId = act.getUserRole().getId();
        regUser = regularUserRepository.findRegularUserById(regId);


        assertNotNull(regUser);

        assertEquals("Juanito", regUser.getName());
        assertEquals("house", regUser.getHomeDescription());
        assertEquals(514, regUser.getPhoneNumber());
        assertEquals(regId, regUser.getId());

    }

    @AfterEach
    public void DeleteDataBase() {

        regularUserRepository.deleteAll();
        accountRepository.deleteAll();
        petAdoptionSystemRepository.deleteAll();

    }
}