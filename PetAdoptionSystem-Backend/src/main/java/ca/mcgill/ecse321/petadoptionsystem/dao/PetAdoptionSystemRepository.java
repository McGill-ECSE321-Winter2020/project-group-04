package ca.mcgill.ecse321.petadoptionsystem.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;


@Repository
public interface PetAdoptionSystemRepository extends CrudRepository<PetAdoptionSystem, Integer> {
    PetAdoptionSystem findPetAdoptionSystemById(int id);




}