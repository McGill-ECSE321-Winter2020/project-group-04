package ca.mcgill.ecse321.petadoptionsystem.dto;


public class AdminDTO {
    public int id;
    public String client;

    /**
     * @param id admin id
     */
    public AdminDTO(int id, String client){
        this.id = id;
        this.client = client;
    }

    public String getClient() {
        return this.client;
    }

    public int getId(){
        return this.id;
    }
}
