package ca.mcgill.ecse321.petadoptionsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.petadoptionsystem.service.AdoptionApplicationService;
import ca.mcgill.ecse321.petadoptionsystem.dto.AdoptionApplicationDTO;
import ca.mcgill.ecse321.petadoptionsystem.dto.PetProfileDTO;
import ca.mcgill.ecse321.petadoptionsystem.dto.RegularUserDTO;
import ca.mcgill.ecse321.petadoptionsystem.model.AdoptionApplication;
import ca.mcgill.ecse321.petadoptionsystem.model.PetProfile;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;

@CrossOrigin(origins = "*")
@RestController
public class AdoptionApplicationController {

    @Autowired
    private AdoptionApplicationService service;

    @GetMapping(value = { "/applications", "/applications/" })
    public List<AdoptionApplicationDTO> getAllApplications() throws IllegalArgumentException {
        List<AdoptionApplicationDTO> appDtos = new ArrayList<>();
        for (AdoptionApplication app : service.getAllApplications()) {
            appDtos.add(convertToDto(app));
        }
        return appDtos;
    }

    private AdoptionApplicationDTO convertToDto(AdoptionApplication app) {//TODO
        if (app == null) {
            throw new IllegalArgumentException("There is no such Application!");
        }
        RegularUserDTO regUser = convertToDto(app.getApplicant());
        PetProfileDTO petprof = convertToDto(app.getPetProfile());
        AdoptionApplicationDTO appDto = new AdoptionApplicationDTO(app.getId(), app.getPostDate(), app.getPostTime(),
                regUser, petprof);

        return appDto;
    }

    private PetProfileDTO convertToDto(PetProfile petProfile) {//TODO
        return null;
    }

    private RegularUserDTO convertToDto(RegularUser applicant) {//TODO
        return null;
    }



}