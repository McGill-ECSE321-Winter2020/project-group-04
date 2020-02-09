package ca.mcgill.ecse321.petadoptionsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.petadoptionsystem.model.AdoptionApplication;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;

public interface AdoptionApplicationRepository extends CrudRepository<AdoptionApplication, Integer> {

	AdoptionApplication findAdoptionById(int id5);


	// AdoptionApplication findAdoptionByRegularUserId(int id3);

	// AdoptionApplication findAdoptionByAdopter(RegularUser petAdopter);

	
}