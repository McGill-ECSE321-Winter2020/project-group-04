package ca.mcgill.ecse321.petadoptionsystem.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.petadoptionsystem.model.AdoptionApplication;
import ca.mcgill.ecse321.petadoptionsystem.model.PetProfile;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;

@Repository
public interface AdoptionApplicationRepository extends CrudRepository<AdoptionApplication, Integer> {

	AdoptionApplication findAdoptionById(int appid);

	List<AdoptionApplication> findByApplicant(RegularUser regUser);

	List<AdoptionApplication> findByPetProfile(PetProfile petprof);

	AdoptionApplication findByApplicantAndPetProfile(RegularUser petAdopter, PetProfile petProf);

	// List<AdoptionApplication> findListByApplicantAndPetProfile(RegularUser petAdopter, PetProfile petProf);	
	
}