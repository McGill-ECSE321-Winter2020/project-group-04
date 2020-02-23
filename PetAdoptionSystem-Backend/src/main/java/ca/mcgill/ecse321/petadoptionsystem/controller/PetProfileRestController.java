package ca.mcgill.ecse321.petadoptionsystem.controller;

import ca.mcgill.ecse321.petadoptionsystem.service.PetProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController

public class PetProfileRestController {

    @Autowired
    private PetProfileService service;

}
