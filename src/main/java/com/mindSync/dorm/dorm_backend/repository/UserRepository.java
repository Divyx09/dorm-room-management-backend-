package com.mindSync.dorm.dorm_backend.repository;

import com.mindSync.dorm.dorm_backend.model.Room;
import com.mindSync.dorm.dorm_backend.model.User;
import org.apache.el.stream.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    // Find all users in the same room, excluding the logged-in user
    List<User> findByRoomNumberAndUsernameNot(String roomNumber, String username);

//    @Query("SELECT r FROM users r WHERE r.room.id = :roomId")
//    Room findByUserId(@Param("userId") Long roomId);
}
