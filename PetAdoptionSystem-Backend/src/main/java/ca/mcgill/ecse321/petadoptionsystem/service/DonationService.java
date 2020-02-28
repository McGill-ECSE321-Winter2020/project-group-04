package ca.mcgill.ecse321.petadoptionsystem.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.petadoptionsystem.dao.DonationRepository;
import ca.mcgill.ecse321.petadoptionsystem.model.Donation;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;


public class DonationService {
    @Autowired
    private DonationRepository donationRepository;

    @Transactional
    public Donation createDonation(float amount, Date date, Time time, RegularUser user){
        //if(amount ==0 || date==null || time==null || user || == null) throw new 
        Donation donation = new Donation();
        donation.setAmount(amount);
        donation.setDate(date);
        donation.setTime(time);
        donation.setUser(user);

        donationRepository.save(donation);

        return donation;
    }
    @Transactional 
    public List<Donation> getAllDonation(){
        return toList(donationRepository.findAll());
    }

    @Transactional
    public List<Donation> getDonationsByUserId(int id){
        List<Donation> donationsByUser = new ArrayList<>();
        for(Donation d : donationRepository.findDonationByUser_id(id)){
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