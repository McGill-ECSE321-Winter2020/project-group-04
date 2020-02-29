package ca.mcgill.ecse321.petadoptionsystem.service;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.AdminRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.Admin;
import ca.mcgill.ecse321.petadoptionsystem.service.AdminService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestAdminService {

    @Mock
    private AccountRepository accountDao;

    @Mock
    private AdminRepository adminDao;

    private static final String USERNAME1 = "John";


    @InjectMocks
    private AdminService adminService;

    @AfterEach
    public void clearDataBase(){
        accountDao.deleteAll();
        adminDao.deleteAll();;
    }

    @BeforeEach
    public void setMockOutput(){
        lenient().when(adminDao.findAdminByUser(any(Account.class))).thenAnswer((InvocationOnMock invocation) ->
        {
            if (invocation.getArgument(0).equals(anyInt())){
                Account account = new Account();
                account.setUsername(USERNAME1);
                Admin admin = new Admin();
                admin.setUser(account);
                return admin;
            } else {
                return null;
            }
        });
        Answer<?> returnParam = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(adminDao.save(any(Admin.class))).thenAnswer(returnParam);
    }

    @Test
    public void testExistingAdmin(){
        //assertEquals(adminService.getAdminByUsername(USERNAME1).getUser().getUsername(), USERNAME1);
    }

}
