package ca.mcgill.ecse321.petadoptionsystem.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.petadoptionsystem.dao.AdoptionApplicationRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.AdoptionApplication;
import ca.mcgill.ecse321.petadoptionsystem.model.PetProfile;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;

/**
 * @author eknuviad
 */
@Service
public class AdoptionApplicationService{

    @Autowired
    AdoptionApplicationRepository appRepository;

    @Transactional
    public AdoptionApplication createApplication(Date postDate, Time postTime, PetProfile petprof, RegularUser applicant){
// TODO add null checks ..need to check that application already exists "You have already applied to this pet". If needs to be changed, update post date and time
//need to check that profile is up to date. this overides existing application
        //input validation
        String error = "";
        if(postDate == null){
            error = error + "Application date cannot be empty.";
        }
        if(postTime == null){
            error = error + "Application time cannot be empty.";
        }
        if(petprof == null){
            error = error + "Pet Profile needs to be selected to apply.";
        }
        AdoptionApplication adoptApp = appRepository.findByApplicantAndPetProfile(applicant, petprof);
        if(adoptApp != null){
           error = error + "This application already exists.";

        }
        if (error.length()>0){
            throw new IllegalArgumentException(error);
        }
        adoptApp = new AdoptionApplication();
        adoptApp.setIsApproved(false);
        adoptApp.setApplicant(applicant);
        adoptApp.setPostDate(postDate);
        adoptApp.setPostTime(postTime);
        adoptApp.setPetProfile(petprof);

        appRepository.save(adoptApp);

        return adoptApp;    
    }

    @Transactional
    public boolean deleteApplication (int id){//needs to be changed, not this straight forward
//find by applicant and petprofile and get id and delete by id. this should take in an application
        AdoptionApplication adoptApp = appRepository.findAdoptionById(id);
        if(adoptApp == null){
            throw new NullPointerException("No such application exists.");
        }
        appRepository.deleteById(id);
        return true;
    }

    //return all applications in the system
    @Transactional
    public List<AdoptionApplication> getAllApplications(){
        return toList(appRepository.findAll());

    }
    
   
    @Transactional
    public List<AdoptionApplication> getApplicationsByUser(RegularUser regUser){
        List<AdoptionApplication> userApplications = appRepository.findByApplicant(regUser);

        return userApplications;
    }

    @Transactional
    public List<AdoptionApplication> getApplicationsByPetProfile(PetProfile petprof){
        List<AdoptionApplication> petprofApps = appRepository.findByPetProfile(petprof);

        return petprofApps;
    }

    // @Transactional  to be done in due time.
    // public List<AdoptionApplication> getApplicationByDate(Date postDate){
    //     List<AdoptionApplication> appsbyDate = appRepository.findAdoptionApplicationByDate(postDate);

    //     return appsbyDate;

    // }

    @Transactional
    public AdoptionApplication getApplication(int id){ //needs to be changed, not this straight forward
       //find by petprofile and petadopter. this is used in controller to get. Should take in adopter and petprofile
        AdoptionApplication adoptApp = appRepository.findAdoptionById(id);
        if(adoptApp == null){
            throw new NullPointerException("No such application exists.");
        }
        return adoptApp;
    }

    @Transactional
    public AdoptionApplication updateApplicationStatus(int id, boolean status){
        //findbyadopter and profile
        //TODO put in checks
        AdoptionApplication adoptApp = appRepository.findAdoptionById(id);
        adoptApp.setIsApproved(status);
        appRepository.save(adoptApp);
        return adoptApp;
    }



    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for(T t: iterable){
            resultList.add(t);
        }
        return resultList;
    }



}