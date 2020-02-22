package ca.mcgill.ecse321.petadoptionsystem.dto;

public class PetAdoptionSystemDto {

    private int systemID;

    public PetAdoptionSystemDto(int id){
        this.systemID = id;
    }

    public int getSystemID(){
        return systemID;
    }

}