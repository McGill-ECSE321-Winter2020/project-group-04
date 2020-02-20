package ca.mcgill.ecse321.petadoptionsystem.service;

import ca.mcgill.ecse321.petadoptionsystem.dao.AdminRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

import static org.hibernate.internal.util.collections.ArrayHelper.toList;

public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    /**
     * @param id Admin id
     * @return Admin corresponding to input id
     */
    @Transactional
    public Admin getAdmin(int id){
        Admin admin = null;
        try {
            admin = adminRepository.findAdminById(id);
        }
        catch(NullPointerException e) {
            System.out.println("NullPointerException: Invalid Admin id");
        }
        return admin;
    }

    /**
     * @return list of all Admins currently in the Admin repository
     */
    @Transactional
    public List<Admin> getAllAdmin() {
        List admins = null;
        try {
            admins = toList(adminRepository.findAll());
        }
        catch(NullPointerException e){
            System.out.println("NullPointerException: Empty Admin repository");
        }
        return admins;
    }
}
