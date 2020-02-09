package ca.mcgill.ecse321.petadoptionsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.petadoptionsystem.model.Admin;


public interface AdminRepository extends CrudRepository<Admin, Integer> {
	Admin findAdminById(int id);

}