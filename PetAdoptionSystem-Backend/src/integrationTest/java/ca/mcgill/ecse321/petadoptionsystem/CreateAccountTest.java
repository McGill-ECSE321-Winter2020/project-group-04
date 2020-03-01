package ca.mcgill.ecse321.petadoptionsystem;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.AdminRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.PetAdoptionSystemRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.Admin;
import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateAccountTest {

//    @LocalServerPort
//    private int port;
//
//    private static final String URL = "https://eventregistration-backend-4-n.herokuapp.com/";
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private PetAdoptionSystemRepository systemRepository;
//    @Autowired
//    private AccountRepository accountRepository;
//    @Autowired
//    private AdminRepository adminRepository;
//    @Autowired
//    private RegularUserRepository regularUserRepository;
//
//    @Before
//    public void prepareTest(){
//        //create app
//        restTemplate.exchange(URL + "/getSystem", HttpMethod.POST, null, String.class);
//        //create admin account
//        restTemplate.exchange(URL + "/account/createadmin/Sam/sam123@gmail.com/mypassword", HttpMethod.POST, null, String.class);
//
//    }
//
//    @Test
//    public void getAccountByUsernameTest(){
//        String expected = "{\"username\":Sam\"}";
//        ResponseEntity<String> response = restTemplate.exchange(URL + "account/2", HttpMethod.GET, null, String.class);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//    }



}
