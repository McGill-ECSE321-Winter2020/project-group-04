package ca.mcgill.ecse321.petadoptionsystem.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;
import ca.mcgill.ecse321.petadoptionsystem.service.PetAdoptionSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.petadoptionsystem.service.AccountService;
import ca.mcgill.ecse321.petadoptionsystem.dto.AccountDTO;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;

@CrossOrigin(origins = "*")
@RestController
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PetAdoptionSystemService petService;
    
    @GetMapping(value = {"/account/all", "/account/all/"})
    public List<AccountDTO> getAllAccounts() throws IllegalArgumentException {
        List<AccountDTO> accountDTOs = new ArrayList<AccountDTO>();

        for (Account account : accountService.getAllAccounts()) {
            accountDTOs.add(convertToDTO(account));
        }

        return accountDTOs;
    }

    @GetMapping(value = {"/account/username/", "/account/username/"})
    public AccountDTO getAccountByUsername(@RequestParam("username") String username) throws IllegalArgumentException {
        return convertToDTO(accountService.getAccountByUsername(username));
    }

    @GetMapping(value = {"/account/email/", "/account/email/"})
    public AccountDTO getAccountsByEmail(@RequestParam("email") String email) throws IllegalArgumentException {
        return convertToDTO(accountService.getAccountByEmail(email));
    }

    @PostMapping(value = {"/account/createregular", "/account/createregular/"})
    public AccountDTO createRegularUserAccount(
        @RequestParam("username") String username, 
        @RequestParam("passwordHash") String passwordHash, 
        @RequestParam("name") String name,
        @RequestParam("homeDescription") String homeDescription,
        @RequestParam("phoneNumber") int phoneNumber,
        @RequestParam("email") String email) {
        PetAdoptionSystem pas = petService.getPetAdoptionSystem();
        return convertToDTO(accountService.createRegularUserAccount(pas, username, name, passwordHash, email, homeDescription, phoneNumber));
    }

    @PostMapping(value = {"/account/createadmin/", "/account/createadmin/"})
    public AccountDTO createAdminAccount(@RequestParam("username") String username, 
    @RequestParam("passwordHash") String passwordHash, 
    @RequestParam("name") String name,
    @RequestParam("homeDescription") String homeDescription,
    @RequestParam("phoneNumber") int phoneNumber,
    @RequestParam("email") String email) {
        PetAdoptionSystem pas = petService.getPetAdoptionSystem();
        return convertToDTO(accountService.createRegularUserAccount(pas, username, name, passwordHash, email, homeDescription, phoneNumber));
    }

    @PutMapping(value = {"/account/updateemail/", "/account/updateemail/"})
    public void updateEmail(@RequestParam("username") String username, @RequestParam("newEmail") String newEmail) {
        accountService.updateEmail(username, newEmail);
    }

    @PostMapping(value = {"/account/delete/", "/account/delete/"})
    public void deleteAccount(@RequestParam("username") String username) {
        accountService.deleteAccount(username);
    }

    private AccountDTO convertToDTO(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("There is no account.");
        }
        AccountDTO accDTO = new AccountDTO(account.getUsername(),account.getEmail(), account.getUserRole());
        return accDTO;
    
    }
}