package ca.mcgill.ecse321.petadoptionsystem.Service;

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

    /**
     *
     * @param name name of the user to get
     * @return returns the user with all the attributes
     */
    @Transactional
    public RegularUser getRegularUserByName(String name){
    RegularUser regularuser = regularuserrepository.findRegularUserByName(name);
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
     * @param id id of the user to get
     * @return returns the user with all attributes
     */
    @Transactional
    public RegularUser getRegularUserById(int id){
    RegularUser regularuser = regularuserrepository.findRegularUserById(id);
    return regularuser;

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
    public RegularUser updateRegularUser(Account username,String name, String homedescription, int phonenumber){

    RegularUser regularuser = regularuserrepository.findRegularUserByUser(username);

    regularuser.setName(name);
    regularuser.setPhoneNumber(phonenumber);
    regularuser.setHomeDescription(homedescription);
    regularuserrepository.save(regularuser);

    return regularuser;

}


}
