package ca.mcgill.ecse321.petadoptionsystem.controller;


import ca.mcgill.ecse321.petadoptionsystem.dto.PetProfileDTO;
import ca.mcgill.ecse321.petadoptionsystem.dto.RegularUserDTO;
import ca.mcgill.ecse321.petadoptionsystem.model.PetProfile;
import ca.mcgill.ecse321.petadoptionsystem.service.RegularUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class RegularUserRestController {

    @Autowired
    private RegularUserService regularUserService;

    @PostMapping( value = {"/regularuser", "/regularuser/"})
    public


    private RegularUserDTO convertToDto(RegularUser pet) {
        if (pet == null) {
            throw new IllegalArgumentException("There is no such Pet!");
        }

        PetProfileDTO petDto = new PetProfileDTO(pet.getPoster(), pet.getImage(), pet.getApplication(), pet.getName(), pet.getPetType(),
                pet.getBreed(), pet.getDescription(), pet.getId(), pet.getReasonForPosting(), pet.getPostDate(), pet.getPostTime(), pet.isIsAvailable());

        return petDto;
    }

}
