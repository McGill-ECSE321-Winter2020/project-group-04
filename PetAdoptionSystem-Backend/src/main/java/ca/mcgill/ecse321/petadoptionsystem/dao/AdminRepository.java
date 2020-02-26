package ca.mcgill.ecse321.petadoptionsystem.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.petadoptionsystem.model.Admin;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {
}