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
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;
import ca.mcgill.ecse321.petadoptionsystem.model.Admin;
import ca.mcgill.ecse321.petadoptionsystem.model.UserRole;

/**
 * @author Garrett Kinman
 */
@ExtendWith(MockitoExtension.class)
public class TestAccountService {

    @Mock
    private AccountRepository accountDAO;

    @InjectMocks
    private AccountService accountService;

    @Mock
    private PetAdoptionSystemService petAdoptionSystemService;

    private static final String USERNAME = "Bathsheba_Everdene";
    private static final String EMAIL = "bathsheba.everdene@gmail.com";
    private static final String PASSWORDHASH = "the HASH slinging SLASHer";

    /**
     * sets mock output for each dao method call
     */
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
                account.setPetAdoptionSystem(petAdoptionSystemService.getPetAdoptionSystem());
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

        // mock for save
        lenient().when(accountDAO.save(any(Account.class))).thenAnswer(returnParam);
    }

    @AfterEach
    public void clearDataBase(){
        accountDAO.deleteAll();
    }

    /* CREATE REGULAR USER ACCOUNT TESTS */

    /**
     * test should work
     */
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
            account = accountService.createRegularUserAccount(petAdoptionSystemService.getPetAdoptionSystem(), username, passwordHash, email);
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

    /**
     * should have exception that it catches
     * null input username
     */
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
            account = accountService.createRegularUserAccount(petAdoptionSystemService.getPetAdoptionSystem(),username, passwordHash, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check if null and error is as expected
        assertNull(account);
        assertEquals("The username cannot be empty.\nThe password hash cannot be empty.\nThe email address cannot be empty.\n", error);
    }

    /**
     * should catch exception
     * empty string inputs
     */
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
            account = accountService.createRegularUserAccount(petAdoptionSystemService.getPetAdoptionSystem(),username, passwordHash, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check if null and error is as expected
        assertNull(account);
        assertEquals("The username cannot be empty.\nThe password hash cannot be empty.\nThe email address cannot be empty.\n", error);
    }

    /**
     * only spaces in the strings
     * should be caught
     */
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
            account = accountService.createRegularUserAccount(petAdoptionSystemService.getPetAdoptionSystem(),username, passwordHash, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check if null and error is as expected
        assertNull(account);
        assertEquals("The username cannot be empty.\nThe password hash cannot be empty.\nThe email address cannot be empty.\n", error);
    }


    /* CREATE ADMIN ACCOUNT TESTS */

    /**
     * valid input
     */
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
            account = accountService.createAdminAccount(petAdoptionSystemService.getPetAdoptionSystem(), username, passwordHash, email);
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

    /**
     * null input
     */
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
            account = accountService.createAdminAccount(petAdoptionSystemService.getPetAdoptionSystem(),username, passwordHash, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check if null and error is as expected
        assertNull(account);
        assertEquals("The username cannot be empty.\nThe password hash cannot be empty.\nThe email address cannot be empty.\n", error);
    }

    /**
     * empty string input
     */
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
            account = accountService.createAdminAccount(petAdoptionSystemService.getPetAdoptionSystem(),username, passwordHash, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check if null and error is as expected
        assertNull(account);
        assertEquals("The username cannot be empty.\nThe password hash cannot be empty.\nThe email address cannot be empty.\n", error);
    }


    /* GET ACCOUNT BY USERNAME TESTS */

    /**
     * valid input
     */
    @Test
    public void testGetAccountByUsername() {
        assertEquals(1, accountService.getAllAccounts().size());

        // initialize as null to check if user was found
        Account account = null;

        // there should be one account with USERNAME as the username
        try {
            account = accountService.getAccountByUsername(USERNAME);
        } catch (IllegalArgumentException e) {
            fail();
        }

        // check if non-null and everything correct
        assertNotNull(account);
        assertEquals(USERNAME, account.getUsername());
        assertEquals(PASSWORDHASH, account.getPasswordHash());
        assertEquals(EMAIL, account.getEmail());
    }

    /**
     * null input
     */
    @Test
    public void testGetAccountByUsernameNull() {
        assertEquals(1, accountService.getAllAccounts().size());

        Account account = null;

        String error = null;

        try {
            account = accountService.getAccountByUsername(null);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check if null and for right error
        assertNull(account);
        assertEquals("The username cannot be empty.\n", error);
    }

    /**
     * empty string input
     */
    @Test
    public void testGetAccountByUsernameEmpty() {
        assertEquals(1, accountService.getAllAccounts().size());

        Account account = null;

        String error = null;

        try {
            account = accountService.getAccountByUsername("");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check if null and for right error
        assertNull(account);
        assertEquals("The username cannot be empty.\n", error);
    }

    /**
     * only spaces input
     */
    @Test
    public void testGetAccountByUsernameSpaces() {
        assertEquals(1, accountService.getAllAccounts().size());

        Account account = null;

        String error = null;

        try {
            account = accountService.getAccountByUsername(" ");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check if null and for right error
        assertNull(account);
        assertEquals("The username cannot be empty.\n", error);
    }

    /**
     * no account with the given username
     */
    @Test
    public void testGetAccountByUsernameNonExistant() {
        assertEquals(1, accountService.getAllAccounts().size());

        Account account = null;

        String error = null;

        try {
            account = accountService.getAccountByUsername("xXx_mike_xXx");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check if null and for right error
        assertNull(account);
        assertEquals("No user associated with that username.\n", error);
    }

    /* GET ACCOUNT BY EMAIL TESTS */

    /**
     * valid input
     */
    @Test
    public void testGetAccountByEmail() {
        assertEquals(1, accountService.getAllAccounts().size());

        // initialize as null to check if user was found
        Account account = null;

        // there should be one account with EMAIL as the email
        try {
            account = accountService.getAccountByEmail(EMAIL);
        } catch (IllegalArgumentException e) {
            fail();
        }

        // check if non-null and everything correct
        assertNotNull(account);
        assertEquals(USERNAME, account.getUsername());
        assertEquals(PASSWORDHASH, account.getPasswordHash());
        assertEquals(EMAIL, account.getEmail());
    }

    /**
     * null input
     */
    @Test
    public void testGetAccountByEmailNull() {
        assertEquals(1, accountService.getAllAccounts().size());

        Account account = null;

        String error = null;

        try {
            account = accountService.getAccountByEmail(null);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check if null and for right error
        assertNull(account);
        assertEquals("The email address cannot be empty.\n", error);
    }

    /**
     * empty string input
     */
    @Test
    public void testGetAccountByEmailEmpty() {
        assertEquals(1, accountService.getAllAccounts().size());

        Account account = null;

        String error = null;

        try {
            account = accountService.getAccountByEmail("");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check if null and for right error
        assertNull(account);
        assertEquals("The email address cannot be empty.\n", error);
    }

    /**
     * only spaces input
     */
    @Test
    public void testGetAccountByEmailSpaces() {
        assertEquals(1, accountService.getAllAccounts().size());

        Account account = null;

        String error = null;

        try {
            account = accountService.getAccountByEmail(" ");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check if null and for right error
        assertNull(account);
        assertEquals("The email address cannot be empty.\n", error);
    }

    /**
     * no account with input email
     */
    @Test
    public void testGetAccountByEmailNonExistant() {
        assertEquals(1, accountService.getAllAccounts().size());

        Account account = null;

        String error = null;

        try {
            account = accountService.getAccountByEmail("mikey.mike@hotmail.com");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check if null and for right error
        assertNull(account);
        assertEquals("No user associated with that email.\n", error);
    }

    /* UPDATE EMAIL TESTS */

    /**
     * valid input
     */
    @Test
    public void testUpdateEmail() {
        assertEquals(1, accountService.getAllAccounts().size());

        Account account = null;

        // new email to update account with
        String email = "bathsheba.everdene@mail.mcgill.ca";

        try {
            account = accountService.updateEmail(USERNAME, email);
        } catch (IllegalArgumentException e) {
            fail();
        }

        // check that not null and that email is the new email
        assertNotNull(account);
        assertEquals(email, account.getEmail());
    }

    /**
     * null email input
     */
    @Test
    public void testUpdateEmailNullEmail() {
        assertEquals(1, accountService.getAllAccounts().size());

        Account account = null;

        // new email to update account with
        String email = null;

        String error = null;

        try {
            account = accountService.updateEmail(USERNAME, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check that null and for correct error message
        assertNull(account);
        assertEquals("The email address cannot be empty.\n", error);
    }

    /**
     * empty string email input
     */
    @Test
    public void testUpdateEmailEmptyEmail() {
        assertEquals(1, accountService.getAllAccounts().size());

        Account account = null;

        // new email to update account with
        String email = "";

        String error = null;

        try {
            account = accountService.updateEmail(USERNAME, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check that null and for correct error message
        assertNull(account);
        assertEquals("The email address cannot be empty.\n", error);
    }

    /**
     * only spaces email input
     */
    @Test
    public void testUpdateEmailSpacesEmail() {
        assertEquals(1, accountService.getAllAccounts().size());

        Account account = null;

        // new email to update account with
        String email = " ";

        String error = null;

        try {
            account = accountService.updateEmail(USERNAME, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check that null and for correct error message
        assertNull(account);
        assertEquals("The email address cannot be empty.\n", error);
    }

    /**
     * new email is duplicate of existing email
     */
    @Test
    public void testUpdateEmailDuplicateEmail() {
        assertEquals(1, accountService.getAllAccounts().size());

        Account account = null;

        // new email to update account with
        String email = EMAIL;

        String error = null;

        try {
            account = accountService.updateEmail(USERNAME, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check that null and for correct error message
        assertNull(account);
        assertEquals("That email address is already taken.\n", error);
    }

    /**
     * null username
     */
    @Test
    public void testUpdateEmailNullUsername() {
        assertEquals(1, accountService.getAllAccounts().size());

        Account account = null;

        // new email to update account with
        String email = "bathsheba.everdene@mail.mcgill.ca";

        String error = null;

        try {
            account = accountService.updateEmail(null, email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check that null and for correct error message
        assertNull(account);
        assertEquals("The username cannot be empty.\n", error);
    }

    /**
     * empty string username
     */
    @Test
    public void testUpdateEmailEmptyUsername() {
        assertEquals(1, accountService.getAllAccounts().size());

        Account account = null;

        // new email to update account with
        String email = "bathsheba.everdene@mail.mcgill.ca";

        String error = null;

        try {
            account = accountService.updateEmail("", email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check that null and for correct error message
        assertNull(account);
        assertEquals("The username cannot be empty.\n", error);
    }

    /**
     * only spaces username
     */
    @Test
    public void testUpdateEmailSpacesUsername() {
        assertEquals(1, accountService.getAllAccounts().size());

        Account account = null;

        // new email to update account with
        String email = "bathsheba.everdene@mail.mcgill.ca";

        String error = null;

        try {
            account = accountService.updateEmail(" ", email);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check that null and for correct error message
        assertNull(account);
        assertEquals("The username cannot be empty.\n", error);
    }

    /* DELETE ACCOUNT TESTS */

    /**
     * null input
     */
    @Test
    public void testDeleteAccountNull() {
        assertEquals(1, accountService.getAllAccounts().size());

        // initialize to null to see if successfully deleted later
        Account account = null;

        String error = null;

        try {
            account = accountService.deleteAccount(null);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check that account was not deleted and that error is correct
        assertNull(account);
        assertEquals(1, accountService.getAllAccounts().size());
        assertEquals("The username cannot be empty.\n", error);
    }

    /**
     * empty string input
     */
    @Test
    public void testDeleteAccountEmpty() {
        assertEquals(1, accountService.getAllAccounts().size());

        // initialize to null to see if successfully deleted later
        Account account = null;

        String error = null;

        try {
            account = accountService.deleteAccount("");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check that account was not deleted and that error is correct
        assertNull(account);
        assertEquals(1, accountService.getAllAccounts().size());
        assertEquals("The username cannot be empty.\n", error);
    }

    /**
     * only spaces input
     */
    @Test
    public void testDeleteAccountSpaces() {
        assertEquals(1, accountService.getAllAccounts().size());

        // initialize to null to see if successfully deleted later
        Account account = null;

        String error = null;

        try {
            account = accountService.deleteAccount(" ");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        // check that account was not deleted and that error is correct
        assertNull(account);
        assertEquals(1, accountService.getAllAccounts().size());
        assertEquals("The username cannot be empty.\n", error);
    }
}
