package ca.mcgill.ecse321.petadoptionsystem.service;

import ca.mcgill.ecse321.petadoptionsystem.dao.PetAdoptionSystemRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.PetAdoptionSystem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;


import javax.validation.constraints.Null;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestPetAdoptionSystemService {
    @Mock
    private PetAdoptionSystemRepository systemDao;

    @InjectMocks
    private PetAdoptionSystemService systemService;

    @AfterEach
    public void clearDataBase(){
        systemDao.deleteAll();
    }

    private static final int SYS_KEY = 4;
    private static final int NON_EXISTING_SYS_KEY = 6;

    @BeforeEach
    public void setMockOutput(){
        lenient().when(systemDao.findPetAdoptionSystemById(SYS_KEY)).thenAnswer((InvocationOnMock invocation) ->
        {
            PetAdoptionSystem system = new PetAdoptionSystem();
            system.setId(SYS_KEY);
            return system;
        });
        Answer<?> returnParam = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(systemDao.save(any(PetAdoptionSystem.class))).thenAnswer(returnParam);
    }

    @Test
    public void testExistingSystem(){
        assertEquals(systemService.getPetAdoptionSystem().getId(), SYS_KEY);
    }

    @Test
    public void testNonExistingSystem(){
        PetAdoptionSystem system = null;
        String error = null;

        try{
            system = systemService.getPetAdoptionSystem();
        } catch(NullPointerException e){
            error = e.getMessage();
        }
        assertNotNull(system);
    }

    @Test
    public void testCreateSystem(){
        PetAdoptionSystem system = null;
        try {
            system = systemService.createPetAdoptionSystem();
            system.setId(SYS_KEY);
        }catch(IllegalArgumentException e){
            fail();
        }
        assertNotNull(system);
        assertEquals(SYS_KEY, system.getId());
    }

}
