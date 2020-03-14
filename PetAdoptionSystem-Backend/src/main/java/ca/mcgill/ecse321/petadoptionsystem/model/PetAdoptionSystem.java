package ca.mcgill.ecse321.petadoptionsystem.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class PetAdoptionSystem {
   private Set<Account> client;

   @OneToMany(mappedBy = "petAdoptionSystem", cascade = { CascadeType.ALL })
   public Set<Account> getClient() {
      return this.client;
   }

   public void setClient(Set<Account> users) {
      this.client = users;
   }

   private int id;
   public boolean wasSet;

   public void setId(int value) {
      if(wasSet == false){
         this.id = value;
         this.wasSet = true;
      }
      
   }

   @Id
   public int getId() {
      return this.id;
   }
}
