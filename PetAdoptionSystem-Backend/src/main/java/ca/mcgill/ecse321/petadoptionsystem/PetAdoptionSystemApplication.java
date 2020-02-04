package ca.mcgill.ecse321.petadoptionsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class PetAdoptionSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetAdoptionSystemApplication.class, args);
	}

	@RequestMapping("/")
	public String greeting(){
		return "Hello World";
	}

}
