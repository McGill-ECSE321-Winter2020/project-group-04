package ca.mcgill.ecse321.petadoptionsystem.dto;


public class AdminDTO {
    private int id;
    private String user;

    /**
     * @param id admin id
     */
    public AdminDTO(int id){
        this.id = id;
    }

    public String getUser() {
        return this.user;
    }
}
