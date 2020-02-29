package ca.mcgill.ecse321.petadoptionsystem.dao;

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
     * @param act
     * @param pas
     * @return
     */
    public static Admin initAdmin(Account act, PetAdoptionSystem pas) {
        Admin admin = new Admin();
        admin.setUser(act);
        act.setUserRole(admin);
        return admin;
    }

    /**
     * Helper method to return a regular user account
     *
     * @param act
     * @param pas
     * @return
     */
    public static RegularUser initRegularUser(Account act, PetAdoptionSystem pas) {
        RegularUser user = new RegularUser();
        user.setUser(act);
        act.setUserRole(user);

        return user;
    }

    /**
     * Helper method to return PetProfile
     *
     * @param regUser
     * @param pas
     * @return
     */
    public static PetProfile initPetProfile(RegularUser regUser, PetAdoptionSystem pas) {

        PetProfile petProf = new PetProfile();
        petProf.setIsAvailable(true);
        petProf.setPoster(regUser);

        return petProf;
    }

    /**
     * Helper method for adoption application
     *
     * @param regUser
     * @param petProf
     * @return
     */
    public static AdoptionApplication initAdoptionApplication(RegularUser regUser, PetProfile petProf) {
        AdoptionApplication ada = new AdoptionApplication();

        ada.setApplicant(regUser);
        ada.setPetProfile(petProf);
        return ada;

    }

    /**
     * Helper method to return Donation object
     *
     * @param regUser
     * @return
     */
    public static Donation initDonation(int pkey, RegularUser regUser) {

        Donation don = new Donation();
        don.setId(pkey);
        don.setUser(regUser);

        return don;

    }

}