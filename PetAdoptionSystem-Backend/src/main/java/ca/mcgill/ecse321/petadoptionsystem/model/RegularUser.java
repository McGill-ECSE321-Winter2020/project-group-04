package ca.mcgill.ecse321.petadoptionsystem.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class RegularUser extends UserRole {
    private Set<Donation> donation;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    public Set<Donation> getDonation() {
        return this.donation;
    }

    public void setDonation(Set<Donation> donations) {
        this.donation = donations;
    }

    private Set<AdoptionApplication> application;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.REMOVE)
    public Set<AdoptionApplication> getApplication() {
        return this.application;
    }

    @OneToOne(optional = false)
    public Account getClient() {
        return this.client;
    }

    public void setApplication(Set<AdoptionApplication> applications) {
        this.application = applications;
    }

    public String name;

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return this.name;
    }

    private String homeDescription;

    public void setHomeDescription(String value) {
        this.homeDescription = value;
    }

    public String getHomeDescription() {
        return this.homeDescription;
    }

    private int phoneNumber;

    public void setPhoneNumber(int value) {
        this.phoneNumber = value;
    }

    public int getPhoneNumber() {
        return this.phoneNumber;
    }

}
