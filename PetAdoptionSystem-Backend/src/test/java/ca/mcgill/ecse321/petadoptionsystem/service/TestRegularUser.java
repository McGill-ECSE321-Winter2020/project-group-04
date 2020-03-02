package ca.mcgill.ecse321.petadoptionsystem.service;


import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestRegularUser {

    @Mock
    private RegularUserRepository regularUserRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private RegularUserService regularUserService;

    private static final String Username = "pedrito";
    private static final String Name = "Peter";
    private static final String House = "little house";
    private static final int phone= 123;


    @BeforeEach
    public void setMockOutput(){
        lenient().when(regularUserRepository.findRegularUserByClient(any(Account.class))).thenAnswer((InvocationOnMock invocation) ->
        {
                Account account = new Account();
                account.setUsername(Username);
                RegularUser regUser = new RegularUser();
                regUser.setClient(account);
                regUser.setName(Name);
                regUser.setHomeDescription(House);
                regUser.setPhoneNumber(phone);
                return regUser;
        });

        lenient().when(accountRepository.existsByUsername(anyString())).thenAnswer((InvocationOnMock invocation) ->
        {
            if (invocation.getArgument(0).equals(Username)){
                return true;
            }
            return false;
        });

        lenient().when(accountRepository.findAccountByUsername(anyString())).thenAnswer((InvocationOnMock invocation) ->
        {
                Account account = new Account();
                account.setUsername(Username);
                RegularUser regUser = new RegularUser();
                regUser.setClient(account);
                regUser.setName(Name);
                regUser.setHomeDescription(House);
                regUser.setPhoneNumber(phone);
                return account;

        });

        lenient().when(regularUserRepository.findAll()).thenAnswer((InvocationOnMock invocation) ->
        {
            Account account = new Account();
            account.setUsername(Username);
            RegularUser regUser = new RegularUser();
            regUser.setClient(account);
            regUser.setName(Name);
            regUser.setHomeDescription(House);
            regUser.setPhoneNumber(phone);
            List<RegularUser> lst = new ArrayList<RegularUser>();
            lst.add(regUser);
            return lst;

        });

        Answer<?> returnParam = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(regularUserRepository.save(any(RegularUser.class))).thenAnswer(returnParam);
    }

    @Test
    public void testUpdateRegularUser() {
        assertEquals(1, regularUserService.getAllRegularUsers().size());

        String name = "Pedro";
        String homedescription = "biiiiig house";
        int phonenumber = 911;

        RegularUser regularUser = null;

        //Comparing the newly to-be-changed attributes with the old existing ones
        assertNotEquals(homedescription, regularUserService.getRegularUserByUsername(Username).getHomeDescription());
        assertNotEquals(phonenumber, regularUserService.getRegularUserByUsername(Username).getPhoneNumber());
        assertNotEquals(name, regularUserService.getRegularUserByUsername(Username).getName());


        try {
            regularUser = regularUserService.updateRegularUser(Username, name, homedescription, phonenumber);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }

        // Comparing the newly updated ones
        assertNotNull(regularUser);
        assertEquals(name, regularUser.getName());
        assertEquals(homedescription, regularUser.getHomeDescription());
        assertEquals(phonenumber, regularUser.getPhoneNumber());
    }


    @Test
    public void testUpdateRegularUserNullUsername() {

        String name = null;
        String username = null;
        String homedescription = null;
        int phonenumber = 0;
        String error = "";

        RegularUser regularUser = null;

        try {
            regularUser = regularUserService.updateRegularUser(username, name, homedescription, phonenumber);
        } catch (IllegalArgumentException e) {
            error += e.getMessage();
            // Check that no error occurred
        }

        assertNull(regularUser);

        assertEquals("Username field cannot be empty !", error);
    }

    @Test
    public void testUpdateRegularUserSpaceUsername() {

        String name = null;
        String username = " ";
        String homedescription = null;
        int phonenumber = 0;
        String error = "";

        RegularUser regularUser = null;

        try {
            regularUser = regularUserService.updateRegularUser(username, name, homedescription, phonenumber);
        } catch (IllegalArgumentException e) {
            error += e.getMessage();
            // Check that no error occurred
        }

        assertNull(regularUser);

        assertEquals("Username field cannot be empty !", error);
    }

    @Test
    public void testUpdateRegularUserEmptyUsername() {

        String name = null;
        String username = "";
        String homedescription = null;
        int phonenumber = 0;
        String error = "";

        RegularUser regularUser = null;

        try {
            regularUser = regularUserService.updateRegularUser(username, name, homedescription, phonenumber);
        } catch (IllegalArgumentException e) {
            error += e.getMessage();
            // Check that no error occurred
        }

        assertNull(regularUser);

        assertEquals("Username field cannot be empty !", error);
    }

    @Test
    public void testUpdateRegularUserNonExistingUsername() {

        String name = null;
        String username = "Edem";
        String homedescription = null;
        int phonenumber = 0;
        String error = "";

        RegularUser regularUser = null;

        try {
            regularUser = regularUserService.updateRegularUser(username, name, homedescription, phonenumber);
        } catch (IllegalArgumentException e) {
            error += e.getMessage();
            // Check that no error occurred
        }

        assertNull(regularUser);

        assertEquals("No user associated with username" + username, error);
    }

    @Test
    public void testGetAllRegularUsers() {
        List<RegularUser> regularUsers = regularUserService.getAllRegularUsers();

        assertEquals(1, regularUsers.size());
        int id = regularUserService.getRegularUserByUsername(Username).getId();
        assertEquals(id, regularUsers.get(0).getId());

    }

    @Test
    public void testGetRegularUserByUsername() {
        assertEquals(1, regularUserService.getAllRegularUsers().size());

        RegularUser regularUser = null;

        try { regularUser = regularUserService.getRegularUserByUsername(Username);
        }
        catch (IllegalArgumentException error) {
            fail();
        }

        assertNotNull(regularUser);
        assertEquals(regularUser.getName(), regularUserService.getRegularUserByUsername(Username).getName());
    }

    @Test
    public void testGetRegularUserByUsernameEmptyUsername() {
        assertEquals(1, regularUserService.getAllRegularUsers().size());

        String username = "";
        String error = "";
        RegularUser regularUser = null;

        try { regularUser = regularUserService.getRegularUserByUsername(username);
        }
        catch (IllegalArgumentException e) {
            error += e.getMessage();
        }

        assertNull(regularUser);
        assertEquals(error, "Username field cannot be empty !");
    }

    @Test
    public void testGetRegularUserByUsernameSpaceUsername() {
        assertEquals(1, regularUserService.getAllRegularUsers().size());

        String username = " ";
        String error = "";
        RegularUser regularUser = null;

        try { regularUser = regularUserService.getRegularUserByUsername(username);
        }
        catch (IllegalArgumentException e) {
            error += e.getMessage();
        }

        assertNull(regularUser);
        assertEquals(error, "Username field cannot be empty !");
    }

    @Test
    public void testGetRegularUserByUsernameNullUsername() {
        assertEquals(1, regularUserService.getAllRegularUsers().size());

        String username = null;
        String error = "";
        RegularUser regularUser = null;

        try { regularUser = regularUserService.getRegularUserByUsername(username);
        }
        catch (IllegalArgumentException e) {
            error += e.getMessage();
        }

        assertNull(regularUser);
        assertEquals(error, "Username field cannot be empty !");
    }

    @Test
    public void testGetRegularUserByUsernameNonExistingUsername() {
        assertEquals(1, regularUserService.getAllRegularUsers().size());

        String username = "Juanita";
        String error = "";
        RegularUser regularUser = null;

        try { regularUser = regularUserService.getRegularUserByUsername(username);
        }
        catch (IllegalArgumentException e) {
            error += e.getMessage();
        }

        assertNull(regularUser);
        assertEquals(error, "No existing user with the given username");
    }


}
