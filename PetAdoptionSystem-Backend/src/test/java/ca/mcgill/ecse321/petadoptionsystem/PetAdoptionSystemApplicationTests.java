package ca.mcgill.ecse321.petadoptionsystem;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SpringBootApplication
public class PetAdoptionSystemTests {
       	public static void main(String[] args) {
		SpringApplication.run(PetAdoptionSystemTests.class, args);
       	}

	@RequestMapping("/")
	public String greeting(){
		return "Hello world!";
	}
	    
}
