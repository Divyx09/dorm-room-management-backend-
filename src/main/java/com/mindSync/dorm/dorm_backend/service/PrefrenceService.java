package com.mindSync.dorm.dorm_backend.service;

import com.mindSync.dorm.dorm_backend.dto.PrefrenceDto;
import com.mindSync.dorm.dorm_backend.model.Prefrence;
import com.mindSync.dorm.dorm_backend.model.ProfileDetails;
import com.mindSync.dorm.dorm_backend.model.User;
import com.mindSync.dorm.dorm_backend.repository.PrefrenceRepository;
import com.mindSync.dorm.dorm_backend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrefrenceService {

    final PrefrenceRepository prefrenceRepository;

    final UserRepository userRepository;

    PrefrenceService(PrefrenceRepository prefrenceRepository,UserRepository userRepository)
    {
        this.prefrenceRepository = prefrenceRepository;
        this.userRepository = userRepository;
    }

    public String addPrefrences(PrefrenceDto prefrenceDto)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Retrieves email of logged-in seller

        User user = userRepository.findByUsername(userEmail)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        Prefrence prefrence = Prefrence.builder()
                .nightOwl(prefrenceDto.getNightOwl())
                .petFriendly(prefrenceDto.getPetFriendly())
                .smoker(prefrenceDto.getSmoker())
                .okayWithGuest(prefrenceDto.getOkayWithGuest())
                .socialize(prefrenceDto.getSocialize())
                .vegetarian(prefrenceDto.getVegetarian())
                .workFromHome(prefrenceDto.getWorkFromHome())
                .user(user)
                .build();

        prefrenceRepository.save(prefrence);

        return "prefrences added successfully";
    }

    public Prefrence getUserPrefrences()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Retrieves email of logged-in seller

        User user = userRepository.findByUsername(userEmail)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        return prefrenceRepository.findByUserId(user.getId());
    }

//    public List<ProfileDetails> getMatchedPreferenceUsers() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String userEmail = authentication.getName(); // Retrieves email of logged-in seller
//
//        User user = userRepository.findByUsername(userEmail)
//                .orElseThrow(() -> new RuntimeException("Seller not found"));
//
//        return prefrenceRepository.findByMatchingPreferences(user.getId());
//    }
}
