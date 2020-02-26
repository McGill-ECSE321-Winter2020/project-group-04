package ca.mcgill.ecse321.petadoptionsystem;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.AdminRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.PetAdoptionSystemRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.Admin;
import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;
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
    @Autowired
    private RegularUserRepository regularUserRepository;

    @AfterEach
    public void clearDataBase(){
        regularUserRepository.deleteAll();
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

        Admin admin = TestingUtility.initAdmin(account, system);

        adminRepository.save(admin);

        assertNotNull(admin);

        admin = null;

        int adId = accountRepository.findAccountByUsername("JohnDoe361").getUserRole().getId();
        admin = adminRepository.findAdminById(adId);
        assertEquals(adId, admin.getId());
    }

}