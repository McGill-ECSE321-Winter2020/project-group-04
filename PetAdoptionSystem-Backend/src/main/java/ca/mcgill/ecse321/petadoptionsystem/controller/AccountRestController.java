package ca.mcgill.ecse321.petadoptionsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.petadoptionsystem.service.AccountService;
import ca.mcgill.ecse321.petadoptionsystem.dto.AccountDTO;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;

/**
 * The AccountRestController implements RESTful services for the AccountService
 * class of the PetAdoptionSystem backend. It maps HTTP requests to method calls in
 * the business methods.
 * 
 * @author Garrett Kinman
 */
@CrossOrigin(origins = "*")
@RestController
public class AccountRestController {

    @Autowired
    private AccountService accountService;
    
    /**
     * This method is to retrieve all accounts within the PetAdoptionSystem.
     * 
     * @return List<AccountDTO> This returns a list of all the Account Data Transfer Objects (DTOs).
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
     * This method is to retrieve the account corresponding to a particular username.
     * 
     * @param username The input username to search for.
     * @return AccountDTO The account with the input username.
     * @throws IllegalArgumentException
     */
    @GetMapping(value = {"/account/username/{username}", "/account/username/{username}/"})
    public AccountDTO getAccountByUsername(@PathVariable("username") String username) throws IllegalArgumentException {
        return convertToDTO(accountService.getAccountByUsername(username));
    }

    /**
     * This method is to retrieve the account corresponding to a particular email address.
     * 
     * @param email The input email address to search for.
     * @return AccountDTO The account with the input email address.
     * @throws IllegalArgumentException
     */
    @GetMapping(value = {"/account/email/{email}", "/account/email/{email}/"})
    public AccountDTO getAccountsByEmail(@PathVariable("email") String email) throws IllegalArgumentException {
        return convertToDTO(accountService.getAccountByEmail(email));
    }

    @PostMapping(value = {"/account/createregular", "/account/createregular/"})
    public AccountDTO createRegularUserAccount(@RequestParam("username") String username, @RequestParam("passwordHash") String passwordHash, @RequestParam("email") String email) {
        return convertToDTO(accountService.createRegularUserAccount(username, passwordHash, email));
    }

    /**
     * This method is to create an instance of Account with the given username, email address, and password hash.
     * The created account is associated with a created instance of Admin.
     * 
     * @param username The input username the new account will have.
     * @param passwordHash The input password hash the new account will have.
     * @param email The input email address the new account will have.
     * @return AccountDTO The created account DTO.
     */
    @PostMapping(value = {"/account/createadmin/{username}+{email}+{passwordHash}", "/account/createadmin/{username}+{email}+{passwordHash}/"})
    public AccountDTO createAdminAccount(@PathVariable("username") String username, @PathVariable("passwordHash") String passwordHash, @PathVariable("email") String email) {
        return convertToDTO(accountService.createAdminAccount(username, passwordHash, email));
    }

    /**
     * This method finds the Account with the input username and updates its email address
     * to be the input email address.
     * 
     * @param username The username of the Account whose email address will be updated.
     * @param newEmail The new email address to supply to the correct account.
     */
    @PutMapping(value = {"/account/updateemail/{username}+{newEmail}", "/account/updateemail/{username}+{newEmail}/"})
    public void updateEmail(@PathVariable("username") String username, @PathVariable("newEmail") String newEmail) {
        accountService.updateEmail(username, newEmail);
        return;
    }

    /**
     * This method deletes the Account with the input username.
     * 
     * @param username The username of the Account to be deleted.
     */
    @PostMapping(value = {"/account/delete/{username}", "/account/delete/{username}/"})
    public void deleteAccount(@PathVariable("username") String username) {
        accountService.deleteAccount(username);
        return;
    }

    /**
     * This method is a helper method to convert a given Account into an AccountDTO :)
     * @param account The given Account.
     * @return AccountDTO The DTO version of the Account.
     */
    private AccountDTO convertToDTO(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("There is no account.");
        }
        AccountDTO accDTO = new AccountDTO(account.getUsername(),account.getEmail(), account.getUserRole());
        return accDTO;
    
    }
}