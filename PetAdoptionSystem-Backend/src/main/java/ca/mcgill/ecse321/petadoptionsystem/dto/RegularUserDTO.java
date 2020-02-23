package ca.mcgill.ecse321.petadoptionsystem.dto;

import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.AdoptionApplication;
import ca.mcgill.ecse321.petadoptionsystem.model.Donation;

import java.util.Set;


public class RegularUserDTO {

    private Set<Donation> donation;
    private Account user;
    private String name;
    private Set<AdoptionApplication> application;
    private String homeDescription;
    private int phoneNumber;

    public RegularUserDTO(Set<Donation> donation, Account user, String name,
                          Set<AdoptionApplication> application, String homeDescription, int phoneNumber) {
        this.donation = donation;
        this.user = user;
        this.name = name;
        this.application = application;
        this.homeDescription = homeDescription;
        this.phoneNumber = phoneNumber;
    }


    public Set<Donation> getDonation() {
        return this.donation;
    }

    public Set<AdoptionApplication> getApplication() {
        return this.application;
    }

    public Account getUser() {
        return this.user;
    }

    public String getName() {
        return this.name;
    }

    public String getHomeDescription() {
        return this.homeDescription;
    }

    public int getPhoneNumber() {
        return this.phoneNumber;
    }

}
