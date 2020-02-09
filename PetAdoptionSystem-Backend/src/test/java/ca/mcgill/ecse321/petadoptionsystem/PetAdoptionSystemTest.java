package ca.mcgill.ecse321.petadoptionsystem;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.PetAdoptionSystemRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

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
		system.setId(myId);
		petAdoptionSystemRepository.save(system);

		system = null;
		system = petAdoptionSystemRepository.findPetAdoptionSystemById(myId);
		assertEquals(123, system.getId());
	}

}
