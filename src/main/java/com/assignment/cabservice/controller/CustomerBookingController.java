package com.assignment.cabservice.controller;

import com.assignment.cabservice.model.CustomerBooking;
import com.assignment.cabservice.repository.CustomerBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
public class CustomerBookingController {

    @Autowired
    private CustomerBookingRepository customerBookingRepository;

    // Show booking form
    @RequestMapping("/add-booking")
    public String showBookingForm(Model model) {
        model.addAttribute("booking", new CustomerBooking());
        return "customer-booking";
    }

    // Process booking and redirect to bill summary
    @PostMapping("/add-booking")
    public String addBooking(@ModelAttribute CustomerBooking booking) {
        if (booking.getOrderNumber() == null || booking.getOrderNumber().trim().isEmpty()) {
            booking.setOrderNumber("ORD-" + System.currentTimeMillis()); // Unique order number
        }

        booking.setStatus("Pending");
        booking.setBookingDate(LocalDate.now());
        booking.setBookingTime(LocalTime.now().withSecond(0).withNano(0));

        // Calculate bill
        double baseFare = calculateBaseFare(booking.getPickupLocation(), booking.getDestination());
        double tax = baseFare * 0.1; // 10% tax
        double totalAmount = baseFare + tax;

        booking.setBaseFare(baseFare);
        booking.setTax(tax);
        booking.setTotalAmount(totalAmount);

        customerBookingRepository.save(booking);
        return "redirect:/bill-summary/" + booking.getId();
    }

    // Bill summary page
    @GetMapping("/bill-summary/{id}")
    public String showBillSummary(@PathVariable Long id, Model model) {
        Optional<CustomerBooking> bookingOptional = customerBookingRepository.findById(id);
        if (bookingOptional.isEmpty()) {
            return "redirect:/booking-list"; // Redirect if booking not found
        }

        model.addAttribute("booking", bookingOptional.get());
        return "bill-summary";
    }

    @PostMapping("/bookings/user-confirm/{id}")
    public String confirmBookingByUser(@PathVariable Long id) {
        Optional<CustomerBooking> bookingOptional = customerBookingRepository.findById(id);

        if (bookingOptional.isPresent()) {
            CustomerBooking booking = bookingOptional.get();

            if ("Pending".equalsIgnoreCase(booking.getStatus())) {
                booking.setStatus("Pending"); // Keep it as pending for admin approval
                customerBookingRepository.save(booking);
            }
        }

        return "redirect:/booking-list"; // Redirect user to their booking list
    }


    @GetMapping("/booking-list")
    public String showBookings(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login"; // Redirect if user is not logged in
        }

        String loggedInUsername = principal.getName(); // Get logged-in user's username
        List<CustomerBooking> userBookings = customerBookingRepository.findByCustomerName(loggedInUsername);
        model.addAttribute("bookings", userBookings);

        return "booking-list";
    }
    @GetMapping("/booking-summary/{id}")
    public String viewBookingSummary(@PathVariable Long id, Model model) {
        Optional<CustomerBooking> bookingOptional = customerBookingRepository.findById(id);

        if (bookingOptional.isPresent()) {
            model.addAttribute("booking", bookingOptional.get());
            return "booking-summary"; // This should match your JSP template name
        }

        return "redirect:/booking-list"; // Redirect if the booking is not found
    }

    // Cancel booking if it's "Pending"
    @PostMapping("/bookings/cancel/{id}")
    public String cancelBooking(@PathVariable Long id) {
        Optional<CustomerBooking> bookingOptional = customerBookingRepository.findById(id);
        if (bookingOptional.isPresent() && "Pending".equalsIgnoreCase(bookingOptional.get().getStatus())) {
            customerBookingRepository.deleteById(id);
        }

        return "redirect:/booking-list";
    }

    // Method to calculate base fare based on pickup & destination
    private double calculateBaseFare(String pickup, String destination) {
        if ("City A".equalsIgnoreCase(pickup) && "City B".equalsIgnoreCase(destination)) {
            return 100.0;
        } else {
            return 150.0;
        }
    }
}
