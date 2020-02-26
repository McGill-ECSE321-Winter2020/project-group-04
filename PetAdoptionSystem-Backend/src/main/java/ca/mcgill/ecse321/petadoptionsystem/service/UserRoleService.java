package ca.mcgill.ecse321.petadoptionsystem.service;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public abstract class UserRoleService {

    @Autowired
    AccountRepository accountRepository;

    /**
     * @param username account username
     * @return associated UserRole
     */
    @Transactional
    public UserRole getUserRoleByUsername(String username) throws IllegalArgumentException {
        Account account = accountRepository.findAccountByUsername(username);
        return account.getUserRole();
    }

}
