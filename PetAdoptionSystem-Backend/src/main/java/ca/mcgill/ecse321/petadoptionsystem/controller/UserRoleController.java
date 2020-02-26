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
     * @param username
     * @return userRoleDTO object
     * @throws IllegalArgumentException
     */
    @GetMapping(value = {"/userroles/{username}", "/userroles/{username}/"})
    public UserRoleDTO getUserRoleByUsername(@PathVariable("username") String username) throws IllegalArgumentException {
        return convertToDTO(userRoleService.getUserRoleByUsername(username));
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
