package ca.mcgill.ecse321.petadoptionsystem.model;

import javax.persistence.*;

@Entity
public class Account{
   private PetAdoptionSystem petAdoptionSystem;

   @ManyToOne(optional=false)
   public PetAdoptionSystem getPetAdoptionSystem() {
      return this.petAdoptionSystem;
   }
   
   public void setPetAdoptionSystem(PetAdoptionSystem petAdoptionSystem) {
      this.petAdoptionSystem = petAdoptionSystem;
   }
   
   private UserRole userRole;
   
   //@OneToOne(mappedBy="user" , optional=false)
   @OneToOne(mappedBy="user")
   public UserRole getUserRole() { return this.userRole; }
   public void setUserRole(UserRole userRole) {
      this.userRole = userRole;
   }
   
   private String username;

public void setUsername(String value) {
    this.username = value;
}
@Id
public String getUsername() {
    return this.username;
}
private String passwordHash;

private void setPasswordHash(String value) {
    this.passwordHash = value;
}
private String getPasswordHash() {
    return this.passwordHash;
}
private String email;

public void setEmail(String value) {
    this.email = value;
}
public String getEmail() {
    return this.email;
}
}
