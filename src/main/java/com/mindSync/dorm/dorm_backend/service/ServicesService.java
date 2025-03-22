package com.mindSync.dorm.dorm_backend.service;

import com.mindSync.dorm.dorm_backend.dto.ServiceRequest;
import com.mindSync.dorm.dorm_backend.model.Services;
import com.mindSync.dorm.dorm_backend.model.User;
import com.mindSync.dorm.dorm_backend.repository.ServiceRepository;
import com.mindSync.dorm.dorm_backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class ServicesService {
    final UserRepository userRepository;
    final ServiceRepository serviceRepository;

    ServicesService(ServiceRepository serviceRepository,UserRepository userRepository)
    {
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
    }

    public String addService(ServiceRequest serviceRequest)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Retrieves email of logged-in seller

        User user = userRepository.findByUsername(userEmail)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

//        if (serviceRepository.existsByServiceAndUserId(serviceRequest.getServiceName(), user.getId())) {
//
//            return "service with the same name.";
//        }

        Services services = Services.builder()
                .serviceName(serviceRequest.getServiceName())
                .description(serviceRequest.getDescription())
                .status(serviceRequest.getDescription())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(user)
                .build();

        serviceRepository.save(services);
                return "Service saved successfully";
    }
}
