package ca.mcgill.ecse321.petadoptionsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.petadoptionsystem.model.AdoptionApplication;

public interface AdoptionApplicationRepository extends CrudRepository<AdoptionApplication, Integer> {

	
}