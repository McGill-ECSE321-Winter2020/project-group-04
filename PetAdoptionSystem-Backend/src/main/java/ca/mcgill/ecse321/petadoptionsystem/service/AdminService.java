package ca.mcgill.ecse321.petadoptionsystem.service;

import ca.mcgill.ecse321.petadoptionsystem.dao.AdminRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.internal.util.collections.ArrayHelper.toList;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    /**
     * @param id Admin id
     * @return Admin corresponding to input id
     */
    @Transactional
    public Admin getAdminById(int id){
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
    public List<Admin> getAllAdmins() {
        List<Admin> admins;
        admins = toList(adminRepository.findAll());
        return admins;
    }

    /**
     * @param iterable
     * @param <T>
     * @return
     */
    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
