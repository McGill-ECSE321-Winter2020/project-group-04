package ca.mcgill.ecse321.petadoptionsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.Admin;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;
import ca.mcgill.ecse321.petadoptionsystem.model.UserRole;

@ExtendWith(MockitoExtension.class)
public class TestAccountService {

    @Mock
    private AccountRepository accountDAO;

    @InjectMocks
    private AccountService accountService;

    private static final String USERNAME = "Bathsheba_Everdene";
    private static final String EMAIL = "bathsheba.everdene@gmail.com";
    private static final String PASSWORDHASH = "the HASH slinging SLASHer";

    @BeforeEach
    public void setMockOutput() {
        
        // mock for findAccountsByUsername
        lenient().when(accountDAO.findAccountByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(USERNAME)) {
                Account account = new Account();
                account.setUsername(USERNAME);
                account.setEmail(EMAIL);
                account.setPasswordHash(PASSWORDHASH);
                account.setUserRole(new RegularUser());
                return account;
            } else {
                return null;
            }
        });

        // mock for findAccountsByEmail
        lenient().when(accountDAO.findAccountByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(EMAIL)) {
                Account account = new Account();
                account.setUsername(USERNAME);
                account.setEmail(EMAIL);
                account.setPasswordHash(PASSWORDHASH);
                account.setUserRole(new RegularUser());
                return account;
            } else {
                return null;
            }
        });

        // mock for existsByUsername
        lenient().when(accountDAO.existsByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(USERNAME)) {
                return true;
            } else {
                return false;
            }
        });

        // mock for existsByEmail
        lenient().when(accountDAO.existsByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(EMAIL)) {
                return true;
            } else {
                return false;
            }
        });
    }

    @Test
    public void testCreateRegularUserAccount() {
        assertEquals(0, accountService.getAllAccounts().size());

        String username = "xXx_mike_xXx";
    }
}
