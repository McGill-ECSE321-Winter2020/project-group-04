package ca.mcgill.ecse321.petadoptionsystem.controller;


import ca.mcgill.ecse321.petadoptionsystem.dao.AccountRepository;
import ca.mcgill.ecse321.petadoptionsystem.dao.RegularUserRepository;
import ca.mcgill.ecse321.petadoptionsystem.dto.RegularUserDTO;
import ca.mcgill.ecse321.petadoptionsystem.model.*;
import ca.mcgill.ecse321.petadoptionsystem.service.RegularUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class RegularUserRestController {

    @Autowired
    private RegularUserService regularUserService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    RegularUserRepository regularUserRepository;



    /**
     *
     * @return all regular users
     * @throws IllegalArgumentException error
     */
    @GetMapping( value = { "/regularusers", "/regularusers/" })
    public List<RegularUserDTO> getAllRegularUsers() throws IllegalArgumentException{
        List<RegularUserDTO> regDtos = new ArrayList<>();

        for (RegularUser reg : regularUserService.getAllRegularUsers()) {
            regDtos.add(convertToDto(reg));
        }
        return regDtos;
    }

    /**
     *
     * @param username name of sought user
     * @return profile of sought user
     * @throws IllegalArgumentException error
     */
    @GetMapping( value = { "/regularuser/{username}", "/regularusers/{username}/" })
    public RegularUserDTO getRegularUsersByUser(@PathVariable("username") String username) throws IllegalArgumentException{

        RegularUser reg =  regularUserService.getRegularUserByUsername(username);

        return convertToDto(reg);
    }


    /**
     *
     * @param regularUserDTO object to be changed
     * @param username of the profile to be updated
     * @return the updated object
     * @throws IllegalArgumentException error
     */
    @PutMapping(value = { "/updateregularuser/{username}", "/updateregularuser/{username}/"})
    public RegularUserDTO updateRegularUser(
            @RequestBody RegularUserDTO regularUserDTO,
            @PathVariable ("username") String username)
            throws IllegalArgumentException{

        RegularUser reg = regularUserService.updateRegularUser(username, regularUserDTO.getName(),
                regularUserDTO.getHomeDescription(), regularUserDTO.getPhoneNumber());


        return convertToDto(reg);

    }


    private RegularUserDTO convertToDto(RegularUser reg) {
        if (reg == null) {
            throw new IllegalArgumentException("There is no such User!");
        }

        RegularUserDTO regDto = new RegularUserDTO(reg.getClient().getUsername(), reg.getName(), reg.getApplication(), reg.getHomeDescription(),
                reg.getPhoneNumber());

        return regDto;
    }

}
