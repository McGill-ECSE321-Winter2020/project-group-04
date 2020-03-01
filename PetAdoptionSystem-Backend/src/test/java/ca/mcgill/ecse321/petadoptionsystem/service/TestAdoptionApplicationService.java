package ca.mcgill.ecse321.petadoptionsystem.service;

import ca.mcgill.ecse321.petadoptionsystem.dao.AdoptionApplicationRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.AdoptionApplication;
import ca.mcgill.ecse321.petadoptionsystem.model.PetProfile;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;


@ExtendWith(MockitoExtension.class)
public class TestAdoptionApplicationService {

    @Mock
    private AdoptionApplicationRepository appDao;

	private static final String ruName = "John";
	
	private static final String ppName = "Bingo";
	private static final String ruDesc = "Hardworking champ";
	private static final String ppDesc = "Bad doggy for adoption";
	
    private static final int phoneNum = 123456789;

    private static final Integer appId = 2;

	private static final String ppBreed = "chihuahua";

	private static final boolean isApproved = false;
	private static final boolean isConfirmed = false;

    @InjectMocks
    private AdoptionApplicationService appService;


    @BeforeEach
    public void setMockOutput(){
        lenient().when(appDao.findAdoptionById((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
			
				AdoptionApplication aa = new AdoptionApplication();
				aa.setId(appId);
				return aa;
			
		});
		lenient().when(appDao.findByApplicantAndPetProfile(any(RegularUser.class),any(PetProfile.class))).thenAnswer((InvocationOnMock invocation) -> {
			RegularUser ru = new RegularUser();
			ru.setName(ruName);
			ru.setHomeDescription(ruDesc);
			ru.setPhoneNumber(phoneNum);

			PetProfile pp = new PetProfile();
			pp.setName(ppName);
			pp.setDescription(ppDesc);
			pp.setBreed(ppBreed);

			AdoptionApplication app = new AdoptionApplication();
			app.setApplicant(ru);
			app.setPetProfile(pp);
			app.setIsApproved(isApproved);
			app.setIsConfirmed(isConfirmed);

			return app;
		});
		
		lenient().when(appDao.findByApplicant(any(RegularUser.class))).thenAnswer((InvocationOnMock invocation) -> {
			RegularUser ru = new RegularUser();
			ru.setName(ruName);
			ru.setHomeDescription(ruDesc);
			ru.setPhoneNumber(phoneNum);

			PetProfile pp = new PetProfile();
			pp.setName(ppName);
			pp.setDescription(ppDesc);
			pp.setBreed(ppBreed);

			List<AdoptionApplication> apps = new ArrayList<AdoptionApplication>();
			AdoptionApplication app = new AdoptionApplication();
			app.setApplicant(ru);
			app.setPetProfile(pp);
			apps.add(app);
			return apps;
		});

		lenient().when(appDao.findByPetProfile(any(PetProfile.class))).thenAnswer((InvocationOnMock invocation) -> {
			RegularUser ru = new RegularUser();
			ru.setName(ruName);
			ru.setHomeDescription(ruDesc);
			ru.setPhoneNumber(phoneNum);

			PetProfile pp = new PetProfile();
			pp.setName(ppName);
			pp.setDescription(ppDesc);
			pp.setBreed(ppBreed);

			List<AdoptionApplication> apps = new ArrayList<AdoptionApplication>();
			AdoptionApplication app = new AdoptionApplication();
			app.setApplicant(ru);
			app.setPetProfile(pp);
			app.setIsApproved(isApproved);
			app.setIsConfirmed(isConfirmed);
			apps.add(app);
			return apps;
		});

		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(appDao.save(any(AdoptionApplication.class))).thenAnswer(returnParameterAsAnswer);
		// lenient().when(eventDao.save(any(Event.class))).thenAnswer(returnParameterAsAnswer);
		// lenient().when(registrationDao.save(any(Registration.class))).thenAnswer(returnParameterAsAnswer);
    }

    @Test
    public void testApply(){

	// 	Calendar c = Calendar.getInstance();
	// 	c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
	// 	Date postDate = new Date(c.getTimeInMillis());
	// 	Time postTime = new Time(c.getTimeInMillis());
	   
	// 	AdoptionApplication app = null;

	// 	String curUser = "Edem";
	// 	int petId = 2;
	
	// 	try {
    //         app = appService.createApplication(postDate, postTime, curUser, petId);
    //     } catch (IllegalArgumentException e) {
    //         // Check that no error occurred
    //         fail();
    //     }

    //     // Comparing the newly updated ones
    //     assertNotNull(app);
    //     // assertEquals(postDate, app.getPostDate()
    //     assertEquals(homedescription, regularUser.getHomeDescription());
    //     assertEquals(phonenumber, regularUser.getPhoneNumber());
	

	// 	checkResultRegister(registration, nameP, nameE, eventDate, startTime, endTime);
	// }

	// private void checkResultRegister(Registration registration, String nameP, String nameE, Date eventDate,
	// 		Time startTime, Time endTime) {
	// 	assertNotNull(registration);
	// 	assertEquals(nameP, registration.getPerson().getName());
	// 	assertEquals(nameE, registration.getEvent().getName());
	// 	assertEquals(eventDate.toString(), registration.getEvent().getDate().toString());
	// 	assertEquals(startTime.toString(), registration.getEvent().getStartTime().toString());
	// 	assertEquals(endTime.toString(), registration.getEvent().getEndTime().toString());
	// }

    }
}
