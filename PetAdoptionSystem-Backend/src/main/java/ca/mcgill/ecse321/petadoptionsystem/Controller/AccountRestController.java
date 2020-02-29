package ca.mcgill.ecse321.petadoptionsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.petadoptionsystem.service.AccountService;
import ca.mcgill.ecse321.petadoptionsystem.dto.AccountDTO;

@CrossOrigin(origins = "*")
@RestController
public class AccountRestController {

    @Autowired
    private AccountService accountService;
    
    @GetMapping(value = {"/accounts", "/accounts/"})
    public List<AccountDTO> getAllAccounts() throws IllegalArgumentException {
        List<AccountDTO> accountDTOs = new ArrayList<AccountDTO>();

        for (Account account : accountService.getAllAccounts()) {
            accountsDTOs.add(convertToDTO(account));
        }

        return accountDTOs;
    }
}