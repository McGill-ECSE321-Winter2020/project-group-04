package ca.mcgill.ecse321.petadoptionsystem.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class PetAdoptionSystem{
   private Set<User> user;
   
   @OneToMany(mappedBy="petAdoptionSystem" , cascade={CascadeType.ALL})
   public Set<User> getUser() {
      return this.user;
   }
   
   public void setUser(Set<User> users) {
      this.user = users;
   }
   
   }
