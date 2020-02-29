package ca.mcgill.ecse321.petadoptionsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.petadoptionsystem.service.AccountService;
import ca.mcgill.ecse321.petadoptionsystem.dto.AccountDTO;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;

@CrossOrigin(origins = "*")
@RestController
public class AccountRestController {

    @Autowired
    private AccountService accountService;
    
    @GetMapping(value = {"/account/all", "/account/all/"})
    public List<AccountDTO> getAllAccounts() throws IllegalArgumentException {
        List<AccountDTO> accountDTOs = new ArrayList<AccountDTO>();

        for (Account account : accountService.getAllAccounts()) {
            accountDTOs.add(convertToDTO(account));
        }

        return accountDTOs;
    }

    @GetMapping(value = {"/account/username/{username}", "/account/username/{username}/"})
    public AccountDTO getAccountByUsername(@PathVariable("username") String username) throws IllegalArgumentException {
        return convertToDTO(accountService.getAccountByUsername(username));
    }

    @GetMapping(value = {"/account/email/{email}", "/account/email/{email}/"})
    public AccountDTO getAccountsByEmail(@PathVariable("email") String email) throws IllegalArgumentException {
        return convertToDTO(accountService.getAccountByEmail(email));
    }

    @PostMapping(value = {"/account/createregular/{username}+{email}+{passwordHash}", "/account/createregular/{username}+{email}+{passwordHash}/"})
    public AccountDTO createRegularUserAccount(@PathVariable("username") String username, @PathVariable("passwordHash") String passwordHash, @PathVariable("email") String email) {
        return convertToDTO(accountService.createRegularUserAccount(username, passwordHash, email));
    }

    @PostMapping(value = {"/account/createadmin/{username}+{email}+{passwordHash}", "/account/createadmin/{username}+{email}+{passwordHash}/"})
    public AccountDTO createAdminAccount(@PathVariable("username") String username, @PathVariable("passwordHash") String passwordHash, @PathVariable("email") String email) {
        return convertToDTO(accountService.createAdminAccount(username, passwordHash, email));
    }

    @PutMapping(value = {"/account/updateemail/{username}+{newEmail}", "/account/updateemail/{username}+{newEmail}/"})
    public void updateEmail(@PathVariable("username") String username, @PathVariable("newEmail") String newEmail) {
        accountService.updateEmail(username, newEmail);
        return;
    }

    @PutMapping(value = {"/account/updatepassword/{username}+{newPasswordHash}", "/account/updatepassword/{username}+{newPasswordHash}/"})
    public void updatePassword(@PathVariable("username") String username, @PathVariable("newPasswordHash") String newPasswordHash) {
        accountService.updatePassword(username, newPasswordHash);
        return;
    }

    @PostMapping(value = {"/account/delete/{username}", "/account/delete/{username}/"})
    public void deleteAccount(@PathVariable("username") String username) {
        accountService.deleteAccount(username);
        return;
    }

    private AccountDTO convertToDTO(Account account) {
        return null;
    }
}