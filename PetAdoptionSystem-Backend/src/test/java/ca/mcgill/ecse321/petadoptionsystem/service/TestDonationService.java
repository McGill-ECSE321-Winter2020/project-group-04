package ca.mcgill.ecse321.petadoptionsystem.service;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.DonationRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.Admin;
import ca.mcgill.ecse321.petadoptionsystem.model.Donation;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Time;
import java.time.LocalTime;
import java.util.*;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
    private static final String USERNAME2 = "Samantha";
    private static final float AMOUNT1 = 50;
    private static final float AMOUNT2 = 100;

    @AfterEach
    public void clearDataBase(){
        accountDao.deleteAll();
        regularUserDao.deleteAll();
        donationDao.deleteAll();
    }

    @BeforeEach
    public void setMockOutput(){
        lenient().when(accountDao.findAccountByUsername(USERNAME1)).thenAnswer((InvocationOnMock invocation) ->
        {
            Account account = new Account();
            account.setUsername(USERNAME1);
            return account;
        });

        lenient().when(regularUserDao.findRegularUserByUser(any(Account.class))).thenAnswer((InvocationOnMock invocation) ->
        {
            Account account = new Account();
            account.setUsername(USERNAME1);
            RegularUser regularUser = new RegularUser();
            regularUser.setUser(account);
            return regularUser;
        });

        lenient().when(donationDao.getDonationsByUser(any(RegularUser.class))).thenAnswer((InvocationOnMock invocation) ->
        {
            Account account = new Account();
            account.setUsername(USERNAME1);
            RegularUser regularUser = new RegularUser();
            regularUser.setUser(account);
            Donation donation = new Donation();
            donation.setAmount(AMOUNT1);
            donation.setUser(regularUser);

            Set<Donation> donations = new HashSet<Donation>();
            donations.add(donation);
            regularUser.setDonation(donations);
            //return donation;
            return toList(donations);
        });

        lenient().when(donationDao.findDonationById(anyInt())).thenAnswer((InvocationOnMock invocation) ->
        {
            Account account = new Account();
            account.setUsername(USERNAME1);
            RegularUser regularUser = new RegularUser();
            regularUser.setUser(account);
            Donation donation = new Donation();
            donation.setAmount(AMOUNT1);
            Set<Donation> donations = new HashSet<Donation>();
            donations.add(donation);
            regularUser.setDonation(donations);
            return donation;
        });

        lenient().when(donationDao.findAll()).thenAnswer((InvocationOnMock invocation) ->
        {
            Account account1 = new Account();
            account1.setUsername(USERNAME1);
            RegularUser regularUser1 = new RegularUser();
            regularUser1.setUser(account1);
            Donation donation1 = new Donation();
            donation1.setAmount(AMOUNT1);

            Set<Donation> donations1 = new HashSet<Donation>();
            donations1.add(donation1);
            regularUser1.setDonation(donations1);

            Account account2 = new Account();
            account2.setUsername(USERNAME2);
            RegularUser regularUser2 = new RegularUser();
            regularUser2.setUser(account2);
            Donation donation2 = new Donation();
            donation2.setAmount(AMOUNT2);

            Set<Donation> donations2 = new HashSet<Donation>();
            donations2.add(donation2);
            regularUser2.setDonation(donations2);

//            Set<Donation> allDonations = new HashSet<Donation>();
//            allDonations.add()

            return toList(donations2);
        });

        Answer<?> returnParam = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(donationDao.save(any(Donation.class))).thenAnswer(returnParam);

    }

//    @Test
//    public void testExistingDonation(){
//        assertEquals(donationService.getDonationsByUsername(USERNAME1).get(0).getUser().getUser().getUsername(), USERNAME1);
//    }

    @Test
    public void testExistingDonation(){
        assertEquals(donationService.getDonationsByUsername(USERNAME1).get(0).getUser().getUser().getUsername(), USERNAME1);
    }

    @Test
    public void createDonation(){
        //String username = "Bill";
        float amount = 150;
        Calendar cal = Calendar.getInstance();
        cal.set(2020, Calendar.MARCH, 1, 12, 0, 0);
        Date date = new Date(cal.getTimeInMillis());
        Time time = new Time(cal.getTimeInMillis());
        int donId = 0;

        Donation donation = null;
        try {
            donation = donationService.createDonation(amount, date, time, USERNAME1);
            donId = donation.getId();
        }catch(IllegalArgumentException e){
            fail();
        }

        assertNotNull(donation);
        assertEquals(amount, donation.getAmount());
        assertEquals(time, donation.getTime());
        assertEquals(date, donation.getDate());
        assertEquals(USERNAME1, donation.getUser().getUser().getUsername());
        assertEquals(donId, donation.getId());

    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

}
