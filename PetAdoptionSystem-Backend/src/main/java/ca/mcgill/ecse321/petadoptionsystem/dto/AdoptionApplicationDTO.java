package ca.mcgill.ecse321.petadoptionsystem.dto;

import java.sql.Date;
import java.sql.Time;


public class AdoptionApplicationDTO {

    public RegularUserDTO applicant;
    public PetProfileDTO petprofile;
    public int id;
    public Date date;
    public Time time;

    public AdoptionApplicationDTO(int i, Date date, Time time, RegularUserDTO regUser, PetProfileDTO petProfile) {
        this.id = i;
        this.date = date;
        this.time = time;
        this.applicant = regUser;
        this.petprofile = petProfile;

    }

	public int getId() {
		return id;
    }
    
    public Date getDate(){
        return date;
    }
    public Time getTime(){
        return time;
    }

	public RegularUserDTO getRegularUserDTO() {
		return applicant;
	}

	public PetProfileDTO getPetProfileDTO() {
		return petprofile;
	}

}