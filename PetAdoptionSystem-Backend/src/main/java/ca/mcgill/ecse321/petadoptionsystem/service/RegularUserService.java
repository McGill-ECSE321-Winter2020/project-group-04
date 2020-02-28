package ca.mcgill.ecse321.petadoptionsystem.service;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hibernate.internal.util.collections.ArrayHelper.toList;

@Service
public class RegularUserService {

    @Autowired
    RegularUserRepository regularuserrepository;

    @Autowired
    AccountRepository accountRepository;

    /**
     *
     * @param username name of the user to get
     * @return returns the user with all the attributes
     */



    @Transactional
    public RegularUser getRegularUserByUsername(String username){

        String error = "";

        if (accountRepository.existsByUsername(username)) error += "No existing user with the username" + username;
        if (error.length() > 0) throw new IllegalArgumentException(error);

        Account account = accountRepository.findAccountByUsername(username);
        RegularUser regularuser = regularuserrepository.findRegularUserByUser(account);

        return(regularuser);
    }

    /**
     *
     * @return returns the list of all regular users
     */
    @Transactional
    public List<RegularUser> getAllRegularUsers(){

        return toList(regularuserrepository.findAll());
    }

    /**
     *
     * @param username username
     * @param name name
     * @param homedescription string
     * @param phonenumber phone
     * @return returns the saved and updated regular user
     */
    @Transactional
    public RegularUser updateRegularUser(String username,String name, String homedescription, int phonenumber){

        String error = "";

        if (accountRepository.existsByUsername(username)) error += "No user associated with username" + username;
        if (error.length() > 0) throw new IllegalArgumentException(error);

        Account account = accountRepository.findAccountByUsername(username);
        RegularUser regularuser = regularuserrepository.findRegularUserByUser(account);

        if (name != null) {
            regularuser.setName(name);
        }
        if (phonenumber != 0) {
            regularuser.setPhoneNumber(phonenumber);
        }
        if (homedescription != null) {
            regularuser.setHomeDescription(homedescription);
        }
        regularuserrepository.save(regularuser);

        return regularuser;

    }


}