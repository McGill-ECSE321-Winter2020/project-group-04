package ca.mcgill.ecse321.petadoptionsystem.dto;

import ca.mcgill.ecse321.petadoptionsystem.model.Admin;

import java.util.List;

public class AdminDTO {
    private AdminDTO admin;
    //private List<AdminDTO> admins;
    private int id;

    /**
     * @param id admin id
     */
    public AdminDTO(int id){
        this.id = id;
    }
}
