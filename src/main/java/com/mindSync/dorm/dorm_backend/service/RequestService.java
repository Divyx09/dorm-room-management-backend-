package com.mindSync.dorm.dorm_backend.service;

import com.mindSync.dorm.dorm_backend.dto.RequestDto;
import com.mindSync.dorm.dorm_backend.model.Request;
import com.mindSync.dorm.dorm_backend.model.User;
import com.mindSync.dorm.dorm_backend.repository.RequestRepository;
import com.mindSync.dorm.dorm_backend.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RequestService {

    final RequestRepository requestRepository;
    final UserRepository userRepository;

    RequestService(RequestRepository requestRepository,UserRepository userRepository)
    {
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
    }

    public String addRequest(RequestDto requestDto)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // Retrieves email of logged-in seller

        User user = userRepository.findByUsername(userEmail)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        Request request = Request.builder()
                .requestType(requestDto.getRequestType())
                .description(requestDto.getDescription())
                .status(requestDto.getStatus())
                .title(requestDto.getTitle())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(user)
                .build();

        requestRepository.save(request);

        return "Request added successfully";
    }

    public List<RequestDto> getUsersRequests() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return requestRepository.findRequestsByUserId(user.getId());
    }

}
