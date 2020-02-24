package ca.mcgill.ecse321.petadoptionsystem.controller;

import ca.mcgill.ecse321.petadoptionsystem.dto.UserRoleDTO;
import ca.mcgill.ecse321.petadoptionsystem.model.UserRole;
import ca.mcgill.ecse321.petadoptionsystem.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public abstract class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    /**
     * @return list of all UserRoles
     */
    @GetMapping(value = {"/userroles", "/userroles/"})
    public List<UserRoleDTO> getAllUserRoles(){
        List<UserRoleDTO> userRoleDTOs = new ArrayList<>();
        for (UserRole userRole : userRoleService.getAllUserRoles()){
            userRoleDTOs.add(convertToDTO(userRole));
        }
        return userRoleDTOs;
    }

    /**
     * @param id UserRole id
     * @return UserRoleDTO object
     * @throws IllegalArgumentException
     */
    @GetMapping(value = {"/userroles/{id}", "/userroles/{id}/"})
    public UserRoleDTO getUserRoleById(@PathVariable("id") int id) throws IllegalArgumentException {
        return convertToDTO(userRoleService.getUserRoleById(id));
    }

    /**
     * @param id UserRole id
     * @return if deletion of UserRole was successful
     * @throws IllegalArgumentException
     */
    @RequestMapping(value = {"/userroles/{id}", "/userroles/{id}/"})
    public boolean deleteUserRole(@PathVariable("id") int id) throws IllegalArgumentException {
        return userRoleService.deleteUserRole(id);
    }

    /**
     * @param userRole
     * @return UserRoleDTO object
     */
    public UserRoleDTO convertToDTO(UserRole userRole){
        if (userRole == null){
            throw new IllegalArgumentException("The UserRole does not exist");
        }
        return new UserRoleDTO(userRole.getId());
    }
}
