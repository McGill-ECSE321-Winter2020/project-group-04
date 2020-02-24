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
        
        String error = "";

        // check if valid inputs
        if (username == null || username.length() == 0) error += "The username cannot be empty.\n";
        if (passwordHash == null || passwordHash.length() == 0) error += "The password hash cannot be empty.\n";
        if (email == null || email.length() == 0) error += "The email address cannot be empty.\n";

        if (error.length() > 0) throw new IllegalArgumentException(error);


        // check if username or email is taken
        if (accountRepository.existsByUsername(username)) error += "That username is already taken.\n";
        if (accountRepository.existsByEmail(email)) error += "That email address is already taken.\n";

        if (error.length() > 0) throw new IllegalArgumentException(error);


        // create account with the now-validated inputs
        Account account = new Account();
        account.setUsername(username);
        account.setPasswordHash(passwordHash);
        account.setEmail(email);
                    
        // TODO: create UserRole

        accountRepository.save(account);
        return account;
    }

    @Transactional
    public Account createAdminAccount(String username, String passwordHash, String email) {
        
        String error = "";

        // check if valid inputs
        if (username == null || username.length() == 0) error += "The username cannot be empty.\n";
        if (passwordHash == null || passwordHash.length() == 0) error += "The password hash cannot be empty.\n";
        if (email == null || email.length() == 0) error += "The email address cannot be empty.\n";

        if (error.length() > 0) throw new IllegalArgumentException(error);


        // check if username or email is taken
        if (accountRepository.existsByUsername(username)) error += "That username is already taken.\n";
        if (accountRepository.existsByEmail(email)) error += "That email address is already taken.\n";

        if (error.length() > 0) throw new IllegalArgumentException(error);


        // create account with the now-validated inputs
        Account account = new Account();
        account.setUsername(username);
        account.setPasswordHash(passwordHash);
        account.setEmail(email);
                    
        // TODO: create UserRole

        accountRepository.save(account);
        return account;
    }

    @Transactional
    public Account getAccount(String username) {

        String error = "";

        // check if valid username
        if (username == null || username.length() == 0) error += "The username cannot be empty.\n";

        if (error.length() > 0) throw new IllegalArgumentException(error);

        return accountRepository.findAccountByUsername(username);
    }

    @Transactional
    public Account getAccountByEmail(String email) {

        String error = "";

        // check if valid username
        if (email == null || email.length() == 0) error += "The email address cannot be empty.\n";

        if (error.length() > 0) throw new IllegalArgumentException(error);

        return accountRepository.findAccountByEmail(email);
    }

    @Transactional
    public ArrayList<Account> getAllAccounts() {
        return toArrayList(accountRepository.findAll());
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

    private static <T> ArrayList<T> toArrayList(Iterable<T> iterable) {
        ArrayList<T> list = new ArrayList<T>();
        for (T t : iterable) {
            list.add(t);
        }
        return list;
    }
}