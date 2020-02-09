package ca.mcgill.ecse321.petadoptionsystem;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.AdminRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.PetAdoptionSystemRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.Admin;
import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;
import ca.mcgill.ecse321.petadoptionsystem.model.UserRole;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AdminTest {
    @Autowired
    private PetAdoptionSystemRepository petAdoptionSystemRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AdminRepository adminRepository;

    @AfterEach
    public void clearDataBase(){
        adminRepository.deleteAll();
        accountRepository.deleteAll();
        petAdoptionSystemRepository.deleteAll();
    }

    @Test
    public void persistAndLoadAdmin(){
        PetAdoptionSystem system = TestingUtility.initPetAdoptionSystem(123);
        petAdoptionSystemRepository.save(system);

        Account account = TestingUtility.initAccount("JohnDoe361", "johndoe1955@gmail.com", system);
        accountRepository.save(account);

        int adId = 456;
        //Admin myAd= TestingUtility.initAdmin(adId, account, system);
        Admin admin = new Admin();
        admin.setId(adId);
        admin.setUser(account);
        account.setUserRole(admin);
        adminRepository.save(admin);

        assertNotNull(admin);

        admin = null;
        admin = adminRepository.findAdminById(adId);
        assertEquals(adId, admin.getId());
    }

}