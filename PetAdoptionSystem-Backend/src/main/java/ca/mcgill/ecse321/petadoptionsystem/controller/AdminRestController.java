package ca.mcgill.ecse321.petadoptionsystem.controller;

import ca.mcgill.ecse321.petadoptionsystem.dto.AdminDTO;
import ca.mcgill.ecse321.petadoptionsystem.model.Admin;
import ca.mcgill.ecse321.petadoptionsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * The AdminRestController implements RESTful services for the AdminService
 * class of the PetAdoptionSystem backend. It maps HTTP requests to method calls in
 * the business methods.
 * 
 * @author Jessie Tang
 */
@CrossOrigin(origins = "*")
@RestController
public class AdminRestController {

    @Autowired
    private AdminService adminService;

    /**
     * This method retrieves all Admins in the PetAdoptionSystem. It maps HTTP requests to
     * method calls in the business methods.
     * 
     * @return List<AdminDTO> The list of all Admin DTOs in the system.
     */
    @GetMapping(value = {"/admins", "/admins/"})
    public List<AdminDTO> getAllAdmins(){
        List<AdminDTO> adminDTOs = new ArrayList<>();
        for (Admin admin : adminService.getAllAdmins()){
            adminDTOs.add(convertToDTO(admin));
        }
        return adminDTOs;
    }

    /**
     * This helper method converts any given Admin into a corresponding Data Transfer Object (DTO).
     * 
     * @param admin The input Admin.
     * @return AdminDTO The DTO of the Admin.
     */
    private AdminDTO convertToDTO(Admin admin) {
        if (admin == null){
            throw new IllegalArgumentException("The Admin does not exist");
        }
        return new AdminDTO(admin.getId());
    }


}
