package com.assignment.cabservice.controller;

import com.assignment.cabservice.model.CustomerBooking;
import com.assignment.cabservice.repository.CustomerBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.security.Principal;
import java.util.List;




import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Controller
public class CustomerBookingController {

    @Autowired
    private CustomerBookingRepository customerBookingRepository;

    @RequestMapping("/add-booking")
    public String showBookingForm(Model model) {
        model.addAttribute("booking", new CustomerBooking());
        return "customer-booking";
    }

    @PostMapping("/add-booking")
    public String addBooking(@ModelAttribute CustomerBooking booking) {
        // Ensure Order Number is not null
        if (booking.getOrderNumber() == null || booking.getOrderNumber().trim().isEmpty()) {
            booking.setOrderNumber("ORD-" + System.currentTimeMillis()); // Generate Unique Order Number
        }

        // Ensure Status is not null
        if (booking.getStatus() == null || booking.getStatus().trim().isEmpty()) {
            booking.setStatus("Pending"); // Default status
        }

        // Ensure Booking Date is not null
        if (booking.getBookingDate() == null) {
            booking.setBookingDate(LocalDate.now()); // Default to today
        }

        // Ensure Booking Time is not null
        if (booking.getBookingTime() == null) {
            booking.setBookingTime(LocalTime.now().withSecond(0).withNano(0)); // Default to now
        }

        // Ensure Pickup Location is not null
        if (booking.getPickupLocation() == null || booking.getPickupLocation().trim().isEmpty()) {
            booking.setPickupLocation("Not Specified"); // Default if not provided
        }

        // Save the booking to the database
        customerBookingRepository.save(booking);

        return "redirect:/booking-list"; // Redirect to the booking list page
    }

    @GetMapping("/booking-list")
    public String showBookings(Model model,Principal principal) {
        if (principal == null) {
            return "redirect:/login"; // Redirect if user is not logged in
        }

        String loggedInUsername = principal.getName(); // Get logged-in user's username
        List<CustomerBooking> userBookings = customerBookingRepository.findByCustomerName(loggedInUsername);

        model.addAttribute("bookings", userBookings);
        return "booking-list";
    }

    // Cancel Booking Only if it's "Pending"
    @PostMapping("/bookings/cancel/{id}")
    public String cancelBooking(@PathVariable Long id) {
        Optional<CustomerBooking> bookingOptional = customerBookingRepository.findById(id);


        if (bookingOptional.isPresent()) {
            CustomerBooking booking = bookingOptional.get();

            if ("Pending".equalsIgnoreCase(booking.getStatus())) {
                customerBookingRepository.deleteById(id);

            }
        }

        return "redirect:/booking-list";
    }
}
