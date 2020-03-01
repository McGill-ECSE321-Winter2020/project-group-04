package ca.mcgill.ecse321.petadoptionsystem.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.PetProfileRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.PetProfile;
import ca.mcgill.ecse321.petadoptionsystem.model.PetType;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;
import ca.mcgill.ecse321.petadoptionsystem.model.UserRole;

@ExtendWith(MockitoExtension.class)
public class TestPetProfileService {

    @Mock
    private PetProfileRepository petprofilerepository;

    @Mock
    private RegularUserRepository regularUserRepository;
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private PetProfileService petProfileService;

    private final String BREED_KEY = "chihuahua";
    private final String NAME = "PATO";
    private final boolean ISAVAILABLE = true;
    private final String DESCRIPTION = "cute pato. I love him";
    private final String REASON = "I had to change location";
    private final PetType PETTYPE = PetType.CAT;
    private HashSet<String> images = new HashSet<String>();
    private static final String USERNAME = "TRAE";
    private static final String HOUSE = "Condo";
    private static final int PHONE = 123;

    private final UserRole user = new RegularUser();

    @BeforeEach
    public void setMockOutput() {
        lenient().when(petprofilerepository.findAllPetProfileByBreed(anyString()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (invocation.getArgument(0).equals(BREED_KEY)) {
                        PetProfile petProf = new PetProfile();
                        petProf.setBreed(BREED_KEY);
                        petProf.setName(NAME);
                        petProf.setDescription(DESCRIPTION);
                        petProf.setReasonForPosting(REASON);
                        petProf.setIsAvailable(ISAVAILABLE);
                        images.add(
                                "https://images.pexels.com/photos/617278/pexels-photo-617278.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
                        images.add(
                                "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/reference_guide/cats_and_excessive_meowing_ref_guide/1800x1200_cats_and_excessive_meowing_ref_guide.jpg");
                        petProf.setImages(images);
                        List<PetProfile> list = new ArrayList<>();
                        list.add(petProf);
                        return list;
                    } else {
                        return null;
                    }
                });

        lenient().when(petprofilerepository.findAllPetProfileByIsAvailable(ISAVAILABLE))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (invocation.getArgument(0).equals(ISAVAILABLE)) {
                        PetProfile petProf = new PetProfile();
                        petProf.setBreed(BREED_KEY);
                        petProf.setName(NAME);
                        petProf.setDescription(DESCRIPTION);
                        petProf.setReasonForPosting(REASON);
                        petProf.setIsAvailable(ISAVAILABLE);
                        images.add(
                                "https://images.pexels.com/photos/617278/pexels-photo-617278.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
                        images.add(
                                "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/reference_guide/cats_and_excessive_meowing_ref_guide/1800x1200_cats_and_excessive_meowing_ref_guide.jpg");
                        petProf.setImages(images);
                        List<PetProfile> list = new ArrayList<>();
                        list.add(petProf);
                        return list;
                    } else {
                        return null;
                    }
                });
        lenient().when(accountRepository.existsByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(USERNAME)) {
                return true;
            }
            return false;
        });
        lenient().when(regularUserRepository.findRegularUserByUser(any(Account.class)))
                .thenAnswer((InvocationOnMock invocation) -> {
                    Account account = new Account();
                    account.setUsername(USERNAME);
                    RegularUser regUser = new RegularUser();
                    regUser.setUser(account);
                    regUser.setName(USERNAME);
                    regUser.setHomeDescription(HOUSE);
                    regUser.setPhoneNumber(PHONE);
                    return regUser;
                });

        lenient().when((petprofilerepository.findAllPetProfileByPetType(any(PetType.class))))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (invocation.getArgument(0).equals(PETTYPE)) {
                        PetProfile petProf = new PetProfile();
                        petProf.setBreed(BREED_KEY);
                        petProf.setName(NAME);
                        petProf.setDescription(DESCRIPTION);
                        petProf.setReasonForPosting(REASON);
                        petProf.setIsAvailable(ISAVAILABLE);
                        petProf.setPetType(PETTYPE);
                        images.add(
                                "https://images.pexels.com/photos/617278/pexels-photo-617278.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
                        images.add(
                                "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/reference_guide/cats_and_excessive_meowing_ref_guide/1800x1200_cats_and_excessive_meowing_ref_guide.jpg");
                        petProf.setImages(images);
                        List<PetProfile> list = new ArrayList<>();
                        list.add(petProf);
                        return list;
                    } else {
                        return null;
                    }
                });

        lenient().when(petprofilerepository.findAllPetProfileByPoster(any(UserRole.class)))
                .thenAnswer((InvocationOnMock invocation) -> {

                    Account account = new Account();
                    account.setUsername(USERNAME);
                    RegularUser regUser = new RegularUser();
                    regUser.setUser(account);
                    regUser.setName(USERNAME);
                    regUser.setHomeDescription(HOUSE);
                    regUser.setPhoneNumber(PHONE);

                    PetProfile petProf = new PetProfile();
                    petProf.setPoster(regUser);
                    petProf.setBreed(BREED_KEY);
                    petProf.setName(NAME);
                    petProf.setDescription(DESCRIPTION);
                    petProf.setReasonForPosting(REASON);
                    petProf.setIsAvailable(ISAVAILABLE);
                    images.add(
                            "https://images.pexels.com/photos/617278/pexels-photo-617278.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
                    images.add(
                            "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/reference_guide/cats_and_excessive_meowing_ref_guide/1800x1200_cats_and_excessive_meowing_ref_guide.jpg");
                    petProf.setImages(images);
                    List<PetProfile> list = new ArrayList<>();
                    list.add(petProf);
                    return list;

                });

        lenient().when(petprofilerepository.findPetProfileByNameAndPoster(anyString(), any(UserRole.class)))
                .thenAnswer((InvocationOnMock invocation) -> {

                    Account account = new Account();
                    account.setUsername(USERNAME);
                    RegularUser regUser = new RegularUser();
                    regUser.setUser(account);
                    regUser.setName(USERNAME);
                    regUser.setHomeDescription(HOUSE);
                    regUser.setPhoneNumber(PHONE);

                    PetProfile petProf = new PetProfile();
                    petProf.setPoster(regUser);
                    petProf.setBreed(BREED_KEY);
                    petProf.setName(NAME);
                    petProf.setDescription(DESCRIPTION);
                    petProf.setReasonForPosting(REASON);
                    petProf.setIsAvailable(ISAVAILABLE);
                    images.add(
                            "https://images.pexels.com/photos/617278/pexels-photo-617278.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
                    images.add(
                            "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/reference_guide/cats_and_excessive_meowing_ref_guide/1800x1200_cats_and_excessive_meowing_ref_guide.jpg");
                    petProf.setImages(images);
                    
                    return petProf;

                });

        lenient().when(petprofilerepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {

            Account account = new Account();
            account.setUsername(USERNAME);
            RegularUser regUser = new RegularUser();
            regUser.setUser(account);
            regUser.setName(USERNAME);
            regUser.setHomeDescription(HOUSE);
            regUser.setPhoneNumber(PHONE);

            PetProfile petProf = new PetProfile();
            petProf.setPoster(regUser);
            petProf.setBreed(BREED_KEY);
            petProf.setName(NAME);
            petProf.setDescription(DESCRIPTION);
            petProf.setReasonForPosting(REASON);
            petProf.setIsAvailable(ISAVAILABLE);
            petProf.setPetType(PETTYPE);
            images.add(
                    "https://images.pexels.com/photos/617278/pexels-photo-617278.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
            images.add(
                    "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/reference_guide/cats_and_excessive_meowing_ref_guide/1800x1200_cats_and_excessive_meowing_ref_guide.jpg");
            petProf.setImages(images);
            List<PetProfile> list = new ArrayList<>();
            list.add(petProf);
            return list;

        });

        lenient().when(petprofilerepository.existsByBreed(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(BREED_KEY)) {
                return true;
            }
            return false;

        });
        lenient().when(petprofilerepository.existsByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(NAME)) {
                return true;
            }
            return false;

        });
        lenient().when(accountRepository.findAccountByUsername(anyString()))
                .thenAnswer((InvocationOnMock invocation) -> {
                    Account account = new Account();
                    account.setUsername(USERNAME);
                    RegularUser regUser = new RegularUser();
                    regUser.setUser(account);
                    regUser.setName(NAME);
                    regUser.setHomeDescription(HOUSE);
                    regUser.setPhoneNumber(PHONE);
                    return account;

                });
        lenient().when(petprofilerepository.existsByNameAndPoster(anyString(), any(UserRole.class)))
                .thenAnswer((InvocationOnMock invocation) -> {
                    Account account = new Account();
                    account.setUsername(USERNAME);
                    RegularUser regUser = new RegularUser();
                    regUser.setUser(account);
                    regUser.setName(USERNAME);
                    regUser.setHomeDescription(HOUSE);
                    regUser.setPhoneNumber(PHONE);
                    if (invocation.getArgument(0).equals(NAME) && invocation.getArgument(1).equals(regUser)) {
                        return true;
                    }
                    return false;

                });
        lenient().when(accountRepository.existsByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(USERNAME)) {
                return true;
            } else {
                return false;
            }
        });
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(accountRepository.save(any(Account.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(regularUserRepository.save(any(RegularUser.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(petprofilerepository.save(any(PetProfile.class))).thenAnswer(returnParameterAsAnswer);
    }

    @Test
    public void testCreatePetProfile() {
        Calendar c = Calendar.getInstance();
        c.set(2020, Calendar.MARCH, 16, 9, 0, 0);
        Date postDate = new Date(c.getTimeInMillis());
        Time postTime = new Time(c.getTimeInMillis());
        Account account = new Account();
        account.setUsername(USERNAME);
        RegularUser regUser = new RegularUser();
        regUser.setUser(account);
        regUser.setName(USERNAME);
        regUser.setHomeDescription(HOUSE);
        regUser.setPhoneNumber(PHONE);
        images.add(
                "https://images.pexels.com/photos/617278/pexels-photo-617278.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        images.add(
                "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/reference_guide/cats_and_excessive_meowing_ref_guide/1800x1200_cats_and_excessive_meowing_ref_guide.jpg");

        accountRepository.save(account);
        regularUserRepository.save(regUser);
        PetProfile petProf = null;

        try {

            petProf = petProfileService.createPetProfile(BREED_KEY, DESCRIPTION, NAME, PETTYPE, postTime, postDate,
                    USERNAME, REASON, ISAVAILABLE, images, regUser);

        } catch (IllegalArgumentException e) {
            fail();
        }

        assertNotNull(petProf);
        assertEquals(BREED_KEY, petProf.getBreed());
        assertEquals(NAME, petProf.getName());
        assertEquals(regUser, petProf.getPoster());
        assertEquals(DESCRIPTION, petProf.getDescription());
        assertEquals(PETTYPE, petProf.getPetType());
        assertEquals(REASON, petProf.getReasonForPosting());
        assertEquals(postDate, petProf.getPostDate());
        assertEquals(postTime, petProf.getPostTime());
    }

    @Test
    public void testGetAllPetProf() {
        Calendar c = Calendar.getInstance();
        c.set(2020, Calendar.MARCH, 16, 9, 0, 0);
        Date postDate = new Date(c.getTimeInMillis());
        Time postTime = new Time(c.getTimeInMillis());
        Account account = new Account();
        account.setUsername(USERNAME);
        RegularUser regUser = new RegularUser();
        regUser.setUser(account);
        regUser.setName(USERNAME);
        regUser.setHomeDescription(HOUSE);
        regUser.setPhoneNumber(PHONE);
        images.add(
                "https://images.pexels.com/photos/617278/pexels-photo-617278.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        images.add(
                "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/reference_guide/cats_and_excessive_meowing_ref_guide/1800x1200_cats_and_excessive_meowing_ref_guide.jpg");

        accountRepository.save(account);
        regularUserRepository.save(regUser);
        PetProfile petProf = null;
        try {

            petProf = petProfileService.createPetProfile(BREED_KEY, DESCRIPTION, NAME, PETTYPE, postTime, postDate,
                    USERNAME, REASON, ISAVAILABLE, images, regUser);

        } catch (Exception e) {
            fail();
        }
        List<PetProfile> petProfiles = petProfileService.getAllPetProfiles();
        assertEquals(1, petProfiles.size());
        assertEquals(petProf.getBreed(), petProfiles.get(0).getBreed());
        assertEquals(petProf.getName(), petProfiles.get(0).getName());
        assertEquals(petProf.getDescription(), petProfiles.get(0).getDescription());
        assertEquals(petProf.getId(), petProfiles.get(0).getId());
        assertEquals(petProf.getPetType(), petProfiles.get(0).getPetType());
        assertEquals(petProf.getReasonForPosting(), petProfiles.get(0).getReasonForPosting());

    }

    @Test
    public void testGetAllPetProfileByUsername() {
        List<PetProfile> petProfiles = new ArrayList<>();
        createPetProfile();

        try {
            petProfiles = petProfileService.getAllPetProfilesByUsername(USERNAME);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }

        assertNotEquals(0, petProfiles.size());
        assertEquals(NAME, petProfiles.get(0).getName());

    }

    @Test
    public void testGetAllProfileByNoneExistingUsername() {
        List<PetProfile> petProfiles = new ArrayList<>();
        createPetProfile();

        String nonExistingUsername = "2PAC";
        try {
            petProfiles = petProfileService.getAllPetProfilesByUsername(nonExistingUsername);
        } catch (Exception e) {
            String error = e.getMessage();
            assertEquals("No user associated with this username", error);
        }
    }

    @Test
    public void testGetAllPetProfileByIsAvailable() {
        List<PetProfile> petProfiles = new ArrayList<>();
        createPetProfile();

        try {
            petProfiles = petProfileService.getAllPetProfilesByIsAvailable(ISAVAILABLE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }

        assertNotEquals(0, petProfiles.size());
        assertEquals(ISAVAILABLE, petProfiles.get(0).isIsAvailable());
    }

    @Test
    public void testGetAllPetProfileByNotIsAvailable() {
        List<PetProfile> petProfiles = new ArrayList<>();
        createPetProfile();

        try {
            petProfiles = petProfileService.getAllPetProfilesByIsAvailable(!ISAVAILABLE);
        } catch (Exception e) {
            String error = e.getMessage();

            assertEquals("No Pet Profiles with the selected availability", error);
        }
    }

    @Test
    public void testGetAllPetProfileByBreed() {
        List<PetProfile> petProfiles = new ArrayList<>();
        createPetProfile();

        try {
            petProfiles = petProfileService.getAllPetProfilesByBreed(BREED_KEY);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }

        assertNotEquals(0, petProfiles.size());
        assertEquals(BREED_KEY, petProfiles.get(0).getBreed());
    }

    @Test
    public void testGetAllPetProfilesByNonExistingBreed() {
        List<PetProfile> petProfiles = new ArrayList<>();
        createPetProfile();

        String nonExistingBreed = "TYPE";
        try {
            petProfiles = petProfileService.getAllPetProfilesByBreed(nonExistingBreed);
        } catch (Exception e) {
            String error = e.getMessage();

            assertEquals("There is no such breed in our database", error);
        }
    }

    @Test
    public void testGetAllPetProfileByPetType() {
        List<PetProfile> petProfiles = new ArrayList<>();
        createPetProfile();

        try {
            petProfiles = petProfileService.getAllPetProfilesByPetType(PETTYPE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }

        assertNotEquals(0, petProfiles.size());
        assertEquals(PETTYPE, petProfiles.get(0).getPetType());
    }

    @Test
    public void testUpdatePetProf() {
        

        String newBreed = "OMLETT";
        String newDescription = "HE IS BLACK AND CUTE";
        PetType newPetType = PetType.AMPHIBIAN;
        String newReason = "new reason";
        createPetProfile();
        PetProfile petProf = null;


        try {
            petProf = petProfileService.updatePetProfile(USERNAME, newBreed, newDescription, newReason, newPetType, NAME,
                    !ISAVAILABLE, images);
        } catch (Exception e) {
            fail();
        }

        assertNotNull(petProf);
        assertEquals(newBreed, petProf.getBreed());
        assertEquals(newDescription, petProf.getDescription());
        assertEquals(newPetType, petProf.getPetType());
        assertNotEquals(0, petProf.getImages().size());
    }

    
    @AfterEach
    public void clearDatabase() {

        petprofilerepository.deleteAll();
        regularUserRepository.deleteAll();
        accountRepository.deleteAll();
      
    }
    public void createPetProfile() {
        Calendar c = Calendar.getInstance();
        c.set(2020, Calendar.MARCH, 16, 9, 0, 0);
        Date postDate = new Date(c.getTimeInMillis());
        Time postTime = new Time(c.getTimeInMillis());
        Account account = new Account();
        account.setUsername(USERNAME);

        RegularUser regUser = new RegularUser();
        regUser.setUser(account);
        regUser.setName(USERNAME);
        regUser.setHomeDescription(HOUSE);
        regUser.setPhoneNumber(PHONE);
        images.add(
                "https://images.pexels.com/photos/617278/pexels-photo-617278.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        images.add(
                "https://img.webmd.com/dtmcms/live/webmd/consumer_assets/site_images/article_thumbnails/reference_guide/cats_and_excessive_meowing_ref_guide/1800x1200_cats_and_excessive_meowing_ref_guide.jpg");

        accountRepository.save(account);
        regularUserRepository.save(regUser);
        PetProfile petProf = null;
        try {
            petProf = petProfileService.createPetProfile(BREED_KEY, DESCRIPTION, NAME, PETTYPE, postTime, postDate,
                    USERNAME, REASON, ISAVAILABLE, images, regUser);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
