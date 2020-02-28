package ca.mcgill.ecse321.petadoptionsystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Image {
    private PetProfile petProfile;

    @ManyToOne(optional = false)
    public PetProfile getPetProfile() {
        return this.petProfile;
    }

    public void setPetProfile(PetProfile petProfile) {
        this.petProfile = petProfile;
    }
    @Id @GeneratedValue
    private int id;

    public void setId(int value) {
        this.id = value;
    }

    @Id
    public int getId() {
        return this.id;
    }

    @Lob
    private byte[] data;
    
    private String imageName;

    public String getImageName(){
        return this.imageName;
    }
    public void setImageName(String imageName){
        this.imageName = imageName;
    }
    private String description;

    public void setDescription(String value) {
        this.description = value;
    }

    public String getDescription() {
        return this.description;
    }

    private int sizeX;

    public void setSizeX(int value) {
        this.sizeX = value;
    }

    public int getSizeX() {
        return this.sizeX;
    }

    
    private int sizeY;


    public void setSizeY(int value) {
        this.sizeY = value;
    }

    public int getSizeY() {
        return this.sizeY;
    }
    private String imageType;
    
    private String filePath;

    public void setFilePath(String value) {
        this.filePath = value;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}
