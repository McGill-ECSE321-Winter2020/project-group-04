package ca.mcgill.ecse321.petadoptionsystem.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class PetProfile{
   private UserRole poster;
   
   @ManyToOne(optional=false)
   public UserRole getPoster() {
      return this.poster;
   }
   
   public void setPoster(UserRole poster) {
      this.poster = poster;
   }
   
   private Set<Image> image;
   
   @OneToMany(mappedBy="petProfile" )
   public Set<Image> getImage() {
      return this.image;
   }
   
   public void setImage(Set<Image> images) {
      this.image = images;
   }
   
   private Set<AdoptionApplication> application;
   
   @OneToMany(mappedBy="petProfile" )
   public Set<AdoptionApplication> getApplication() {
      return this.application;
   }
   
   public void setApplication(Set<AdoptionApplication> applications) {
      this.application = applications;
   }
   
   private String name;

public void setName(String value) {
    this.name = value;
}
public String getName() {
    return this.name;
}
private PetType petType;

public void setPetType(PetType value) {
    this.petType = value;
}
public PetType getPetType() {
    return this.petType;
}
private String breed;

public void setBreed(String value) {
    this.breed = value;
}
public String getBreed() {
    return this.breed;
}
private String description;

public void setDescription(String value) {
    this.description = value;
}
public String getDescription() {
    return this.description;
}
}
