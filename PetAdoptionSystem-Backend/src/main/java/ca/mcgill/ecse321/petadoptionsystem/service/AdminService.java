package ca.mcgill.ecse321.petadoptionsystem.service;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.AdminRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jessie Tang
 */
@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AccountRepository accountRepository;

    /**
     * @return list of all Admins currently in the Admin repository
     */
    @Transactional
    public List<Admin> getAllAdmins() {
        List<Admin> admins;
        admins = toList(adminRepository.findAll());
        Admin admin = new Admin();
        return admins;
    }

    /**
     * 
     * @param username of the account associated with the admin we want
     * @return the admin whose account has the above username
     */
    @Transactional
    public Admin getAdminByUsername(String username){
        Account account = accountRepository.findAccountByUsername(username);
        Admin admin = adminRepository.findAdminByUser(account);
        return admin;
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
