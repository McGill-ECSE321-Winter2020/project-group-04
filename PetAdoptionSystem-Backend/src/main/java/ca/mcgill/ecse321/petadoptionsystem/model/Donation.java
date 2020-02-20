package ca.mcgill.ecse321.petadoptionsystem.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

@Entity
public class Donation {
    private RegularUser user;

    @ManyToOne(optional = false)
    public RegularUser getUser() {
        return this.user;
    }

    public void setUser(RegularUser user) {
        this.user = user;
    }

    private int id;

    public void setId(int value) {
        this.id = value;
    }

    @Id
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
}