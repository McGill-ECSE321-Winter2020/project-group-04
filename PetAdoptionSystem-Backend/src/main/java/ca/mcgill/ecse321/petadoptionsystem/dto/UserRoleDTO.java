package ca.mcgill.ecse321.petadoptionsystem.dto;

import ca.mcgill.ecse321.petadoptionsystem.model.Admin;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;
import ca.mcgill.ecse321.petadoptionsystem.model.UserRole;

import java.util.List;

public class UserRoleDTO {
    private UserRole userRole;
    private int id;

    /**
     * @param id UserRole id
     */
    public UserRoleDTO(int id){
        this.id = id;
    }

}
