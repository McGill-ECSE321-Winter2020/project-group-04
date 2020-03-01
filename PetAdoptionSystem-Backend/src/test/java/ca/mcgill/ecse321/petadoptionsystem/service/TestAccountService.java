package ca.mcgill.ecse321.petadoptionsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.Admin;
import ca.mcgill.ecse321.petadoptionsystem.model.Admin;
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

        // TODO: delete

        // mock for findAll
        lenient().when(accountDAO.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            Account account = new Account();
            account.setUsername(USERNAME);
            account.setEmail(EMAIL);
            account.setPasswordHash(PASSWORDHASH);
            account.setUserRole(new RegularUser());

            List<Account> list = new ArrayList<Account>();
            list.add(account);

            return list;
        });

        Answer<?> returnParam = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(accountDAO.save(any(Account.class))).thenAnswer(returnParam);
    }

    @AfterEach
    public void clearDataBase(){
        accountDAO.deleteAll();
    }

    /* CREATE REGULAR USER ACCOUNT TESTS */

    @Test
    public void testCreateRegularUserAccount() {
        assertEquals(1, accountService.getAllAccounts().size());

        // create test account params
        String username = "xXx_mike_xXx";
        String passwordHash = "hashedpotatoes";
        String email = "mikey.mike@hotmail.com";

        // initialize account to null, so we can see if account creation was successful
        Account account = null;
        
        try {
            account = accountService.createRegularUserAccount(username, passwordHash, email);
        } catch (IllegalArgumentException e) {
            // no error should have occurred here
            fail();
        }

        // check if not null and values are as expected
        assertNotNull(account);
        assertEquals(username, account.getUsername());
        assertEquals(passwordHash, account.getPasswordHash());
        assertEquals(email, account.getEmail());

    }

    @Test
    public void testCreateRegularUserAccountNull() {
        assertEquals(1, accountService.getAllAccounts().size());

        // create test account params
        String username = null;
        String passwordHash = null;
        String email = null;

        // initialize account to null, so we can see if account creation was successful
        Account account = null;

        String error = null;
        
        try {
            account = accountService.createRegularUserAccount(username, passwordHash, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check if null and error is as expected
        assertNull(account);
        assertEquals("The username cannot be empty.\nThe password hash cannot be empty.\nThe email address cannot be empty.\n", error);
    }

    @Test
    public void testCreateRegularUserAccountEmpty() {
        assertEquals(1, accountService.getAllAccounts().size());

        // create test account params
        String username = "";
        String passwordHash = "";
        String email = "";

        // initialize account to null, so we can see if account creation was successful
        Account account = null;

        String error = null;
        
        try {
            account = accountService.createRegularUserAccount(username, passwordHash, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check if null and error is as expected
        assertNull(account);
        assertEquals("The username cannot be empty.\nThe password hash cannot be empty.\nThe email address cannot be empty.\n", error);
    }

    @Test
    public void testCreateRegularUserAccountSpaces() {
        assertEquals(1, accountService.getAllAccounts().size());

        // create test account params
        String username = " ";
        String passwordHash = " ";
        String email = " ";

        // initialize account to null, so we can see if account creation was successful
        Account account = null;

        String error = null;
        
        try {
            account = accountService.createRegularUserAccount(username, passwordHash, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check if null and error is as expected
        assertNull(account);
        assertEquals("The username cannot be empty.\nThe password hash cannot be empty.\nThe email address cannot be empty.\n", error);
    }

    @Test
    public void testCreateRegularUserAccountDuplicate() {
        assertEquals(1, accountService.getAllAccounts().size());

        // create test account params
        String username = "xXx_mike_xXx";
        String passwordHash = "hashedpotatoes";
        String email = "mikey.mike@hotmail.com";

        // initialize accounts to null, so we can see if account creation was successful
        Account account1 = null;
        Account account2 = null;

        String error = null;
        
        // try to make the first account
        try {
            account1 = accountService.createRegularUserAccount(username, passwordHash, email);
        } catch (IllegalArgumentException e) {
            fail();
        }

        // check if not null and values are as expected
        assertNotNull(account1);
        assertEquals(username, account1.getUsername());
        assertEquals(passwordHash, account1.getPasswordHash());
        assertEquals(email, account1.getEmail());

        // try to create the second account
        try {
            account2 = accountService.createRegularUserAccount(username, passwordHash, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // check if null and error is as expected
        assertNull(account2);
        assertEquals("That username is already taken.\nThat email address is already taken.\n", error);
    }

    /* CREATE ADMIN ACCOUNT TESTS */

    @Test
    public void testCreateAdminAccount() {
        assertEquals(1, accountService.getAllAccounts().size());

        // create test account params
        String username = "xXx_mike_xXx";
        String passwordHash = "hashedpotatoes";
        String email = "mikey.mike@hotmail.com";

        // initialize account to null, so we can see if account creation was successful
        Account account = null;
        
        try {
            account = accountService.createAdminAccount(username, passwordHash, email);
        } catch (IllegalArgumentException e) {
            // no error should have occurred here
            fail();
        }

        // check if not null and values are as expected
        assertNotNull(account);
        assertEquals(username, account.getUsername());
        assertEquals(passwordHash, account.getPasswordHash());
        assertEquals(email, account.getEmail());

    }

    @Test
    public void testCreateAdminAccountNull() {
        assertEquals(1, accountService.getAllAccounts().size());

        // create test account params
        String username = null;
        String passwordHash = null;
        String email = null;

        // initialize account to null, so we can see if account creation was successful
        Account account = null;

        String error = null;
        
        try {
            account = accountService.createAdminAccount(username, passwordHash, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check if null and error is as expected
        assertNull(account);
        assertEquals("The username cannot be empty.\nThe password hash cannot be empty.\nThe email address cannot be empty.\n", error);
    }

    @Test
    public void testCreateAdminAccountEmpty() {
        assertEquals(1, accountService.getAllAccounts().size());

        // create test account params
        String username = "";
        String passwordHash = "";
        String email = "";

        // initialize account to null, so we can see if account creation was successful
        Account account = null;

        String error = null;
        
        try {
            account = accountService.createAdminAccount(username, passwordHash, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check if null and error is as expected
        assertNull(account);
        assertEquals("The username cannot be empty.\nThe password hash cannot be empty.\nThe email address cannot be empty.\n", error);
    }

    @Test
    public void testCreateAdminAccountSpaces() {
        assertEquals(1, accountService.getAllAccounts().size());

        // create test account params
        String username = " ";
        String passwordHash = " ";
        String email = " ";

        // initialize account to null, so we can see if account creation was successful
        Account account = null;

        String error = null;
        
        try {
            account = accountService.createAdminAccount(username, passwordHash, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check if null and error is as expected
        assertNull(account);
        assertEquals("The username cannot be empty.\nThe password hash cannot be empty.\nThe email address cannot be empty.\n", error);
    }

    @Test
    public void testCreateAdminAccountDuplicate() {
        assertEquals(1, accountService.getAllAccounts().size());

        // create test account params
        String username = "xXx_mike_xXx";
        String passwordHash = "hashedpotatoes";
        String email = "mikey.mike@hotmail.com";

        // initialize accounts to null, so we can see if account creation was successful
        Account account1 = null;
        Account account2 = null;

        String error = null;
        
        // try to make the first account
        try {
            account1 = accountService.createAdminAccount(username, passwordHash, email);
        } catch (IllegalArgumentException e) {
            fail();
        }

        // check if not null and values are as expected
        assertNotNull(account1);
        assertEquals(username, account1.getUsername());
        assertEquals(passwordHash, account1.getPasswordHash());
        assertEquals(email, account1.getEmail());

        // try to create the second account
        try {
            account2 = accountService.createAdminAccount(username, passwordHash, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        // check if null and error is as expected
        assertNull(account2);
        assertEquals("That username is already taken.\nThat email address is already taken.\n", error);
    }
}
