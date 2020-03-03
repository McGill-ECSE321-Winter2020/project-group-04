package ca.mcgill.ecse321.petadoptionsystem.dto;

import java.sql.Date;
import java.sql.Time;


public class AdoptionApplicationDTO {

    // public RegularUserDTO applicant;
    // public PetProfileDTO petprofile;
    public String applicantName;
    public int petProfileID;
    public int applicationID;
    public Date date;
    public Time time;

    public boolean isAproved;
    public boolean isConfirmed;

    public AdoptionApplicationDTO(int i, Date date, Time time, RegularUserDTO regUser, PetProfileDTO petProfile) {
        this.applicationID = i;
        this.date = date;
        this.time = time;

        this.applicantName = regUser.getClient();
        this.applicationID = regUser.getId();
        this.petProfileID = petProfile.getId();

    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public int getPetProfileID() {
        return petProfileID;
    }

    public void setPetProfileID(int petProfileID) {
        this.petProfileID = petProfileID;
    }

    public int getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    public Date getDate() {
        return date;
    }


    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public boolean isAproved() {
        return isAproved;
    }

    public void setAproved(boolean isAproved) {
        this.isAproved = isAproved;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    



}