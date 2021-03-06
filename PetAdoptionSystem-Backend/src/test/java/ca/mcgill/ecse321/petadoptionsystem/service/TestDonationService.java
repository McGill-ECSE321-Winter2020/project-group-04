package ca.mcgill.ecse321.petadoptionsystem.service;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.DonationRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;
import ca.mcgill.ecse321.petadoptionsystem.exception.NullUsernameException;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.Donation;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.sql.Time;
import java.util.*;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class TestDonationService {
    @Mock
    private AccountRepository accountDao;

    @Mock
    private RegularUserRepository regularUserDao;

    @Mock
    private DonationRepository donationDao;

    @InjectMocks
    private DonationService donationService;

    private static final String USERNAME1 = "Will";
    private static final String NON_EXISTING_USERNAME = "Samantha";
    private static final float AMOUNT1 = 50;
    private static final String DONOREMAIL = "donor@gmail.com";

    @AfterEach
    public void clearDataBase(){
        accountDao.deleteAll();
        regularUserDao.deleteAll();
        donationDao.deleteAll();
    }

    /**
     * set mock output
     */
    @BeforeEach
    public void setMockOutput(){
        lenient().when(accountDao.findAccountByUsername(USERNAME1)).thenAnswer((InvocationOnMock invocation) ->
        {
            Account account = new Account();
            account.setUsername(USERNAME1);
            return account;
        });

        lenient().when(regularUserDao.findRegularUserByClient(any(Account.class))).thenAnswer((InvocationOnMock invocation) ->
        {
            Account account = new Account();
            account.setUsername(USERNAME1);
            RegularUser regularUser = new RegularUser();
            regularUser.setClient(account);
            return regularUser;
        });

        lenient().when(donationDao.findDonationsByDonorName(anyString())).thenAnswer((InvocationOnMock invocation) ->
        {
           
            Donation donation = new Donation();
            donation.setAmount(AMOUNT1);

            donation.setDonorName(USERNAME1);
            donation.setDonorEmail(DONOREMAIL);
            Set<Donation> donations = new HashSet<Donation>();
            donations.add(donation);

            return toList(donations);
        });

        lenient().when(donationDao.findDonationById(anyInt())).thenAnswer((InvocationOnMock invocation) ->
        {
            
            Donation donation = new Donation();
            donation.setAmount(AMOUNT1);
            donation.setDonorEmail(DONOREMAIL);
            donation.setDonorName(USERNAME1);

            return donation;
        });

        lenient().when(donationDao.findAll()).thenAnswer((InvocationOnMock invocation) ->
        {
            Donation donation = new Donation();
            donation.setAmount(AMOUNT1);

            donation.setDonorName(USERNAME1);
            donation.setDonorEmail(DONOREMAIL);
            Set<Donation> donations1 = new HashSet<Donation>();
            donations1.add(donation);

            return toList(donations1);
        });

        Answer<?> returnParam = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(donationDao.save(any(Donation.class))).thenAnswer(returnParam);

    }

    @Test
    public void testExistingDonation(){
        assertEquals(donationService.getDonationsByUsername(USERNAME1).get(0).getDonorName(), USERNAME1);
    }

    @Test
    public void testCreateDonation(){
        //String username = "Bill";
        float amount = 150;
        Calendar cal = Calendar.getInstance();
        cal.set(2020, Calendar.MARCH, 1, 12, 0, 0);
        Date date = new Date(cal.getTimeInMillis());
        Time time = new Time(cal.getTimeInMillis());
        int donId = 0;

        Donation donation = null;
        try {
            donation = donationService.createDonation(amount, date, time, USERNAME1, DONOREMAIL);
            donId = donation.getId();
        }catch(IllegalArgumentException e){
            fail();
        }

        assertNotNull(donation);
        assertEquals(amount, donation.getAmount());
        assertEquals(time, donation.getTime());
        assertEquals(date, donation.getDate());
        assertEquals(USERNAME1, donation.getDonorName());
        assertEquals(donId, donation.getId());

    }

    @Test
    public void testGetAllDonation(){
        List<Donation> donations = donationService.getAllDonation();
        assertEquals(1, donations.size());
        int donId = donationService.getDonationsByUsername(USERNAME1).get(0).getId();
        assertEquals(donations.get(0).getId(), donId);
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

}
