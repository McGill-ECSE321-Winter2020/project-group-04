package ca.mcgill.ecse321.petadoptionsystem.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

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

        lenient().when((petprofilerepository.findAllPetProfileByPetType(any(PetType.class))))
                .thenAnswer((InvocationOnMock invocation) -> {
                    if (invocation.getArgument(0).equals(PETTYPE)) {
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
                    List<PetProfile> list = new ArrayList<>();
                    list.add(petProf);
                    return list;

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
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(accountRepository.save(any(Account.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(regularUserRepository.save(any(RegularUser.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(petprofilerepository.save(any(PetProfile.class))).thenAnswer(returnParameterAsAnswer);
    }

    @Test
    public void testCreatePetProfile(){
        
    }


}
