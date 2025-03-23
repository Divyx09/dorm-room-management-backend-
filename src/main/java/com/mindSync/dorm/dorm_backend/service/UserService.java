package com.mindSync.dorm.dorm_backend.service;

import com.mindSync.dorm.dorm_backend.dto.ProfileDetailsDto;
import com.mindSync.dorm.dorm_backend.dto.RequestDto;
import com.mindSync.dorm.dorm_backend.dto.UserRequest;
import com.mindSync.dorm.dorm_backend.model.User;
import com.mindSync.dorm.dorm_backend.model.ProfileDetails;
import com.mindSync.dorm.dorm_backend.repository.RequestRepository;
import com.mindSync.dorm.dorm_backend.repository.RoomRepository;
import com.mindSync.dorm.dorm_backend.repository.ProfileDetailsRepository;
import com.mindSync.dorm.dorm_backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    final UserRepository userRepository;
    final RoomRepository roomRepository;
    final RequestRepository requestRepository;
    final ProfileDetailsRepository userDetailsRepository;

    UserService(UserRepository userRepository, RoomRepository roomRepository, RequestRepository requestRepository, ProfileDetailsRepository userDetailsRepository)
    {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.requestRepository = requestRepository;
        this.userDetailsRepository = userDetailsRepository;
    }

    public String allocalteRoom(UserRequest userRequest)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Retrieves email of logged-in seller

        User user = userRepository.findByUsername(userEmail)
                .orElseThrow(() -> new RuntimeException("user not found"));

        // Check if the user already has a room allocated
        if (user.getRoomNumber() != null) {
            return "Room already allocated for this user";
        }
        user.setRoomNumber(userRequest.getRoomNumber());
        userRepository.save(user);

        return "Room allocated successfully";
    }

    public String updateProfileDetails(ProfileDetailsDto userDetailsDto)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Retrieves email of logged-in seller

        User user = userRepository.findByUsername(userEmail)
                .orElseThrow(() -> new RuntimeException("user not found"));

        ProfileDetails userDetails = new ProfileDetails().builder()
                .branch(userDetailsDto.getBranch())
                .name(user.getName())
                .dob(userDetailsDto.getDob())
                .course(userDetailsDto.getCourse())
                .gender(userDetailsDto.getGender())
                .phoneNo(userDetailsDto.getPhoneNo())
                .occupation(userDetailsDto.getOccupation())
                .user(user)
                .build();

        userDetailsRepository.save(userDetails);
        return "Profile details updated successfully.";
    }

    public Optional<ProfileDetails> getProfileDetails()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Retrieves email of logged-in seller

        User user = userRepository.findByUsername(userEmail)
                .orElseThrow(() -> new RuntimeException("user not found"));

        return userDetailsRepository.findByUserId(user.getId());
    }

    public List<User> getallusers() {

        return userRepository.findAll();
    }
}
