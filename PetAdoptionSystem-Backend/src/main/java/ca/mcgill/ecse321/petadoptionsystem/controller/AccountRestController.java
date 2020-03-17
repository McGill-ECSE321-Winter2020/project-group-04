package ca.mcgill.ecse321.petadoptionsystem.controller;

import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.petadoptionsystem.model.Admin;
import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;
import ca.mcgill.ecse321.petadoptionsystem.service.PetAdoptionSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    /**
     * Logs in the user with input email and password
     *
     * @param username
     * @param password
     * @return AccountDto or null if login failed
     * @throws IllegalArgumentException
     */
    @PostMapping(value = {"/login", "/login/"})
    public AccountDTO login(@RequestParam("username") String username,
                           @RequestParam("password") String password) throws IllegalArgumentException {
        Account user = accountService.login(username, password);
        if (user.getUserRole() instanceof RegularUser) {
            return convertToDTO(user);
        } else if (user.getUserRole() instanceof Admin) {
            return convertToDTO(user);
        } else {
            return null;
        }
    }

    /**
     *
     * @return
     * @throws IllegalArgumentException
     */
    @GetMapping(value = {"/account/all", "/account/all/"})
    public List<AccountDTO> getAllAccounts() throws IllegalArgumentException {
        List<AccountDTO> accountDTOs = new ArrayList<AccountDTO>();

        for (Account account : accountService.getAllAccounts()) {
            accountDTOs.add(convertToDTO(account));
        }

        return accountDTOs;
    }

    /**
     *
     * @param username
     * @return
     * @throws IllegalArgumentException
     */
    @GetMapping(value = {"/account/username/", "/account/username/"})
    public AccountDTO getAccountByUsername(@RequestParam("username") String username) throws IllegalArgumentException {
        return convertToDTO(accountService.getAccountByUsername(username));
    }

    /**
     *
     * @param email
     * @return
     * @throws IllegalArgumentException
     */
    @GetMapping(value = {"/account/email/", "/account/email/"})
    public AccountDTO getAccountsByEmail(@RequestParam("email") String email) throws IllegalArgumentException {
        return convertToDTO(accountService.getAccountByEmail(email));
    }

    /**
     *
     * @param username
     * @param passwordHash
     * @param name
     * @param homeDescription
     * @param phoneNumber
     * @param email
     * @return
     */
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

    /**
     *
     * @param username
     * @param passwordHash
     * @param name
     * @param homeDescription
     * @param phoneNumber
     * @param email
     * @return
     */
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

    /**
     *
     * @param username
     * @param newEmail
     */
    @PutMapping(value = {"/account/updateemail/", "/account/updateemail/"})
    public void updateEmail(@RequestParam("username") String username, @RequestParam("newEmail") String newEmail) {
        accountService.updateEmail(username, newEmail);
    }

    /**
     *
     * @param username
     */
    @DeleteMapping(value = {"/account/delete/", "/account/delete/"})
    public void deleteAccount(@RequestParam("username") String username) {
        accountService.deleteAccount(username);
    }

    /**
     *
     * @param account
     * @return
     */
    private AccountDTO convertToDTO(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("There is no account.");
        }
        AccountDTO accDTO = new AccountDTO(account.getUsername(),account.getEmail(), account.getUserRole());
        return accDTO;
    
    }
}