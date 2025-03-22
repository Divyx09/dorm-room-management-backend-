package com.mindSync.dorm.dorm_backend.service;

import com.mindSync.dorm.dorm_backend.dto.RoomRequest;
import com.mindSync.dorm.dorm_backend.model.Room;
import com.mindSync.dorm.dorm_backend.model.User;
import com.mindSync.dorm.dorm_backend.repository.RoomRepository;
import com.mindSync.dorm.dorm_backend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    RoomService(RoomRepository roomRepository,UserRepository userRepository)
    {

        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    public String addRoom(RoomRequest request)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Retrieves email of logged-in seller

        User user = userRepository.findByUsername(userEmail)
                .orElseThrow(() -> new RuntimeException("user not found"));

        if (roomRepository.findByRoomNumber(request.getRoomNumber()).isPresent()) {
            return "Room already exists with the same room number";
        }

        Room room = Room.builder()
                .roomNumber(request.getRoomNumber())
                .floorNo(request.getMaxCapacity())
                .isOccupied(request.getIsOccupied())
                .maxCapacity(request.getMaxCapacity())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(user)
                .build();

        roomRepository.save(room);
        return "Room added successfully";
    }

    public List<Room> gelallroom() {
        return  roomRepository.findAll();
    }
}
