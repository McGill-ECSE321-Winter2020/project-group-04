package ca.mcgill.ecse321.petadoptionsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.util.Set;

@Entity
public abstract class UserRole {
   private Set<PetProfile> petProfile;

   @JsonManagedReference
   @OneToMany(mappedBy = "poster", cascade = CascadeType.REMOVE)
   public Set<PetProfile> getPetProfile() {
      return this.petProfile;
   }

   public void setPetProfile(Set<PetProfile> petProfiles) {
      this.petProfile = petProfiles;
   }

   public Account client;

   @OneToOne(optional = false)
   @JsonBackReference
   public Account getClient() {
      return this.client;
   }

   public void setClient(Account client) {
      this.client = client;
   }

   private int id;

   public void setId(int value) {
      this.id = value;
   }

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   public int getId() {
      return this.id;
   }
}
