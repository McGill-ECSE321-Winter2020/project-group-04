package ca.mcgill.ecse321.petadoptionsystem.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.petadoptionsystem.model.Image;



public interface ImageRepository extends CrudRepository<Image, Integer> {

	Image findImageById(int i);

	

}