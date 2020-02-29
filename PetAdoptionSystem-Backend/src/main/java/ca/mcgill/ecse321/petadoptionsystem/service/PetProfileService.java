package ca.mcgill.ecse321.petadoptionsystem.service;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.PetProfileRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * @author joseantoniojijon
 */

@Service
public class PetProfileService {

    @Autowired
    PetProfileRepository petprofilerepository;
    @Autowired
    RegularUserRepository regularUserRepository;
    @Autowired
    AccountRepository accountRepository;


    /**
     *
     * @param breed breed of pet
     * @param description description of pet
     * @param name name of pet
     * @param pettype what kind of animal is it
     * @param posttime when is it posted
     * @param postdate when is it posted
     * @param username of the poster
     * @param reason why post it
     * @return the whole petprofile with attributes
     */

    @Transactional
    public PetProfile createPetProfile(String breed, String description, String name,
                                       PetType pettype, Time posttime, Date postdate, String username, String reason, boolean isAvailable)
            throws IllegalArgumentException {

        String error = "";

        //Checking the validity of the inputs

        if (name == null || name.length() == 0) error += "The name cannot be empty.\n";
        if (pettype == null ) error += "Must select a Pet-Type for the pet.\n";
        if (username == null) error += "The PetProfile must have a Poster username.\n";
        if (posttime == null) error += "A posting time must be inserted.\n";
        if (postdate == null) error += "A posting date must be inserted.\n";
        if (reason == null) error += "A reason for posting the pet for adoption must be inserted.\n";
        if (description == null || description.length() == 0) error += "A description of the pet must be inserted.\n";

        if (error.length() > 0) throw new IllegalArgumentException(error);

        Account account = accountRepository.findAccountByUsername(username);
        UserRole userRole = regularUserRepository.findRegularUserByUser(account);

        if (accountRepository.existsByUsername(username)) error += "No user associated with this username";
        if (error.length() > 0) throw new IllegalArgumentException(error);

        // Check if the user has another pet with that same name (not possible)

        if (petprofilerepository.existsByNameAndPoster(name, regularUserRepository.findRegularUserByUser(account)))
            error += "Cannot have two pets with the same exact name.\n" ;

        if (error.length() > 0) throw new IllegalArgumentException(error);


        PetProfile pet = new PetProfile();

        pet.setBreed(breed);

        pet.setDescription(description);

        pet.setName(name);

        pet.setPetType(pettype);

        pet.setPostTime(posttime);

        pet.setPostDate(postdate);

        pet.setPoster(userRole);

        pet.setReasonForPosting(reason);

        pet.setIsAvailable(isAvailable);

        petprofilerepository.save(pet);

        return pet;

    }


    /**
     *
     * @return returns the whole list of petprofiles
     */
    @Transactional
    public List<PetProfile> getAllPetProfiles(){
        return toList(petprofilerepository.findAll());
    }

    /**
     *
     * @param username account username of the poster to return all the pet profiles posted by the user
     * @return return all post done by this poster
     */
    @Transactional
    public List<PetProfile> getAllPetProfilesByUsername(String username){

        //Get the PosterId from the account username
        String error = "";
        if (accountRepository.existsByUsername(username)) error += "No user associated with this username";
        if (error.length() > 0) throw new IllegalArgumentException(error);

        Account account = accountRepository.findAccountByUsername(username);
        UserRole poster = regularUserRepository.findRegularUserByUser(account);

        return toList(petprofilerepository.findAllPetProfileByPoster(poster));

    }

    /**
     *
     * @param breed of the pet
     * @return get all pet profiles from this breed
     */
    @Transactional
    public List<PetProfile> getAllPetProfilesByBreed(String breed){

        String error = "";
        if (petprofilerepository.existsByBreed(breed)) error += "There is no such breed in our database";
        if (error.length() > 0) throw new IllegalArgumentException(error);

        return toList(petprofilerepository.findAllPetProfileByBreed(breed));
    }

    /**
     *
     * @param type of the pet
     * @return all pets from this type
     */
    @Transactional
    public List<PetProfile> getAllPetProfilesByPetType(PetType type){

        return toList(petprofilerepository.findAllPetProfileByPetType(type));
    }

    @Transactional
    public List<PetProfile> getAllPetProfilesByIsAvailable(boolean available){

//        String error = "";
//        if (petprofilerepository.existsByBreed(available)) error += "There is no such breed in our database";
//        if (error.length() > 0) throw new IllegalArgumentException(error);

        return toList(petprofilerepository.findAllPetProfileByIsAvailable(available));
    }



    /**
     *
     * @param username of the user
     * @param breed of the pet
     * @param description of the pet
     * @param reason for posting
     * @param type of the pet
     * @param name of the pet
     * @param isAvailable if it's already adopted should be boolean false
     * @return the updated petprofile
     */
    @Transactional
    public PetProfile updatePetProfile(String username, String breed, String description, String reason,
                                       PetType type, String name, Boolean isAvailable){

        //Get the PosterId from the account username

        String error = "error";
        if (accountRepository.existsByUsername(username)) error += "No user associated with username" + username;

        if (error.length() > 0) throw new IllegalArgumentException(error);

        if (petprofilerepository.existsByName(name)) error += "No pet of name " + name + "in the data base";
        if (error.length() > 0) throw new IllegalArgumentException(error);

        Account account = accountRepository.findAccountByUsername(username);
        UserRole poster = regularUserRepository.findRegularUserByUser(account);

        //Find the PetProfile with the posterid and the pet's name
        PetProfile pet = petprofilerepository.findPetProfileByNameAndPoster(name, poster);

        if (reason != null) {
            pet.setReasonForPosting(reason);
        }
        if (type != null) {
            pet.setPetType(type);
        }
        if (description != null) {
            pet.setDescription(description);
        }
        if (breed != null) {
            pet.setBreed(breed);
        }
        if (name != null) {
            pet.setName(name);
        }

        if (isAvailable != null) {
            pet.setIsAvailable(isAvailable);
        }

        petprofilerepository.save(pet);
        return pet;

    }

    /**
     *
     * @param username of the user
     * @param petname of the pet to be deleted
     */
    @Transactional
    public void deletePetProfile(String username, String petname){

        String error = "";
        if (accountRepository.existsByUsername(username)) error += "No user associated with username" + username;
        if (error.length() > 0) throw new IllegalArgumentException(error);

        if (petprofilerepository.existsByName(petname)) error += "No existing pet of name " + petname + "in the data base";
        if (error.length() > 0) throw new IllegalArgumentException(error);

        Account account = accountRepository.findAccountByUsername(username);
        UserRole posterid = regularUserRepository.findRegularUserByUser(account);

        PetProfile pet = petprofilerepository.findPetProfileByNameAndPoster(petname, posterid);
        petprofilerepository.delete(pet);

    }

    /**
     *
     * @param id id of petprofile
     * @return the petprofile
     */
    @Transactional
    public PetProfile getPetProfileById(int id){

        PetProfile pet = petprofilerepository.findPetProfileById(id);
        return pet;

    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
