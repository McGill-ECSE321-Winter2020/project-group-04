package ca.mcgill.ecse321.petadoptionsystem.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.petadoptionsystem.model.Account;

// TODO dao and model imports

@Service
public class AccountService {


    @Transactional
    public Account createRegularUserAccount(String username, String passwordHash, String email) {
        // TODO
    }

    @Transactional
    public Account createAdminAccount(String username, String passwordHash, String email) {
        // TODO
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