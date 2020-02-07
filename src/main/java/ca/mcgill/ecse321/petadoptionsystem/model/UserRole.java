package ca.mcgill.ecse321.petadoptionsystem.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
   
   }
