package ca.mcgill.ecse321.petadoptionsystem.controller;


import ca.mcgill.ecse321.petadoptionsystem.service.RegularUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class RegularUserRestController {

    @Autowired
    private RegularUserService service;

}
