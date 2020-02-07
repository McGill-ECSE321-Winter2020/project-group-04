package ca.mcgill.ecse321.petadoptionsystem.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Donation{
   private RegularUser user;
   
   @ManyToOne(optional=false)
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
}
