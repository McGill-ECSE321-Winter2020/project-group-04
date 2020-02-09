package ca.mcgill.ecse321.petadoptionsystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.PetProfile;


public interface PetProfileRepository extends CrudRepository<PetProfile, Integer> {

<<<<<<< HEAD

    PetProfile findPetProfileByName(String name);
||||||| merged common ancestors
	
=======
	PetProfile findPetProfileByUsername(int id);

	PetProfile findPetProfileByID(int i);
>>>>>>> Users/Obaric

}