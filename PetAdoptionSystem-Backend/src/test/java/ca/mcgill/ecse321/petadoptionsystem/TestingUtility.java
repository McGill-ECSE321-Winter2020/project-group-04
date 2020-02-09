package ca.mcgill.ecse321.petadoptionsystem;

import ca.mcgill.ecse321.petadoptionsystem.model.*;

class TestingUtility {

    public static PetAdoptionSystem initPetAdoptionSystem(int id){
        PetAdoptionSystem pas = new PetAdoptionSystem();
        pas.setId(id);

        return pas;
    }

    public static Account initAccount(String username, String email, PetAdoptionSystem pas){
        Account act = new Account();

        act.setUsername(username);
        act.setEmail(email);
        act.setPetAdoptionSystem(pas);

        return act;
    }

    public static Admin initAdmin(int pkey, Account act, PetAdoptionSystem pas){
        Admin admin = new Admin();
        admin.setId(pkey);
        admin.setUser(act);
        act.setUserRole(admin);

        return admin;
    }

    public static RegularUser initRegularUser(int pkey, Account act, PetAdoptionSystem pas){
        RegularUser user = new RegularUser();
        user.setId(pkey);
        user.setUser(act);

        return user;
    }

    public static PetProfile initPetProfile(int pkey, RegularUser regUser, PetAdoptionSystem pas){
        PetProfile petProf = new PetProfile();
        petProf.setId(pkey);
        petProf.setPoster(regUser);

        return petProf;
    }


    public static AdoptionApplication initAdoptionApplication(int pkey, RegularUser regUser, PetProfile petProf){
        AdoptionApplication ada = new AdoptionApplication();

        ada.setApplicant(regUser);
        ada.setId(pkey);
        ada.setPetProfile(petProf);
        return ada;

    }

    public static Donation initDonation(int pkey, RegularUser regUser) {

        Donation don = new Donation();
        don.setId(pkey);
        don.setUser(regUser);

        return don;

    }

    public static Image initImage(int pkey, PetProfile petProf){
        Image img = new Image();
        img.setId(pkey);
        img.setPetProfile(petProf);

        return img;

    }

}