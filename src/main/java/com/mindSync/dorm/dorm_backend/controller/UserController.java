package com.mindSync.dorm.dorm_backend.controller;

import com.mindSync.dorm.dorm_backend.dto.*;
import com.mindSync.dorm.dorm_backend.model.*;
import com.mindSync.dorm.dorm_backend.repository.RequestRepository;
import com.mindSync.dorm.dorm_backend.service.PrefrenceService;
import com.mindSync.dorm.dorm_backend.service.RequestService;
import com.mindSync.dorm.dorm_backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    final UserService userService;
    final PrefrenceService prefrenceService;

    final RequestService requestService;

    UserController(UserService userService,PrefrenceService prefrenceService,RequestService requestService)
    {
        this.userService = userService;
        this.prefrenceService = prefrenceService;
        this.requestService = requestService;
    }

    @GetMapping("/profile")
    public ResponseEntity<String> getUserProfile() {
        System.out.println("🚀 User profile endpoint hit!");
        return ResponseEntity.ok("User profile data retrieved successfully!");
    }

    @PostMapping("/roomrequest")
    public String allocateRoom(@Valid @RequestBody UserRequest userRequest)
    {
        return userService.allocalteRoom(userRequest);
    }

    @PostMapping("/addprefrences")
    public String addPrefrences(@Valid @RequestBody PrefrenceDto prefrenceDto)
    {
        return prefrenceService.addPrefrences(prefrenceDto);
    }

    @PostMapping("/addprofiledetails")
    public String addProfileDetails(@Valid @RequestBody ProfileDetailsDto userDetailsDto)
    {
        return userService.updateProfileDetails(userDetailsDto);
    }

    @GetMapping("/getProfileDetails")
    public Optional<ProfileDetails> getProfileDetails(){
        return userService.getProfileDetails();
    }

    @GetMapping("/getuserprefrences")
    public Prefrence getUserPrefrences()
    {
        return prefrenceService.getUserPrefrences();
    }

//    @GetMapping("/mathcedprefrencesusers")
//    public List<ProfileDetails> matchedPrefrencedUsers()
//    {
//        return prefrenceService.getMatchedPreferenceUsers();
//    }

    @GetMapping("/getuserrequests")
    public List<RequestDto> getUsersRequests() {
        return requestService.getUsersRequests();
    }

    @GetMapping("/getusertasks")
    public List<TaskRequest> findTasksForUser()
    {
        return userService.findTasksForUser();
    }



}
