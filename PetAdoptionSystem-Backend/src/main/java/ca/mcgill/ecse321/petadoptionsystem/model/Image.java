package ca.mcgill.ecse321.petadoptionsystem.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

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
@Id
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
private int sizeX;

public void setSizeX(int value) {
    this.sizeX = value;
}
public int getSizeX() {
    return this.sizeX;
}
private int sizeY;

public void setSizeY(int value) {
    this.sizeY = value;
}
public int getSizeY() {
    return this.sizeY;
}
private String filePath;

public void setFilePath(String value) {
    this.filePath = value;
}
public String getFilePath() {
    return this.filePath;
}
}
