package ca.mcgill.ecse321.petadoptionsystem.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import ca.mcgill.ecse321.petadoptionsystem.model.Donation;

import java.util.Set;


@Repository
public interface DonationRepository extends CrudRepository<Donation, Integer> {

	Donation findDonationById(int id);

	List<Donation> findDonationByUser_id(int id);

}