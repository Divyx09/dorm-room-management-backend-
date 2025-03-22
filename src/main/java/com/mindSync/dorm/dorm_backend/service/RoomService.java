package com.mindSync.dorm.dorm_backend.service;

import com.mindSync.dorm.dorm_backend.dto.RoomRequest;
import com.mindSync.dorm.dorm_backend.model.Room;
import com.mindSync.dorm.dorm_backend.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class RoomService {

    private final RoomRepository roomRepository;

    RoomService(RoomRepository roomRepository)
    {
        this.roomRepository = roomRepository;
    }

    public String addRoom(RoomRequest request)
    {
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
                .build();

        roomRepository.save(room);
        return "Room added successfully";
    }
}
