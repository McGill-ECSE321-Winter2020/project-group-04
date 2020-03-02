package ca.mcgill.ecse321.petadoptionsystem.dto;

public class UserRoleDTO {
    private int id;
    private String client;

    /**
     * @param id UserRole id
     */
    public UserRoleDTO(int id){
        this.id = id;
    }

    public String getClient() {
        return this.client;
    }

}
