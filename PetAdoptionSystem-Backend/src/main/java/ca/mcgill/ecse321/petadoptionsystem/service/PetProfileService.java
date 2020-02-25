package ca.mcgill.ecse321.petadoptionsystem.service;

import ca.mcgill.ecse321.petadoptionsystem.dao.PetProfileRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.PetProfile;
import ca.mcgill.ecse321.petadoptionsystem.model.PetType;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import static org.hibernate.internal.util.collections.ArrayHelper.toList;

@Service
public class PetProfileService {

    @Autowired
    PetProfileRepository petprofilerepository;

    /**
     *
     * @param breed breed of pet
     * @param description description of pet
     * @param name name of pet
     * @param pettype what kind of animal is it
     * @param posttime when is it posted
     * @param postdate when is it posted
     * @param poster who posts it
     * @param reason why post it
     * @return the whole petprofile with attributes
     */
    @Transactional
    public PetProfile createPetProfile(String breed, String description, String name, PetType pettype, Time posttime, Date postdate, RegularUser poster, String reason){

        PetProfile pet = new PetProfile();
        pet.setBreed(breed);
        pet.setDescription(description);
        pet.setName(name);
        pet.setPetType(pettype);
        pet.setPostTime(posttime);
        pet.setPostDate(postdate);
        pet.setPoster(poster);
        pet.setReasonForPosting(reason);

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
     * @param id id of the poster, to return all the pet profiles posted by the user
     * @return return all post done by this poster
     */
    @Transactional
    public List<PetProfile> getAllPetProfilesByPosterId(RegularUser id){

            return toList(petprofilerepository.findAllPetProfileByPoster(id));
        }

    /**
     *
     * @param breed of the pet
     * @return get all pet profiles from this breed
     */
    @Transactional
    public List<PetProfile> getAllPetProfilesByBreed(String breed){

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

    /**
     *
     * @param id of pet you want to change name
     * @param breed change breed
     * @param description change description
     * @param reason change reason for posting
     * @param type change pet type
     * @param name change name
     * @return new pet profile
     */
    @Transactional
    public PetProfile updatePetProfile(int id, String breed, String description, String reason, PetType type, String name){

        PetProfile pet = petprofilerepository.findPetProfileById(id);
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
        petprofilerepository.save(pet);
        return pet;

    }

    /**
     *
     * @param id id of pet profile to be deleted
     */
    @Transactional
    //Look for Account username, PetProfile name
    //Get Id from UserRole, using the username
    public void deletePetProfile(int id){

        PetProfile pet = petprofilerepository.findPetProfileById(id);
        deletePetProfile(id);
        return;

    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }
}
