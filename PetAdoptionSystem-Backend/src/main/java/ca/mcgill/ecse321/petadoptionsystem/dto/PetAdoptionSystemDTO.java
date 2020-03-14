package ca.mcgill.ecse321.petadoptionsystem.dto;

import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import java.util.HashSet;
import java.util.Set;

public class PetAdoptionSystemDTO {

    public int systemID;
    public Set<Account> clients = new HashSet<>();

    public PetAdoptionSystemDTO(int id) {
        this.systemID = id;
    }

    public int getSystemID() {
        return systemID;
    }

}