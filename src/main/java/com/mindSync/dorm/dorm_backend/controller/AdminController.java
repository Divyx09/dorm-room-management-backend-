package com.mindSync.dorm.dorm_backend.controller;

import com.mindSync.dorm.dorm_backend.model.Room;
import com.mindSync.dorm.dorm_backend.model.Services;
import com.mindSync.dorm.dorm_backend.model.User;
import com.mindSync.dorm.dorm_backend.repository.UserRepository;
import com.mindSync.dorm.dorm_backend.service.RoomService;
import com.mindSync.dorm.dorm_backend.service.ServicesService;
import com.mindSync.dorm.dorm_backend.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    final UserService userService;
    final RoomService roomService;
    final ServicesService servicesService;

    AdminController(UserService userService,RoomService roomService,ServicesService servicesService)
    {
        this.userService= userService;
        this.roomService  = roomService;
        this.servicesService = servicesService;
    }

    @GetMapping("/dashboard")
    public String getAdminDashboard() {
        return "Admin dashboard data";
    }

    @GetMapping("/getallusers")
    public List<User> getallusers()
    {
        return userService.getallusers();
    }

    @GetMapping("/getallrooms")
    public List<Room> getallrooms()
    {
        return roomService.gelallroom();
    }

    @GetMapping("/getallservices")
    public List<Services> getallservices()
    {
        return servicesService.getallservices();
    }
}
