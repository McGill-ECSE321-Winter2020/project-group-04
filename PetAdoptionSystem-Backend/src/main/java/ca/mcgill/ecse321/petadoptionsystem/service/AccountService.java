package ca.mcgill.ecse321.petadoptionsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.Admin;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;
import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;

// TODO dao and model imports

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public Account createRegularUserAccount(String username, String passwordHash, String email) throws IllegalArgumentException {
        
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
        account.setUserRole(new RegularUser());

        accountRepository.save(account);
        return account;
    }

    @Transactional
    public Account createAdminAccount(String username, String passwordHash, String email) throws IllegalArgumentException {
        
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
        account.setUserRole(new Admin());

        accountRepository.save(account);
        return account;
    }

    @Transactional
    public Account getAccountByUsername(String username) throws IllegalArgumentException {

        String error = "";

        if (!accountRepository.existsByUsername(username))
            error += "No user associated with username" + username;
        if (error.length() > 0) throw new IllegalArgumentException(error);

        // check if valid username
        if (username == null || username.length() == 0) error += "The username cannot be empty.\n";

        if (error.length() > 0) throw new IllegalArgumentException(error);

        return accountRepository.findAccountByUsername(username);
    }

    @Transactional
    public Account getAccountByEmail(String email) throws IllegalArgumentException {

        String error = "";

        if (!accountRepository.existsByEmail(email))
            error += "No user associated with username" + email;
        if (error.length() > 0) throw new IllegalArgumentException(error);

        // check if valid username
        if (email == null || email.length() == 0) error += "The email address cannot be empty.\n";

        if (error.length() > 0) throw new IllegalArgumentException(error);

        return accountRepository.findAccountByEmail(email);
    }

    @Transactional
    public List<Account> getAllAccounts() {
        return toList(accountRepository.findAll());
    }

    @Transactional
    public void updateEmail(String username, String newEmail) throws IllegalArgumentException {
        
        String error = "";

        if (!accountRepository.existsByUsername(username))
            error += "No user associated with username" + username;
        if (error.length() > 0) throw new IllegalArgumentException(error);

        if (newEmail == null || newEmail.length() == 0) error += "The email address cannot be empty.\n";

        if (error.length() > 0) throw new IllegalArgumentException(error);

        // retrieve the correct account and update email address
        Account account = this.getAccountByUsername(username);
        account.setEmail(newEmail);
        accountRepository.save(account);

        return;
    }

    @Transactional
    public void deleteAccount(String username) {
        String error = "";

        if (!accountRepository.existsByUsername(username))
            error += "No user associated with username" + username;
        if (error.length() > 0) throw new IllegalArgumentException(error);

        Account account = this.getAccountByUsername(username);
        accountRepository.delete(account);
        return;
    }

    private static <T> List<T> toList(Iterable<T> iterable) {
        ArrayList<T> list = new ArrayList<T>();
        for (T t : iterable) {
            list.add(t);
        }
        return list;
    }
}