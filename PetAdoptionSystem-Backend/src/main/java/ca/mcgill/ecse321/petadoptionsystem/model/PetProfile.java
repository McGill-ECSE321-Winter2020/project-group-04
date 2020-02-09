package ca.mcgill.ecse321.petadoptionsystem.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.CascadeType;

@Entity
public class PetProfile{
   public UserRole poster;
   
   @ManyToOne(optional=false)
   public UserRole getPoster() {
      return this.poster;
   }
   
   public void setPoster(UserRole poster) {
      this.poster = poster;
   }
   
   public Set<Image> image;

   @OneToMany(mappedBy="petProfile", cascade = CascadeType.REMOVE)
   public Set<Image> getImage() {
      return this.image;
   }
   
   public void setImage(Set<Image> images) {
      this.image = images;
   }
   
   public Set<AdoptionApplication> application;

   @OneToMany(mappedBy="petProfile", cascade = CascadeType.REMOVE)
   public Set<AdoptionApplication> getApplication() {
      return this.application;
   }
   
   public void setApplication(Set<AdoptionApplication> applications) {
      this.application = applications;
   }
   
   public String name;

public void setName(String value) {
    this.name = value;
}
public String getName() {
    return this.name;
}
public PetType petType;

public void setPetType(PetType value) {
    this.petType = value;
}

@Enumerated
public PetType getPetType() {
    return this.petType;
}
public String breed;

public void setBreed(String value) {
    this.breed = value;
}
public String getBreed() {
    return this.breed;
}
public String description;

public void setDescription(String value) {
    this.description = value;
}
public String getDescription() {
    return this.description;
}
public int id;

public void setId(int value) {
    this.id = value;
}

@Id
public int getId() {
    return this.id;
}
public String reasonForPosting;

public void setReasonForPosting(String value) {
    this.reasonForPosting = value;
}
public String getReasonForPosting() {
    return this.reasonForPosting;
}
public Date postDate;

public void setPostDate(Date value) {
    this.postDate = value;
}
public Date getPostDate() {
    return this.postDate;
}
public Time postTime;

public void setPostTime(Time value) {
    this.postTime = value;
}
public Time getPostTime() {
    return this.postTime;
}


}
