package ca.mcgill.ecse321.petadoptionsystem.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;

// TODO dao and model imports

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public Account createRegularUserAccount(String username, String passwordHash, String email) {
        
        // check if username, email, and passwordHash are valid
        if (username != null && email != null && passwordHash != null) {
            if (username.length() > 0 && email.length() > 0 && passwordHash.length() > 0) {
                
                // check if username and email are unique in the system
                if (!(accountRepository.existsByUsername(username) || accountRepository.existsByEmail(email))) {
                    Account account = new Account();
                    account.setUsername(username);
                    account.setPasswordHash(passwordHash);
                    account.setEmail(email);
                    
                    // TODO: create UserRole

                    return account;
                }
            }
        }

        return null;
    }

    @Transactional
    public Account createAdminAccount(String username, String passwordHash, String email) {
        Account account = new Account();
        account.setUsername(username);
        account.setPasswordHash(passwordHash);
        account.setEmail(email);
        // TODO: create UserRole
        // TODO: check for valid inputs

        return account;
    }

    @Transactional
    public Account getAccount(String username) {
        // TODO
    }

    @Transactional
    public Account getAccountByEmail(String email) {
        // TODO
    }

    @Transactional
    public ArrayList<Account> getAllAccounts() {
        // TODO
    }

    @Transactional
    public boolean updatePassword(String username, String newPasswordHash) {
        // TODO
    }

    @Transactional
    public boolean updateEmail(String username, String newEmail) {
        // TODO
    }

    @Transactional
    public boolean deleteAccount(String username) {
        // TODO
    }
}