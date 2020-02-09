package ca.mcgill.ecse321.petadoptionsystem.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.petadoptionsystem.model.Image;


@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {

	Image findImageById(int i);


}