package ca.mcgill.ecse321.petadoptionsystem.dto;


import ca.mcgill.ecse321.petadoptionsystem.model.AdoptionApplication;
import ca.mcgill.ecse321.petadoptionsystem.model.Image;
import ca.mcgill.ecse321.petadoptionsystem.model.PetType;
import ca.mcgill.ecse321.petadoptionsystem.model.UserRole;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

/**
 * Dto class
 */


public class PetProfileDTO {

    private UserRole poster;
    private Set<Image> image;
    private Set<AdoptionApplication> application;
    private String name;
    private PetType petType;
    private String breed;
    private String description;
    private int id;
    private String reasonForPosting;
    private Date postDate;
    private Time postTime;

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

    //Get everything from the DTO classes
    public PetProfileDTO(UserRole poster, Set<Image> image, Set<AdoptionApplication> application, String name, PetType petType,
                         String breed, String description, int id, String reasonForPosting, Date postDate, Time postTime){
        this.poster = poster;
        this.image = image;
        this.application = application;
        this.name = name;
        this.petType = petType;
        this.breed = breed;
        this.description = description;
        this.id = id;
        this.reasonForPosting = reasonForPosting;
        this.postDate = postDate;
        this.postTime = postTime;
    }

    public UserRole getPoster() {
        return this.poster;
    }

    public Set<Image> getImage() {
        return this.image;
    }

    public Set<AdoptionApplication> getApplication() {
        return this.application;
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

}
