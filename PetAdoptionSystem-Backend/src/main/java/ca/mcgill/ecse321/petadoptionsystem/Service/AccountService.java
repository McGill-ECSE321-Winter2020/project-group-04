package ca.mcgill.ecse321.petadoptionsystem.Service;

public class AccountService {

    public Account createRegularUserAccount(String username, String passwordHash, String email) {
        // TODO
    }

    public Account createAdminAccount(String username, String passwordHash, String email) {
        // TODO
    }

    public Account getAccount(String username) {
        // TODO
    }

    public Account getAccountByEmail(String email) {
        // TODO
    }

    public ArrayList<Account> getAllAccounts() {
        // TODO
    }

    public boolean updatePassword(String username, String newPasswordHash) {
        // TODO
    }

    public boolean updateEmail(String username, String newEmail) {
        // TODO
    }

    public boolean deleteAccount(String username) {
        // TODO
    }
}