package ca.mcgill.ecse321.petadoptionsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Entity
public class AdoptionApplication {
    private PetProfile petProfile;

    @JsonBackReference
    @ManyToOne(optional = false)
    public PetProfile getPetProfile() {
        return this.petProfile;
    }

    public void setPostDate(Date value) {
        this.postDate = value;
    }

    public Date getPostDate() {
        return this.postDate;
    }

    private Time postTime;

    public void setPostTime(Time value) {
        this.postTime = value;
    }

    public Time getPostTime() {
        return this.postTime;
    }

    public void setPetProfile(PetProfile petProfile) {
        this.petProfile = petProfile;
    }

    private RegularUser applicant;

    @ManyToOne(optional = false)
    public RegularUser getApplicant() {
        return this.applicant;
    }

    public void setApplicant(RegularUser applicant) {
        this.applicant = applicant;
    }

    private boolean isApproved;

    public void setIsApproved(boolean value) {
        this.isApproved = value;
    }

    public boolean isIsApproved() {
        return this.isApproved;
    }

    private int id;

    public void setId(int value) {
        this.id = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return this.id;
    }

    private Date postDate;

    private boolean isConfirmed;

    public void setIsConfirmed(boolean value) {
        this.isConfirmed = value;
    }

    public boolean isIsConfirmed() {
        return this.isConfirmed;
    }

}
