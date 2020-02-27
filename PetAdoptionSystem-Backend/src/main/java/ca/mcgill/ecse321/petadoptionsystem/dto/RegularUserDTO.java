package ca.mcgill.ecse321.petadoptionsystem.dto;

import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.AdoptionApplication;
import ca.mcgill.ecse321.petadoptionsystem.model.Donation;

import java.util.Set;


public class RegularUserDTO {

    public Set<Donation> donation;
    public Account user;
    public String name;
    public Set<AdoptionApplication> application;
    public String homeDescription;
    public int phoneNumber;

    //Get everything from the DTO classes
    public RegularUserDTO(Set<Donation> donation, Account user, String name,
                          Set<AdoptionApplication> application, String homeDescription, int phoneNumber) {
        this.donation = donation;
        this.user = user;
        this.name = name;
        this.application = application;
        this.homeDescription = homeDescription;
        this.phoneNumber = phoneNumber;
    }

    public Account getUser() {
        return this.user;
    }


}
