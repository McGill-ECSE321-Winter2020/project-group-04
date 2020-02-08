package ca.mcgill.ecse321.petadoptionsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.petadoptionsystem.model.Donation;



public interface DonationRepository extends CrudRepository<Donation, Integer> {

	

}