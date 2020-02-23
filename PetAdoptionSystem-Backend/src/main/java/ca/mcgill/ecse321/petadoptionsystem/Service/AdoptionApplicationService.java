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
// TODO add null checks
        AdoptionApplication adoptApp = new AdoptionApplication();
        adoptApp.setIsApproved(false);
        adoptApp.setApplicant(applicant);
        adoptApp.setPostDate(postDate);
        adoptApp.setPostTime(postTime);
        adoptApp.setPetProfile(petprof);

        appRepository.save(adoptApp);

        return adoptApp;    
    }

    @Transactional
    public boolean deleteApplication (int id){

        AdoptionApplication adoptApp = appRepository.findAdoptionById(id);
        if(adoptApp == null){
            throw new NullPointerException("No such document exists.");
        }
        appRepository.deleteById(id);
        return true;
    }

    //return all applications in the system
    @Transactional
    public List<AdoptionApplication> getAllApplications(){
        return toList(appRepository.findAll());

    }
    
    //return all applications of a particular user
    @Transactional
    public List<AdoptionApplication> getApplicationsByUser(RegularUser regUser){
        List<AdoptionApplication> userApplications = appRepository.findAdoptionApplicationByRegularUser(regUser);

        return userApplications;
    }

    @Transactional
    public List<AdoptionApplication> getApplicationsByPetProfile(PetProfile petprof){
        List<AdoptionApplication> petprofApps = appRepository.findAdoptionApplicationByPetProfile(petprof);

        return petprofApps;
    }

    @Transactional
    public List<AdoptionApplication> getApplicationByDate(Date postDate){
        List<AdoptionApplication> appsbyDate = appRepository.findAdoptionApplicationByDate(postDate);

        return appsbyDate;

    }

    @Transactional
    public AdoptionApplication getApplication(int id){
        AdoptionApplication adoptApp = appRepository.findAdoptionById(id);
        if(adoptApp == null){
            throw new NullPointerException("No such document exists.");
        }
        return adoptApp;
    }

    @Transactional
    public AdoptionApplication updateApplicationStatus(int id, boolean status){
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