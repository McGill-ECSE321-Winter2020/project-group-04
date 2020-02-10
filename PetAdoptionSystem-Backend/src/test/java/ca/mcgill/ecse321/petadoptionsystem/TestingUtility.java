package ca.mcgill.ecse321.petadoptionsystem;

import ca.mcgill.ecse321.petadoptionsystem.model.*;

/**
 * Helper methods for testing purpose
 * 
 * @author Ousmane Baricisse
 */
class TestingUtility {
    /**
     * Helper method to return a petAdoption Object
     * 
     * @param id
     * @return PetAdoptionSystem
     */
    public static PetAdoptionSystem initPetAdoptionSystem(int id) {
        PetAdoptionSystem pas = new PetAdoptionSystem();
        pas.setId(id);

        return pas;
    }

    public static Account initAccount(String username, String email, PetAdoptionSystem pas) {
        Account act = new Account();

        act.setUsername(username);
        act.setEmail(email);
        act.setPetAdoptionSystem(pas);

        return act;
    }

    /**
     * Helper method that create admin object based on primarykey, user account, and
     * petadoptionsystem
     * 
     * @param pkey
     * @param act
     * @param pas
     * @return
     */

    public static Admin initAdmin(int pkey, Account act, PetAdoptionSystem pas) {
        Admin admin = new Admin();
        admin.setUser(act);
        admin.setId(pkey);

        return admin;
    }

    /**
     * Helper method to return a regular user account
     * 
     * @param pkey
     * @param act
     * @param pas
     * @return
     */
    public static RegularUser initRegularUser(int pkey, Account act, PetAdoptionSystem pas) {
        RegularUser user = new RegularUser();
        user.setId(pkey);
        user.setUser(act);
        act.setUserRole(user);

        return user;
    }

    /**
     * Helper method to return PetProfile
     * 
     * @param pkey
     * @param regUser
     * @param pas
     * @return
     */
    public static PetProfile initPetProfile(int pkey, RegularUser regUser, PetAdoptionSystem pas) {

        PetProfile petProf = new PetProfile();
        petProf.setId(pkey);
        petProf.setPoster(regUser);

        return petProf;
    }

    /**
     * Helper method for adoption application
     * 
     * @param pkey
     * @param regUser
     * @param petProf
     * @return
     */
    public static AdoptionApplication initAdoptionApplication(int pkey, RegularUser regUser, PetProfile petProf) {
        AdoptionApplication ada = new AdoptionApplication();

        ada.setApplicant(regUser);
        ada.setId(pkey);
        ada.setPetProfile(petProf);
        return ada;

    }

    /**
     * Helper method to return Donation object
     * 
     * @param pkey
     * @param regUser
     * @return
     */
    public static Donation initDonation(int pkey, RegularUser regUser) {

        Donation don = new Donation();
        don.setId(pkey);
        don.setUser(regUser);

        return don;

    }

    /**
     * Helper method to return a Image object
     * 
     * @param pkey
     * @param petProf
     * @return
     */
    public static Image initImage(int pkey, PetProfile petProf) {
        Image img = new Image();
        img.setId(pkey);
        img.setPetProfile(petProf);

        return img;

    }

}