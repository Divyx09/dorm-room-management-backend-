package com.mindSync.dorm.dorm_backend.repository;

import com.mindSync.dorm.dorm_backend.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room,Long> {
    Optional<Room> findByRoomNumber(String roomNumber);

}
