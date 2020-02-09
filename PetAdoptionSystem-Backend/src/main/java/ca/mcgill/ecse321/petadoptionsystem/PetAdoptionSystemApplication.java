package ca.mcgill.ecse321.petadoptionsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class PetAdoptionSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetAdoptionSystemApplication.class, args);
	}

}
