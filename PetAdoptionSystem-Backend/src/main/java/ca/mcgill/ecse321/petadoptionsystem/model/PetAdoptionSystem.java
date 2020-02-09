package ca.mcgill.ecse321.petadoptionsystem.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class PetAdoptionSystem{
   private Set<Account> user;
   
   @OneToMany(mappedBy="petAdoptionSystem" , cascade = CascadeType.REMOVE)
   public Set<Account> getUser() {
      return this.user;
   }
   
   public void setUser(Set<Account> users) {
      this.user = users;
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
