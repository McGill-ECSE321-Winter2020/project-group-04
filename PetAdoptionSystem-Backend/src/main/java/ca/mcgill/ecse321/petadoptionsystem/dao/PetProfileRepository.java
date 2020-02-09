package ca.mcgill.ecse321.petadoptionsystem.dao;

import org.springframework.data.repository.CrudRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.PetProfile;


public interface PetProfileRepository extends CrudRepository<PetProfile, Integer> {

<<<<<<< HEAD
<<<<<<< HEAD

    PetProfile findPetProfileByName(String name);
||||||| merged common ancestors
	
=======
	PetProfile findPetProfileByUsername(int id);

	PetProfile findPetProfileByID(int i);
>>>>>>> Users/Obaric
||||||| merged common ancestors
	PetProfile findPetProfileByUsername(int id);

	PetProfile findPetProfileByID(int i);
=======
	PetProfile findPetProfileById(int i);
>>>>>>> 2f1b9b976b96eb065d00898d2b91d7013e5e893d

}