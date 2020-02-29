package ca.mcgill.ecse321.petadoptionsystem.service;


import ca.mcgill.ecse321.petadoptionsystem.dao.AdoptionApplicationRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class TestAdoptionApplicationService {

    @Mock
    private AdoptionApplicationRepository appDao;

    private static final String USERNAME1 = "John";

    private static final Integer EXISTING_INT = 2;

    @InjectMocks
    private AdoptionApplicationService appService;

    @BeforeEach
    public void setMockOutput(){
        // lenient().when(appDao.findAdoptionById((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
			// if (invocation.getArgument(0).equals(EXISTING_INT)) {
			// 	Application ro = new Application();
			// 	ro.setApplicationId(EXISTING_INT);
			// 	return ro;
			// } else {
			// 	return null;
			// }
		// });
		// lenient().when(appDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
		// 	Set<Application> apps = new HashSet<>();
		// 	Application ro = new Application();
		// 	ro.setApplicationId(EXISTING_INT);
		// 	apps.add(ro);
		// 	return apps;
		// });
    }

    @Test
    public void testApply(){
        
    }
}
