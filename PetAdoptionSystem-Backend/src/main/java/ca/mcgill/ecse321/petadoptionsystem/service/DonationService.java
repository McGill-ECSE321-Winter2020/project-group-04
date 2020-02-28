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
import ca.mcgill.ecse321.petadoptionsystem.model.Account;
import ca.mcgill.ecse321.petadoptionsystem.model.Donation;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;

@Service
public class DonationService {
    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private AccountRepository actRepo;

    @Autowired
    private RegularUserRepository regUserRepo;

    @Transactional
    public Donation createDonation(float amount, Date date, Time time, String username){
        //if(amount ==0 || date==null || time==null || user || == null) throw new 
        Donation donation = new Donation();
        donation.setAmount(amount);
        donation.setDate(date);
        donation.setTime(time);
        Account act =  actRepo.findAccountByUsername(username);
        RegularUser regUser = regUserRepo.findRegularUserByUser(act);
        donation.setUser(regUser);

        donationRepository.save(donation);

        return donation;
    }
    @Transactional 
    public List<Donation> getAllDonation(){
        return toList(donationRepository.findAll());
    }

    @Transactional
    public List<Donation> getDonationsByUsername(String username){
        List<Donation> donationsByUser = new ArrayList<>();
        Account act =  actRepo.findAccountByUsername(username);
        RegularUser regUser = regUserRepo.findRegularUserByUser(act);
        
        for(Donation d : donationRepository.findDonationByUser_id(regUser.getId())){
            donationsByUser.add(d);
        }

        return donationsByUser;
    }

    private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}