package ca.mcgill.ecse321.petadoptionsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;



public interface PetAdoptionSystemRepository extends CrudRepository<PetAdoptionSystem, Integer> {

	PetAdoptionSystem findPetAdoptionSystemByUsername(int id);

}