package ca.mcgill.ecse321.petadoptionsystem.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Image{
   private PetProfile petProfile;
   
   @ManyToOne(optional=false)
   public PetProfile getPetProfile() {
      return this.petProfile;
   }
   
   public void setPetProfile(PetProfile petProfile) {
      this.petProfile = petProfile;
   }
   
   private int id;

public void setId(int value) {
    this.id = value;
}
public int getId() {
    return this.id;
}
private String description;

public void setDescription(String value) {
    this.description = value;
}
public String getDescription() {
    return this.description;
}
}
