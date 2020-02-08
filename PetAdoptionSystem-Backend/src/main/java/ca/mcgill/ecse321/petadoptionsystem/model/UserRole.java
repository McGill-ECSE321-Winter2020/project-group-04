package ca.mcgill.ecse321.petadoptionsystem.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Id;

@Entity
public abstract class UserRole{
   private Set<PetProfile> petProfile;
   
   @OneToMany(mappedBy="poster" )
   public Set<PetProfile> getPetProfile() {
      return this.petProfile;
   }
   
   public void setPetProfile(Set<PetProfile> petProfiles) {
      this.petProfile = petProfiles;
   }
   
   private User user;
   
   @OneToOne(optional=false)
   public User getUser() {
      return this.user;
   }
   
   public void setUser(User user) {
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
}
