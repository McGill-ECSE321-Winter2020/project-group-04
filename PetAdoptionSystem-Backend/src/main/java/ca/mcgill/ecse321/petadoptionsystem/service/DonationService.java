package ca.mcgill.ecse321.petadoptionsystem.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.DonationRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;
import ca.mcgill.ecse321.petadoptionsystem.exception.AmountInvalidException;
import ca.mcgill.ecse321.petadoptionsystem.exception.NullUsernameException;
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.Donation;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;
/**
 * @author Ousmane Baricisse
 * Service class for Donation
 */

@Service
public class DonationService {
    @Autowired
    private DonationRepository donationRepo;

    @Autowired
    private AccountRepository actRepo;

    @Autowired
    private RegularUserRepository regUserRepo;

    /**
     * 
     * @param amount
     * @param date
     * @param time
     * @param username
     * @return
     */
    @Transactional
    public Donation createDonation(float amount, Date date, Time time, String username){
        
        if(amount ==0) throw new AmountInvalidException("Please donate an amount greater than 0. Thank you for your Donation");
        if(date==null) throw new NullPointerException("Date is currently null. Plense enter correct date value");
        if(time==null) throw new NullPointerException("Time is currently null. Plense enter correct time value");
        if(username == null || username.trim()=="") throw new NullUsernameException("The username is null or empty. Please input a username of length >= 1");
        
        Donation donation = new Donation();
        donation.setAmount(amount);
        donation.setDate(date);
        donation.setTime(time);
        Account act =  actRepo.findAccountByUsername(username);
        RegularUser regUser = regUserRepo.findRegularUserByUser(act);
        donation.setUser(regUser);

        donationRepo.save(donation);

        return donation;
    }
    @Transactional 
    public List<Donation> getAllDonation(){
        return toList(donationRepo.findAll());
    }

    @Transactional
    public List<Donation> getDonationsByUsername(String username){
        if(username == null || username.trim()=="") throw new IllegalArgumentException("The username cannot be empty or have spaces.\n");

        Account act =  actRepo.findAccountByUsername(username);
        RegularUser regUser = regUserRepo.findRegularUserByUser(act);
        if(donationRepo.findDonationsByUser(regUser) == null)
            throw new IllegalArgumentException("No donations associated with this username.\n");

        return toList(donationRepo.findDonationsByUser(regUser));
    }


    private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}