package ca.mcgill.ecse321.petadoptionsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.petadoptionsystem.model.Account;


public interface AccountRepository extends CrudRepository<Account, Integer> {

	

}