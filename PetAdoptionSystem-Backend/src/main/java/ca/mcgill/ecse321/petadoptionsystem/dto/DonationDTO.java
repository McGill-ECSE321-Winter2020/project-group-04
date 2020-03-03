package ca.mcgill.ecse321.petadoptionsystem.dto;

import java.sql.Time;
import java.util.Date;

public class DonationDTO {
//    private RegularUserDTO regularUserDto;
    public String nameOfDonor;
    private Time time;
    private Date date;
    private float amnt;
    public DonationDTO(){};
    /**
     * 
     * @param regUserDto
     * @param time
     * @param date
     * @param amount
     */
    public DonationDTO(RegularUserDTO regUserDto, Time time, Date date, float amount){
        this.time = time;
        this.nameOfDonor = regUserDto.getClient();
        this.date = date;
        this.amnt = amount;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getAmnt() {
        return amnt;
    }

    public void setAmnt(float amnt) {
        this.amnt = amnt;
    }

    public String getNameOfDonor() {
        return this.nameOfDonor;
    }

    public void setNameOfDonor(String nameOfDonor) {
        this.nameOfDonor = nameOfDonor;
    }
}