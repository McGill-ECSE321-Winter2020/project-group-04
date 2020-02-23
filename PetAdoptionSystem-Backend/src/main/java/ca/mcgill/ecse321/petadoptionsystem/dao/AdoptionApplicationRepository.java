package ca.mcgill.ecse321.petadoptionsystem.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.petadoptionsystem.model.AdoptionApplication;
import ca.mcgill.ecse321.petadoptionsystem.model.PetProfile;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;

@Repository
public interface AdoptionApplicationRepository extends CrudRepository<AdoptionApplication, Integer> {

	AdoptionApplication findAdoptionById(int id5);

	List<AdoptionApplication> findAdoptionApplicationByRegularUser(RegularUser regUser);

	List<AdoptionApplication> findAdoptionApplicationByPetProfile(PetProfile petprof);

	List<AdoptionApplication> findAdoptionApplicationByDate(Date postDate);


	// AdoptionApplication findAdoptionByRegularUserId(int id3);

	// AdoptionApplication findAdoptionByAdopter(RegularUser petAdopter);

	
}