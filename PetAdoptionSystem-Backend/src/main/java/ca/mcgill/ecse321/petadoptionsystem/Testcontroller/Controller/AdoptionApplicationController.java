package ca.mcgill.ecse321.petadoptionsystem.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.petadoptionsystem.service.AdoptionApplicationService;
import ca.mcgill.ecse321.petadoptionsystem.service.PetProfileService;
import ca.mcgill.ecse321.petadoptionsystem.service.RegularUserService;
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
    private AdoptionApplicationService appservice;

    @Autowired
    private RegularUserService regservice;

    @Autowired
    private PetProfileService profileservice;

    @GetMapping(value = { "/applications", "/applications/" })
    public List<AdoptionApplicationDTO> getAllApplications() throws IllegalArgumentException {
        List<AdoptionApplicationDTO> appDtos = new ArrayList<>();
        for (AdoptionApplication app : appservice.getAllApplications()) {
            appDtos.add(convertToDto(app));
        }
        return appDtos;
    }

    @PostMapping(value = { "/apply", "/apply/" })
    public AdoptionApplicationDTO createApplication(@RequestParam Date postDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime postTime,
            @RequestParam(name = "applicant") RegularUserDTO regUserDTO,
            @RequestParam(name = "petProfile") PetProfileDTO petprofDTO) throws IllegalArgumentException {

        RegularUser ru = regservice.getRegularUserByUsername(regUserDTO.getUser().username);// regUserbyUsername method
                                                                                            // needs to be implemented
                                                                                            // in regservice class.
        PetProfile pp = profileservice.getPetProfileById(petprofDTO.getId()); // petProfile DTO from Jose can store the
                                                                              // // username of poster to make this
                                                                              // easier. needs to store id of petprofile

        AdoptionApplication a = appservice.createApplication(postDate, Time.valueOf(postTime), ru, pp);

        return convertToDto(a);
    }

    @GetMapping(value = { "/deleteApplication", "/deleteApplication/" })
    public boolean deleteApplication(@RequestParam(name = "application") AdoptionApplicationDTO appDTO,
            @RequestParam(name = "applicant") RegularUserDTO regUserDTO,
            @RequestParam(name = "petProfile") PetProfileDTO petprofDTO) throws IllegalArgumentException {

        if (appDTO == null) {
            throw new NullPointerException("An application is required to be deleted");
        }
        RegularUser ru = regservice.getRegularUserByUsername(regUserDTO.getUser().username);// regUserbyUsername method
        // needs to be implemented
        // in regservice class.
        PetProfile pp = profileservice.getPetProfileById(petprofDTO.getId()); // petProfile DTO from Jose can store the
        // // username of poster to make this
        // easi
        AdoptionApplication a = appservice.getApplication(ru, pp);
        Boolean result = appservice.deleteApplication(a);

        return result;
    }

    @GetMapping(value = { "/browse/applications/applicant", "/browse/applications/applicant/" })
    public List<AdoptionApplicationDTO> browseApplicationsOfApplicant(
            @RequestParam(name = "applicant") RegularUserDTO regUserDTO) throws IllegalArgumentException {

        if (regUserDTO == null) {
            throw new NullPointerException("A user is required to browse applications.");
        }

        RegularUser ru = regservice.getRegularUserByUsername(regUserDTO.getUser().username);// regUserbyUsername method
        // needs to be implemented
        // in regservice class.
        List<AdoptionApplicationDTO> appDtos = new ArrayList<>();
        for (AdoptionApplication app : appservice.getApplicationsByUser(ru)) {
            appDtos.add(convertToDto(app));
        }
        return appDtos;
    }

    @GetMapping(value = { "/browse/applications/petprofile", "/browse/applications/petprofile/" })
    public List<AdoptionApplicationDTO> browseApplicationsToPetProfile(
            @RequestParam(name = "petProfile") PetProfileDTO petprofDTO) throws IllegalArgumentException {

        if (petprofDTO == null) {
            throw new NullPointerException("An pet is required to see all applications.");
        }
        PetProfile pp = profileservice.getPetProfileById(petprofDTO.getId()); // petProfile DTO from Jose can store the
        // // username of poster to make this
        List<AdoptionApplicationDTO> appDtos = new ArrayList<>();
        for (AdoptionApplication app : appservice.getApplicationsByPetProfile(pp)) {
            appDtos.add(convertToDto(app));
        }
        return appDtos;
    }









    private AdoptionApplicationDTO convertToDto(AdoptionApplication app) {// TODO
        if (app == null) {
            throw new IllegalArgumentException("There is no such Application!");
        }
        RegularUserDTO regUser = convertToDto(app.getApplicant());
        PetProfileDTO petprof = convertToDto(app.getPetProfile());
        AdoptionApplicationDTO appDto = new AdoptionApplicationDTO(app.getId(), app.getPostDate(), app.getPostTime(),
                regUser, petprof);

        return appDto;
    }

    private PetProfileDTO convertToDto(PetProfile petProfile) {// TODO
        if (petProfile == null) {
            throw new IllegalArgumentException("There is no Pet Profile.");
        }
        PetProfileDTO profileDTO = new PetProfileDTO(petProfile.getPoster(), petProfile.getImage(),
                petProfile.getApplication(), petProfile.getName(), petProfile.getPetType(), petProfile.getBreed(),
                petProfile.getDescription(), petProfile.getId(), petProfile.getReasonForPosting(),
                petProfile.getPostDate(), petProfile.getPostTime());
        // This might have to be changed to convert image and poster into dtos before
        // creating pet perofile dtos
        return profileDTO;
    }

    private RegularUserDTO convertToDto(RegularUser applicant) {// TODO
        if (applicant == null) {
            throw new IllegalArgumentException("There is no Applicant.");
        }
        RegularUserDTO userDTO = new RegularUserDTO(applicant.getDonation(), applicant.getUser(), applicant.getName(),
                applicant.getApplication(), applicant.getHomeDescription(), applicant.getPhoneNumber());
        // This might have to be changed to convert image and poster into dtos before
        // creating user dtos
        return userDTO;
    }

}