package ca.mcgill.ecse321.petadoptionsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    private AccountDTO convertToDTO(Account account) {
        return null;
    }
}