package ca.mcgill.ecse321.petadoptionsystem.dao;

import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;
import ca.mcgill.ecse321.petadoptionsystem.model.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.petadoptionsystem.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

	Account findAccountByUsername(String username);
	Account findAccountByUserRole(String usrR);


	

}