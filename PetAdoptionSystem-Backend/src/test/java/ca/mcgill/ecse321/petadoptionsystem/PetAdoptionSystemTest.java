package ca.mcgill.ecse321.petadoptionsystem;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import ca.mcgill.ecse321.petadoptionsystem.dao.PetAdoptionSystemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import ca.mcgill.ecse321.petadoptionsystem.dao.PetAdoptionSystemRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class PetAdoptionSystemTest {
	@Autowired
	private PetAdoptionSystemRepository petAdoptionSystemRepository;

	@AfterEach
	public void clearDataBase(){
		petAdoptionSystemRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadSystem(){
		PetAdoptionSystem system= new PetAdoptionSystem();
		int myId = 123;
		system.setId(123);
		petAdoptionSystemRepository.save(system);

		assertNotNull(system);

		system = null;
		system = petAdoptionSystemRepository.findPetAdoptionSystemById(myId);
		assertEquals(myId, system.getId());
	}

}
