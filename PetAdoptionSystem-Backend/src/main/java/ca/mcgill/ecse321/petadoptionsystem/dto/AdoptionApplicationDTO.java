package ca.mcgill.ecse321.petadoptionsystem.dto;

import java.sql.Date;
import java.sql.Time;


public class AdoptionApplicationDTO {

    public RegularUserDTO applicant;
    public PetProfileDTO petprofile;

    public AdoptionApplicationDTO(int i, Date date, Time time, RegularUserDTO regUser, PetProfileDTO petProfile) {
        
    }

}