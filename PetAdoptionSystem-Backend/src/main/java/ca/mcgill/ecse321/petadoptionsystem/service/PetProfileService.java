package ca.mcgill.ecse321.petadoptionsystem.service;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.PetProfileRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;
import ca.mcgill.ecse321.petadoptionsystem.exception.ImageStorageException;
import ca.mcgill.ecse321.petadoptionsystem.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author joseantoniojijon
 * @author Ousmane Baricisse "Mainly worked on the image part since we decided to include images as attribute in this class"
 * 
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
     * @param images why post it
     * @return the whole petprofile with attributes
     */
    @Transactional
    public PetProfile createPetProfile(String breed, String description, String name,
                                       PetType pettype, Time posttime, Date postdate, String username, String reason, boolean isAvailable, HashSet<String> images)
            throws IllegalArgumentException {

        String error = "";

        //Checking the validity of the inputs

        if (username == null || username.trim().length() == 0)
            error += "The username cannot be empty or have spaces.\n";
        if (name == null || name.length() == 0)
            error += "The name cannot be empty.\n";
        if (pettype == null)
            error += "Must select a Pet-Type for the pet.\n";
        if (username == null)
            error += "The PetProfile must have an existing Poster username.\n";
        if (posttime == null)
            error += "A posting time must be inserted.\n";
        if (postdate == null)
            error += "A posting date must be inserted.\n";
        if (reason == null)
            error += "A reason for posting the pet for adoption must be inserted.\n";
        if (description == null || description.length() == 0)
            error += "A description of the pet must be inserted.\n";
        if(images==null || images.size()==0) 
            throw new ImageStorageException("You need to submit at least one image url");
        if (error.length() > 0){
            throw new IllegalArgumentException(error);
        } 
        username = username.trim();
        if (!accountRepository.existsByUsername(username))
           error += "No user associated with this username" + username;
       
        if (error.length() > 0) throw new IllegalArgumentException(error);
         Account account = accountRepository.findAccountByUsername(username.trim());
         UserRole userRole = account.getUserRole();

        // // Check if the user has another pet with that same name (not possible)

         if (petprofilerepository.existsByNameAndPoster(name, regularUserRepository.findRegularUserByClient(account)))
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
        pet.setImages(images);

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
        if (username == null || username.trim().length() == 0)
            error += "The username cannot be empty or have spaces.\n";
        if (!accountRepository.existsByUsername(username))
            error += "No user associated with this username";
        if (error.length() > 0) throw new IllegalArgumentException(error);

        Account account = accountRepository.findAccountByUsername(username);
        UserRole poster = regularUserRepository.findRegularUserByClient(account);

        if (petprofilerepository.findAllPetProfileByPoster(poster) == null )
            error += "No Pet Profiles associated with this username";
        if (error.length() > 0) throw new IllegalArgumentException(error);

        return toList(petprofilerepository.findAllPetProfileByPoster(poster));

    }

    /**
     *
     * @param bREED_KEY of the pet
     * @return get all pet profiles from this breed
     */
    @Transactional
    public List<PetProfile> getAllPetProfilesByBreed(String bREED_KEY){

        String error = "";
        if (!petprofilerepository.existsByBreed(bREED_KEY))
            error += "There is no such breed in our database";

        if (error.length() > 0) throw new IllegalArgumentException(error);

        if (petprofilerepository.findAllPetProfileByBreed(bREED_KEY) == null )
            error += "No Pet Profiles associated with this breed";

        if (error.length() > 0) throw new IllegalArgumentException(error);

        return toList(petprofilerepository.findAllPetProfileByBreed(bREED_KEY));
    }

    /**
     *
     * @param type of the pet
     * @return all pets from this type
     */
    @Transactional
    public List<PetProfile> getAllPetProfilesByPetType(PetType type){

        String error = "";
        if (petprofilerepository.findAllPetProfileByPetType(type) == null )
            error += "No Pet Profiles associated with this Pet Type";
        if (error.length() > 0) throw new IllegalArgumentException(error);

        return toList(petprofilerepository.findAllPetProfileByPetType(type));
    }

    /**
     *
     * @param available
     * @return
     */
    @Transactional
    public List<PetProfile> getAllPetProfilesByIsAvailable(boolean available){

        String error = "";
        if (petprofilerepository.findAllPetProfileByIsAvailable(available) == null )
            error += "No Pet Profiles with the selected availability";
        if (error.length() > 0) throw new IllegalArgumentException(error);

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
                                       PetType type, String name, Boolean isAvailable, HashSet<String> images){

        //Get the PosterId from the account username

        String error = "";
        if (username == null || username.trim().length() == 0)
            error += "The username cannot be empty or have spaces.\n";

        if (!accountRepository.existsByUsername(username))
            error += "No user associated with username" + username;

        if (error.length() > 0) throw new IllegalArgumentException(error);
      
        if (error.length() > 0) throw new IllegalArgumentException(error);

        if(images==null || images.size()==0) 
           throw new ImageStorageException("You need to submit at least one image url");

        Account account = accountRepository.findAccountByUsername(username);
        UserRole poster = regularUserRepository.findRegularUserByClient(account);
                               
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
        if(images.size() > 0)
            pet.setImages(images);
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
        if (username == null || username.trim().length() == 0)
            error += "The username cannot be empty or have spaces.\n";

        if (!accountRepository.existsByUsername(username))
            error += "No user associated with username" + username;
        if (error.length() > 0) throw new IllegalArgumentException(error);

        if (!petprofilerepository.existsByName(petname))
            error += "No existing pet of name " + petname + "in the data base";
        if (error.length() > 0) throw new IllegalArgumentException(error);

        Account account = accountRepository.findAccountByUsername(username);
        UserRole posterid = regularUserRepository.findRegularUserByClient(account);

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

        String error = "";
        if (!petprofilerepository.existsById(id))
            error += "No PetProfile associated with this id:" + id;
        if (error.length() > 0) throw new IllegalArgumentException(error);
        
        return petprofilerepository.findPetProfileById(id);

    }

    /**
     *
     * @param iterable
     * @param <T>
     * @return
     */
    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
