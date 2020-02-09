package ca.mcgill.ecse321.petadoptionsystem.dao;

import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.petadoptionsystem.model.Account;


public interface AccountRepository extends CrudRepository<Account, Integer> {

	Account findAccountByUsername(String username);
	//Account findAccountByUsernameAndPetAdoptionSystem(String username, PetAdoptionSystem system);

}