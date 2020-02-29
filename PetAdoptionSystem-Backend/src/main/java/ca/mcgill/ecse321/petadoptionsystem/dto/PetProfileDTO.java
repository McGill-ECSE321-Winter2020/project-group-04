package ca.mcgill.ecse321.petadoptionsystem.dto;

import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.*;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

/**
 * Dto class
 */


public class PetProfileDTO {

    public UserRole poster;
    public HashSet<String> images;
    public Set<AdoptionApplication> application;
    public String name;
    public PetType petType;
    public String breed;
    public String description;
    public int id;
    public String reasonForPosting;
    public Date postDate;
    public Time postTime;
    private boolean isAvailable;

    /**
     *
     * @param poster from user
     * @param image from image
     * @param application id
     * @param name of the pet
     * @param petType enum class
     * @param breed attribute of pet
     * @param description of petprofile
     * @param id of pet
     * @param reasonForPosting for the pet
     * @param postDate date
     * @param postTime time
     */
    public PetProfileDTO(UserRole poster, HashSet<String> images, Set<AdoptionApplication> application, String name, PetType petType,
                         String breed, String description, int id, String reasonForPosting, Date postDate, Time postTime, boolean isAvailable){
        this.poster = poster;
        this.images = images;
        this.application = application;
        this.name = name;
        this.petType = petType;
        this.breed = breed;
        this.description = description;
        this.id = id;
        this.reasonForPosting = reasonForPosting;
        this.postDate = postDate;
        this.postTime = postTime;
        this.isAvailable = isAvailable;
    }

    public UserRole getPoster() {
        return this.poster;
    }

    public HashSet<String> getImages() {
        return this.images;
    }

    public String getName() {
        return this.name;
    }

    public PetType getPetType() {
        return this.petType;
    }

    public String getBreed() {
        return this.breed;
    }

    public String getDescription() {
        return this.description;
    }

    public int getId() {
        return this.id;
    }

    public String getReasonForPosting() {
        return this.reasonForPosting;
    }

    public Date getPostDate() {
        return this.postDate;
    }

    public Time getPostTime() {
        return this.postTime;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }


}
