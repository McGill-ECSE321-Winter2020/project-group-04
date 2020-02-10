package ca.mcgill.ecse321.petadoptionsystem.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.petadoptionsystem.model.AdoptionApplication;

@Repository
public interface AdoptionApplicationRepository extends CrudRepository<AdoptionApplication, Integer> {

	AdoptionApplication findAdoptionById(int id5);


	// AdoptionApplication findAdoptionByRegularUserId(int id3);

	// AdoptionApplication findAdoptionByAdopter(RegularUser petAdopter);

	
}