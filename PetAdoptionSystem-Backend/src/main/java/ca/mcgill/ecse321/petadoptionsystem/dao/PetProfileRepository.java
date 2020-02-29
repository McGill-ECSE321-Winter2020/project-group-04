package ca.mcgill.ecse321.petadoptionsystem.dao;

import ca.mcgill.ecse321.petadoptionsystem.model.PetType;
import ca.mcgill.ecse321.petadoptionsystem.model.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.petadoptionsystem.model.PetProfile;

import java.util.List;

@Repository
public interface PetProfileRepository extends CrudRepository<PetProfile, Integer> {

	PetProfile findPetProfileById(int i);
	List<PetProfile> findAllPetProfileByPoster(UserRole poster);
	List<PetProfile> findAllPetProfileByBreed(String breed);
	List<PetProfile> findAllPetProfileByPetType(PetType id);
	boolean existsByNameAndPoster(String name, UserRole id);
	PetProfile findPetProfileByNameAndPoster(String name, UserRole id);

    boolean existsByBreed(String breed);

	boolean existsByName(String username);

	List<PetProfile> findAllPetProfileByIsAvailable(boolean available);

}