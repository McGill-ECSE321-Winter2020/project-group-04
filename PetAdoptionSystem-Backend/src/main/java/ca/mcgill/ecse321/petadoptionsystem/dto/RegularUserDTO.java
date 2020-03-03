package ca.mcgill.ecse321.petadoptionsystem.dto;

import ca.mcgill.ecse321.petadoptionsystem.model.AdoptionApplication;
import ca.mcgill.ecse321.petadoptionsystem.model.Donation;

import java.util.HashSet;
import java.util.Set;


public class RegularUserDTO {
    public String client;
    public String name;
    // public Set<AdoptionApplication> application;
    public String homeDescription;
    public int phoneNumber;
    public int id;

    //Get everything from the DTO classes
    @SuppressWarnings("unchecked")
    public RegularUserDTO(String name){
        
        this("", name, new HashSet<AdoptionApplication>(), "", 0);
    }
    
    public RegularUserDTO(String client, String name,
                          Set<AdoptionApplication> application, String homeDescription, int phoneNumber) {
        this.client = client;
        this.name = name;
        // this.application = application;
        this.homeDescription = homeDescription;
        this.phoneNumber = phoneNumber;
    }


    public String getClient() {
        return this.client;
    }

    // public Set<AdoptionApplication> getApplication() {
    //     return this.application;
    // }

    public String getName() {
        return this.name;
    }

    public String getHomeDescription() {
        return this.homeDescription;
    }

    public int getPhoneNumber() {
        return this.phoneNumber;
    }

    public int getId(){
        return this.id;
    }

}