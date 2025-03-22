package com.mindSync.dorm.dorm_backend.controller;

import com.mindSync.dorm.dorm_backend.dto.ServiceRequest;
import com.mindSync.dorm.dorm_backend.service.ServicesService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/services")
public class ServiceController {

    final ServicesService servicesService;

    ServiceController(ServicesService servicesService)
    {
        this.servicesService = servicesService;
    }

    @PostMapping("/addservice")
    public String addService(@Valid @RequestBody ServiceRequest serviceRequest)
    {
        return servicesService.addService(serviceRequest);
    }
}
