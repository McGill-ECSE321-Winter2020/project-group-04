package ca.mcgill.ecse321.petadoptionsystem;

import ca.mcgill.ecse321.petadoptionsystem.dao.*;
import ca.mcgill.ecse321.petadoptionsystem.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(SpringExtension.class)

@SpringBootTest
public class PetProfileTest {

    @Autowired
    private PetProfileRepository petProfilerepository;

    @AfterEach
    public void clearDatabase() {

       petProfilerepository.deleteAll();

    }



    @Test
    public void TestPersistancePetProfile() {

        //Test for name attribute
        PetProfile petprofile = new PetProfile();

        String name = "Doggy";
        petprofile.setName(name);
        petprofile.setId(5);


        petProfilerepository.save(petprofile);


        petprofile = petProfilerepository.findPetProfileByName(name);
        assertNotNull(petprofile);
        assertEquals(name, petprofile.getName());
        
    }
}

