package ca.mcgill.ecse321.petadoptionsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;



public interface RegularUserRepository extends CrudRepository<RegularUser, Integer> {

	RegularUser findRegularUserById(int id);


}