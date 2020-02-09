package ca.mcgill.ecse321.petadoptionsystem.model;

import org.graalvm.compiler.graph.spi.Canonicalizable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

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
@Enumerated
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
private int id;

public void setId(int value) {
    this.id = value;
}
@Id
public int getId() {
    return this.id;
}
private String reasonForPosting;

public void setReasonForPosting(String value) {
    this.reasonForPosting = value;
}
public String getReasonForPosting() {
    return this.reasonForPosting;
}
private Date postDate;

private void setPostDate(Date value) {
    this.postDate = value;
}
private Date getPostDate() {
    return this.postDate;
}
private Time postTime;

private void setPostTime(Time value) {
    this.postTime = value;
}
private Time getPostTime() {
    return this.postTime;
}


}
