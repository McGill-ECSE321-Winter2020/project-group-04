package ca.mcgill.ecse321.petadoptionsystem.service;

import ca.mcgill.ecse321.petadoptionsystem.dao.AdminRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Admin;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;
import ca.mcgill.ecse321.petadoptionsystem.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

import static org.hibernate.internal.util.collections.ArrayHelper.toList;

public class UserRoleService {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    RegularUserRepository regularUserRepository;

    /**
     * @return list of all UserRoles, Admin and RegularUser
     */
    @Transactional
    public List<UserRole> getAllUserRoles(){
        List admins = toList(adminRepository.findAll());
        List regularUsers = toList(regularUserRepository.findAll());
        regularUsers.addAll(admins);
        return regularUsers;
    }

    /**
     * @param id UserRole id
     * @return UserRole corresponding to input id
     */
    @Transactional
    public UserRole getUserRole(int id){
        Admin admin = null;
        RegularUser regularUser = null;

        try {
            admin  = adminRepository.findAdminById(id);
        }
        catch(NullPointerException e1){
            //if can't find id in admin repo, then look in regular user repo
            try {
                regularUser = regularUserRepository.findRegularUserById(id);
            }
            catch(NullPointerException e2){
                System.out.println("NullPointerException: Invalid UserRole id");
            }
        }
        if (admin != null){
            return admin;
        }
        else{
            if (regularUser != null){
                return regularUser;
            }
            else{
                return null;
            }
        }

    }

    /**
     * @param id UserRole id for deletion
     * @return if deletion was successful
     */
    @Transactional
    public boolean deleteUserRole(int id){
        Boolean deleted = true;

        try {
            adminRepository.deleteById(id);
        }
        catch(NullPointerException e1){
            try {
                regularUserRepository.deleteById(id);
            }
            catch(NullPointerException e2){
                System.out.println("NullPointerException: Invalid UserRole id");
                deleted = false;
            }
        }
        return deleted;
    }


}
