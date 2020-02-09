package ca.mcgill.ecse321.petadoptionsystem.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.petadoptionsystem.model.PetProfile;

@Repository
public interface PetProfileRepository extends CrudRepository<PetProfile, Integer> {

	PetProfile findPetProfileById(int i);

}