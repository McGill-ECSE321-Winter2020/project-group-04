package ca.mcgill.ecse321.petadoptionsystem;

import ca.mcgill.ecse321.petadoptionsystem.dao.*;
import ca.mcgill.ecse321.petadoptionsystem.model.*;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.junit.jupiter.api.Test;
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
    public void testPersistAndLoadImage(){
        PetAdoptionSystem pas = TestingUtility.initPetAdoptionSystem(1);
         petAdoptionSystemRepository.save(pas);

         Account act = TestingUtility.initAccount("Juan", "Myemail", pas);

         accountRepository.save(act);
         act = null;
         act = accountRepository.findAccountByUsername("Juan");

         RegularUser regUser = TestingUtility.initRegularUser(1111, act, pas);

         regUser.setName("Juanito");
         regUser.setHomeDescription("house");
         regUser.setPhoneNumber(514);

         regularUserRepository.save(regUser);

         regUser = null;
         regUser = regularUserRepository.findRegularUserById(1111);


         assertNotNull(regUser);

         assertEquals("Juanito", regUser.getName());
         assertEquals("house", regUser.getHomeDescription());
         assertEquals(514, regUser.getPhoneNumber());
         assertEquals(1111, regUser.getId());

    }

    @AfterEach
    public void DeleteDataBase(){

        regularUserRepository.deleteAll();
        accountRepository.deleteAll();
        petAdoptionSystemRepository.deleteAll();

    }
   
}