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

@CrossOrigin(origins = "*")
@RestController
public class AdminRestController {

    @Autowired
    private AdminService adminService;

    @GetMapping(value = {"/admins", "/admins/"})
    public List<AdminDTO> getAllAdmins(){
        List<AdminDTO> adminDTOs = new ArrayList<>();
        for (Admin admin : adminService.getAllAdmins()){
            adminDTOs.add(convertToDTO(admin));
        }
        return adminDTOs;
    }


    private AdminDTO convertToDTO(Admin admin) {
        if (admin == null){
            throw new IllegalArgumentException("The Admin does not exist");
        }
        return new AdminDTO(admin.getId());
    }


}
