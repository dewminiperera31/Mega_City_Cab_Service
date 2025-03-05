package com.assignment.cabservice.controller;

import com.assignment.cabservice.model.CarRequest;
import com.assignment.cabservice.repository.CarRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TestCarRequestController {

    @Autowired
    private CarRequestRepository carRequestRepository;

    @GetMapping("/test-car-requests") // New URL
    public String testCarRequests(ModelMap model) {
        System.out.println("🚀 Fetching car requests...");
        List<CarRequest> carRequests = carRequestRepository.findAll();
        System.out.println("✅ Found " + carRequests.size() + " car requests!");

        model.addAttribute("car_requests", carRequests);
        return "testCarRequests1"; // Refers to testCarRequests.jsp
    }
}

