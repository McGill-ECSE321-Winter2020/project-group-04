package ca.mcgill.ecse321.petadoptionsystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
public class Account {
    private PetAdoptionSystem petAdoptionSystem;

    @ManyToOne(optional = false)
    public PetAdoptionSystem getPetAdoptionSystem() {
        return this.petAdoptionSystem;
    }

    public void setPetAdoptionSystem(PetAdoptionSystem petAdoptionSystem) {
        this.petAdoptionSystem = petAdoptionSystem;
    }

    private UserRole userRole;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonManagedReference
    public UserRole getUserRole() {
        return this.userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String username;

    public void setUsername(String value) {
        this.username = value;
    }

    @Id
    public String getUsername() {
        return this.username;
    }

    private String passwordHash;

    public void setPasswordHash(String value) {
        this.passwordHash = value;
    }

    public String getPasswordHash() {
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
