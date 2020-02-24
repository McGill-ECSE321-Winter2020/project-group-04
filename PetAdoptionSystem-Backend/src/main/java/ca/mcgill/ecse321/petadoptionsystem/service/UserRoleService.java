package ca.mcgill.ecse321.petadoptionsystem.service;

import ca.mcgill.ecse321.petadoptionsystem.dao.AdminRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;
import ca.mcgill.ecse321.petadoptionsystem.dto.UserRoleDTO;
import ca.mcgill.ecse321.petadoptionsystem.model.Admin;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;
import ca.mcgill.ecse321.petadoptionsystem.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static org.hibernate.internal.util.collections.ArrayHelper.toList;

@Service
public abstract class UserRoleService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    RegularUserRepository regularUserRepository;

    /**
     * @return list of all UserRoles, Admin and RegularUser
     */
    @Transactional
    public List<UserRole> getAllUserRoles(){
        List<Admin> admins = toList(adminRepository.findAll());
        List<RegularUser> regularUsers = toList(regularUserRepository.findAll());
        List<UserRole> userRoles = new ArrayList<>();
        userRoles.add((UserRole) admins);
        userRoles.add((UserRole) regularUsers);
        return userRoles;
    }


    /**
     * @param id UserRole id
     * @return UserRole corresponding to input id
     */
    @Transactional
    public UserRole getUserRoleById(int id){
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
            return regularUser;
        }

    }

    /**
     * @param id UserRole id for deletion
     * @return if deletion was successful
     */
    @Transactional
    public boolean deleteUserRole(int id){
        boolean deleted = true;

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

    /**
     * @param iterable
     * @param <T>
     * @return list
     */
    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
