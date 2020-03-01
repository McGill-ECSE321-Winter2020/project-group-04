package ca.mcgill.ecse321.petadoptionsystem.service;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.AdoptionApplicationRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.PetProfileRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.AdoptionApplication;
import ca.mcgill.ecse321.petadoptionsystem.model.PetProfile;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
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

	@Mock
	private AccountRepository acDao;
	@Mock
	private PetProfileRepository ppDao;
	@Mock
	private RegularUserRepository ruDao;

	// Applicant Profile params
	private static final String Username1 = "John316";
	private static final String ruName1 = "John";
	private static final String ruDesc1 = "Hardworking champ";
	private static final int phoneNum1 = 123456789;
	// Poster Profile params
	private static final String Username2 = "Achmed316";
	private static final String ruName2 = "Achmed";
	private static final String ruDesc2 = "Generous champ";
	private static final int phoneNum2 = 987654321;

	// application params
	private static final int appId = 2;
	private static final boolean isApproved = false;
	private static final boolean isConfirmed = false;

	// pet profile params
	private static final int ppId = 2;
	private static final int ppId2 = 3;
	private static final String ppBreed = "chihuahua";
	private static final String ppName = "Bingo";
	private static final String ppDesc = "Bad doggy up for adoption";

	@InjectMocks
	private AdoptionApplicationService appService;

	@BeforeEach
	public void setMockOutput() {
		lenient().when(appDao.findAdoptionById((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
			AdoptionApplication aa = new AdoptionApplication();
			aa.setId(appId);
			return aa;

		});
		lenient().when(appDao.findByApplicantAndPetProfile(any(RegularUser.class), any(PetProfile.class)))
				.thenAnswer((InvocationOnMock invocation) -> {
					AdoptionApplication app = setUpApplication();
					if (invocation.getArgument(0).equals(app.getApplicant())
							&& invocation.getArgument(1).equals(app.getPetProfile())) {
						return app;
					} else {
						return null;
					}
				});

		lenient().when(appDao.findByApplicant(any(RegularUser.class))).thenAnswer((InvocationOnMock invocation) -> {

			List<AdoptionApplication> apps = new ArrayList<AdoptionApplication>();
			AdoptionApplication app = setUpApplication();
			apps.add(app);
			return apps;
		});
		lenient().when(appDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			AdoptionApplication app = setUpApplication();
			List<AdoptionApplication> applst = new ArrayList<AdoptionApplication>();
			applst.add(app);
			return applst;

		});

		lenient().when(appDao.findByPetProfile(any(PetProfile.class))).thenAnswer((InvocationOnMock invocation) -> {

			List<AdoptionApplication> apps = new ArrayList<AdoptionApplication>();
			AdoptionApplication app = setUpApplication();
			apps.add(app);
			return apps;
		});

		lenient().when(acDao.findAccountByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			RegularUser ru = setUpRegularUser(Username1, ruName1, ruDesc1, phoneNum1);
			if (invocation.getArgument(0).equals(Username1)) {
				Account ac = ru.getUser();
				return ac;
			} else {
				return null;
			}
		});
		lenient().when(ruDao.findRegularUserByUser(any(Account.class))).thenAnswer((InvocationOnMock invocation) -> {
			RegularUser ru = setUpRegularUser(Username1, ruName1, ruDesc1, phoneNum1);
			// if (invocation.getArgument(0).equals(ru.getUser().getUsername())) {
			return ru;
			// } else {
			// return null;
			// }
		});
		lenient().when(ppDao.findPetProfileById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ppId2)) {
				RegularUser poster = setUpRegularUser(Username2, ruName2, ruDesc2, phoneNum2);
				PetProfile pp = setUpPetProfile(poster, ppId2, ppName, ppDesc, ppBreed);
				return pp;
			} else {
				return null;
			}
		});

		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(appDao.save(any(AdoptionApplication.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(acDao.save(any(Account.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(ppDao.save(any(PetProfile.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(ruDao.save(any(RegularUser.class))).thenAnswer(returnParameterAsAnswer);
	}

	@AfterEach
	public void clearDabase() {
		appDao.deleteAll();
		acDao.deleteAll();
		ppDao.deleteAll();
		ruDao.deleteAll();
	}

	@Test
	public void testCreateApplication() {
		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		Date postDate = new Date(c.getTimeInMillis());
		Time postTime = new Time(c.getTimeInMillis());

		AdoptionApplication app = null;

		String curUser = Username1;
		int petId = 3;

		try {
			app = appService.createApplication(postDate, postTime, curUser, petId);
		} catch (IllegalArgumentException e) {
			fail(); // Check that no error occurred
		}
		assertNotNull(app);
		assertEquals(postDate, app.getPostDate());
		assertEquals(postTime, app.getPostTime());
		assertEquals(petId, app.getPetProfile().getId());

	}

	@Test
	public void testCreateApplicationNoUsername() {
		String error = "";
		Calendar c = Calendar.getInstance();
		c.set(2017, Calendar.MARCH, 16, 9, 0, 0);
		Date postDate = new Date(c.getTimeInMillis());
		Time postTime = new Time(c.getTimeInMillis());

		AdoptionApplication app = null;

		String curUser = null;
		int petId = 2;

		try {
			app = appService.createApplication(postDate, postTime, curUser, petId);
		} catch (IllegalArgumentException e) {
			error = e.getMessage(); // Check that no error occurred
		}
		assertNull(app);
		assertEquals("The username cannot be empty or have spaces.", error);
	}

	@Test
	public void testGetAllApplications() {
		List<AdoptionApplication> apps = appService.getAllApplications();

		assertEquals(1, apps.size());
		int id = appService.getApplicationbyId(appId).getId();
		assertEquals(id, apps.get(0).getId());

	}

	@Test
	public void testGetApplicationsbyUser() {
		String user = Username1;
		List<AdoptionApplication> apps = new ArrayList<>();

		try {
			apps = appService.getApplicationsByUser(user);
		} catch (IllegalArgumentException e) {
			fail(); // Check that no error occurred
		}

		assertNotNull(apps);
		assertEquals(1, apps.size());
		int id = appService.getApplicationbyId(appId).getId();
		assertEquals(id, apps.get(0).getId());
	}

	// @Test
	// public void testGetApplicationsNoUser(){

	// }

	// @Test
	// public void testGetApplicationsbyPetProfile(){

	// }

	// @Test
	// public void testGetApplicationsNoPetProfile(){

	// }

	// @Test
	// public void testGetAllApplicationsToProfile(){

	// }

	// @Test
	// public void testGetAllApplicationsOfUser(){

	// }
	// @Test
	// public void testGetApplicationNoUserNoProfile(){

	// }
	// @Test
	// public void updateApplication(){

	// }

	/**
	 * Helper methods to create stubs and setup Mockito for Dao
	 * 
	 * @return
	 */
	private AdoptionApplication setUpApplication() {
		RegularUser applicant = setUpRegularUser(Username1, ruName1, ruDesc1, phoneNum1);
		RegularUser poster = setUpRegularUser(Username2, ruName2, ruDesc2, phoneNum2);
		PetProfile petprof = setUpPetProfile(poster, ppId, ppName, ppDesc, ppBreed);

		AdoptionApplication app = new AdoptionApplication();
		app.setId(appId);
		app.setApplicant(applicant);
		app.setPetProfile(petprof);
		app.setIsApproved(isApproved);
		app.setIsConfirmed(isConfirmed);
		return app;
	}

	private PetProfile setUpPetProfile(RegularUser poster, int id, String ppname2, String ppdesc2, String ppbreed2) {
		PetProfile pp = new PetProfile();
		pp.setId(id);
		pp.setName(ppName);
		pp.setDescription(ppDesc);
		pp.setBreed(ppBreed);
		pp.setPoster(poster);
		return pp;
	}

	private RegularUser setUpRegularUser(String username, String regName, String desc, int phoneNum) {
		Account acc = new Account();
		acc.setUsername(username);
		RegularUser ru = new RegularUser();
		ru.setName(regName);
		ru.setHomeDescription(desc);
		ru.setPhoneNumber(phoneNum);
		ru.setUser(acc);
		return ru;
	}

}
