package ca.mcgill.ecse321.petadoptionsystem.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.AdoptionApplicationRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.PetProfileRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.AdoptionApplication;
import ca.mcgill.ecse321.petadoptionsystem.model.PetProfile;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;

/**
 * @author eknuviad
 */
@Service
public class AdoptionApplicationService {

    @Autowired
    AdoptionApplicationRepository appRepository;

    @Autowired
    PetProfileRepository ppRepository;
    @Autowired
    AccountRepository acRepository;
    @Autowired
    RegularUserRepository regRepository;

    /**
     * Method to create a new Application
     * 
     * @param postDate
     * @param postTime
     * @param petprof
     * @param applicant
     * @return created application
     */
    @Transactional
    public AdoptionApplication createApplication(Date postDate, Time postTime, String username, int petId) {
        String error = "";
        if (postDate == null) {
            error = error + "Application date cannot be empty.";
        }
        if (postTime == null) {
            error = error + "Application time cannot be empty.";
        }
        if (username == null || username.trim().length() == 0) {
            error = error + "The username cannot be empty or have spaces.";
        }
        PetProfile petprof = ppRepository.findPetProfileById(petId);
        Account ac = acRepository.findAccountByUsername(username);

        if (username != null && ac == null) {
            error = error + "No account associated with this username.";
        }
        RegularUser ru = regRepository.findRegularUserByUser(ac);

        AdoptionApplication adoptApp = appRepository.findByApplicantAndPetProfile(ru, petprof);
        if (adoptApp != null) {
            error = error + "This application already exists.";
        }
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        adoptApp = new AdoptionApplication();
        adoptApp.setIsApproved(false);
        adoptApp.setIsConfirmed(false);
        adoptApp.setApplicant(ru);
        adoptApp.setPostDate(postDate);
        adoptApp.setPostTime(postTime);
        adoptApp.setPetProfile(petprof);

        appRepository.save(adoptApp);

        return adoptApp;
    }

    /**
     * 
     * @param adoptApp
     * @return if delete is successful
     */
    @Transactional
    public boolean deleteApplication(AdoptionApplication adoptApp) {
        int id = adoptApp.getId();
        AdoptionApplication deleteApp = appRepository.findAdoptionById(id);
        if (deleteApp == null) {
            throw new NullPointerException("No such application exists to be deleted.");
        }
        appRepository.deleteById(id);
        return true;
    }

    /**
     * 
     * @return list of all applications in system
     */
    @Transactional
    public List<AdoptionApplication> getAllApplications() {
        return toList(appRepository.findAll());

    }

    /**
     * Method to get all applications made by a user
     * 
     * @param regUser
     * @return list of applications of a user
     */
    @Transactional
    public List<AdoptionApplication> getApplicationsByUser(String username) {
        String error = "";
        if (username == null || username.trim().length() == 0) {
            error = error + "The username cannot be empty or have spaces.";
        }
        Account ac = acRepository.findAccountByUsername(username);
        if (ac == null) {
            error = error + "No account associated with this username.";
        }
    
        RegularUser ru = regRepository.findRegularUserByUser(ac);
        List<AdoptionApplication> userApplications = appRepository.findByApplicant(ru);

        return userApplications;
    }

    /**
     * Method to return all applications made to a pet profile
     * 
     * @param petprof
     * @return list of applications to a petprofile
     */
    @Transactional
    public List<AdoptionApplication> getApplicationsByPetProfile(int petId) {// name of pet and string of username

        PetProfile petprof = ppRepository.findPetProfileById(petId);
        if (petprof == null) {
            throw new NullPointerException("Pet profile is required to get its application.");
        }
        List<AdoptionApplication> petprofApps = appRepository.findByPetProfile(petprof);

        return petprofApps;
    }

    /**
     * Method to find an application
     * 
     * @param adopter
     * @param petprof
     * @return a specific application to pet profile
     */
    @Transactional
    public AdoptionApplication getAppbyAdopterAndPetProfile(String username, int petId) {
        String error = "";
        Account ac = acRepository.findAccountByUsername(username);
        if (ac == null) {
            error = error + "No account associated with this username.";
        }
        PetProfile petprof = ppRepository.findPetProfileById(petId);
        if (petprof == null) {
            throw new NullPointerException("Pet profile is required to get its application.");
        }
        if (error.length() > 0) {
            throw new IllegalArgumentException(error);
        }
        RegularUser ru = regRepository.findRegularUserByUser(ac);
        AdoptionApplication adoptApp = appRepository.findByApplicantAndPetProfile(ru, petprof);
        if (adoptApp == null) {
            throw new NullPointerException("No such application exists.");
        }

        return adoptApp;
    }

    /**
     * Get an application by id
     * 
     * @param id
     * @return adoption application
     */
    @Transactional
    public AdoptionApplication getApplicationbyId(int id) {
        AdoptionApplication adoptApp = appRepository.findAdoptionById(id);
        if (adoptApp == null) {
            throw new NullPointerException("No such application exists.");
        }
        return adoptApp;
    }

    /**
     * Method to update the status of an application
     * 
     * @param adoptApp
     * @param isApproved
     * @param isConfirmed
     * @return an updated application
     */
    @Transactional
    public AdoptionApplication updateApplicationStatus(AdoptionApplication adoptApp, boolean isApproved,
            boolean isConfirmed) {
        if (adoptApp == null) {
            throw new NullPointerException("An application is required to be updated.");
        }
        int id = adoptApp.getId();
        AdoptionApplication updateApp = appRepository.findAdoptionById(id);
        if (updateApp == null) {
            throw new NullPointerException("This application does not exist.");
        }
        boolean approvalStatus = updateApp.isIsApproved();
        if ((approvalStatus == false) && (isConfirmed == true)) {
            throw new IllegalArgumentException("This application has not been approved.");
        }
        updateApp.setIsApproved(isApproved);
        updateApp.setIsConfirmed(isConfirmed);
        appRepository.save(updateApp);
        return adoptApp;
    }

    private <T> List<T> toList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

}