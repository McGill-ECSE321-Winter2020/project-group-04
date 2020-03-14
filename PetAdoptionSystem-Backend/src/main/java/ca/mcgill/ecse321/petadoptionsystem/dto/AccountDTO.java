package ca.mcgill.ecse321.petadoptionsystem.dto;

import ca.mcgill.ecse321.petadoptionsystem.model.UserRole;

public class AccountDTO {

    private String username;
    private String email;
    // private UserRole userRole;

    /**
     *
     * @param username
     * @param email
     * @param userRole
     */
    public AccountDTO(String username, String email, UserRole userRole) {
        this.username = username;
        this.email = email;
        // this.userRole = userRole;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    // public UserRole getUserRole() {
    //     return this.userRole;
    // }
}