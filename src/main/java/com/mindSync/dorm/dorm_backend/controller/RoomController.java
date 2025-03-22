package com.mindSync.dorm.dorm_backend.controller;

import com.mindSync.dorm.dorm_backend.dto.RoomRequest;
import com.mindSync.dorm.dorm_backend.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/room")
public class RoomController {

    private final RoomService roomService;
    RoomController(RoomService roomService)
    {
        this.roomService = roomService;
    }

    @PostMapping("/addroom")
    public String addRoom(@Valid @RequestBody RoomRequest roomRequest)
    {
        return roomService.addRoom(roomRequest);
    }
}
