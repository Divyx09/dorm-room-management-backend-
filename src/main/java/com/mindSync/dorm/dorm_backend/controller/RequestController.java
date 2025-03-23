package com.mindSync.dorm.dorm_backend.controller;

import com.mindSync.dorm.dorm_backend.dto.RequestDto;
import com.mindSync.dorm.dorm_backend.service.RequestService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins="http://lcoalhost:5173")
public class RequestController {

    final RequestService requestService;

    RequestController(RequestService requestService)
    {
        this.requestService = requestService;
    }

    @PostMapping("/addrequest")
    public String addReuest(@Valid @RequestBody RequestDto requestDto)
    {
        return requestService.addRequest(requestDto);
    }
}
