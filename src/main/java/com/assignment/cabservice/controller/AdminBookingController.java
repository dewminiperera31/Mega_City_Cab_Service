package com.assignment.cabservice.controller;

import com.assignment.cabservice.model.CustomerBooking;
import com.assignment.cabservice.repository.CustomerBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;




import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Controller
public class AdminBookingController {

    @Autowired
    private CustomerBookingRepository customerBookingRepository;


    @GetMapping("/admin-booking-list")
    public String showAdminBookings(Model model) {
        List<CustomerBooking> allBookings = customerBookingRepository.findAll();
        model.addAttribute("bookings", allBookings);
        return "admin-booking-list";
    }

    @PostMapping("/confirm/{id}")
    public String confirmBooking(@PathVariable Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Booking ID cannot be null");
        }
        Optional<CustomerBooking> bookingOptional = customerBookingRepository.findById(id);

        if (bookingOptional.isPresent()) {
            CustomerBooking booking = bookingOptional.get();
            booking.setStatus("Confirmed"); // Set status to Confirmed
            customerBookingRepository.save(booking);
        }

        return "redirect:/admin-booking-list";
    }
    @PostMapping("/cancel/{id}")
    public String cancelBooking(@PathVariable Long id) {
        Optional<CustomerBooking> bookingOptional = customerBookingRepository.findById(id);

        if (bookingOptional.isPresent()) {
            CustomerBooking booking = bookingOptional.get();
            booking.setStatus("Cancelled"); // Set status to Cancelled
            customerBookingRepository.save(booking);
        }

        return "redirect:/admin-booking-list";

    }



}
