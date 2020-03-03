package ca.mcgill.ecse321.petadoptionsystem.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Donation {
    private String donorName;
    private String donorEmail;
 
    

    private int id;

    public void setId(int value) {
        this.id = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return this.id;
    }

    private float amount;

    public void setAmount(float value) {
        this.amount = value;
    }

    public float getAmount() {
        return this.amount;
    }

    private Date date;

    public void setDate(Date value) {
        this.date = value;
    }

    public Date getDate() {
        return this.date;
    }

    public Time time;

    public void setTime(Time value) {
        this.time = value;
    }

    public Time getTime() {
        return this.time;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorEmail() {
        return donorEmail;
    }

    public void setDonorEmail(String donorEmail) {
        this.donorEmail = donorEmail;
    }
}
