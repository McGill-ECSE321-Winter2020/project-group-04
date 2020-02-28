package ca.mcgill.ecse321.petadoptionsystem.controller;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.petadoptionsystem.dto.DonationDTO;
import ca.mcgill.ecse321.petadoptionsystem.dto.RegularUserDTO;
import ca.mcgill.ecse321.petadoptionsystem.model.Donation;
import ca.mcgill.ecse321.petadoptionsystem.model.RegularUser;
import ca.mcgill.ecse321.petadoptionsystem.service.DonationService;
import ca.mcgill.ecse321.petadoptionsystem.service.RegularUserService;

@CrossOrigin(origins = "*")
@RestController
public class DonationRestController {

    @Autowired
    private DonationService donationService;

    // @Autowired
    // private RegularUserService reguserService;

    @PostMapping(value = { "/donations/{name}", "/donation/{name}" })
    public DonationDTO createDonation(@PathVariable("name") String name, @RequestParam Date date,
            @RequestParam float amount,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
            @RequestBody RegularUserDTO regUserDTO) throws IllegalArgumentException {

        Donation d = donationService.createDonation(amount, date, Time.valueOf(startTime), name);

        return convertDonationToDTO(d);
    }

    @GetMapping(value = { "/donations", "/donations/" })
    public List<DonationDTO> getAllEvents() {
        List<DonationDTO> donationDtos = new ArrayList<>();
        for (Donation d : donationService.getAllDonation()) {
            donationDtos.add(convertDonationToDTO(d));
        }
        return donationDtos;
    }

    public DonationDTO convertDonationToDTO(Donation d) {
        if (d == null)
            throw new IllegalArgumentException("There is no such Donation!");
        RegularUserDTO regDTO = new RegularUserDTO(d.getUser().getDonation(), d.getUser().getUser().getUsername(), d.getUser().getName(),
                d.getUser().getApplication(), d.getUser().getHomeDescription(), d.getUser().getPhoneNumber());
        return new DonationDTO(regDTO, d.getTime(), d.getDate(), d.getAmount());
    }

    

}