package com.assignment.cabservice.controller;

import com.assignment.cabservice.model.Bill;
import com.assignment.cabservice.model.CustomerBooking;
import com.assignment.cabservice.model.User;
import com.assignment.cabservice.repository.CustomerBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/billing")
public class BillController {

    @Autowired
    private CustomerBookingRepository bookingRepository;

    @GetMapping("/calculate/{bookingId}")
    public String generateBill(@PathVariable Long bookingId, Model model) {
        Optional<CustomerBooking> bookingOpt = bookingRepository.findById(bookingId);

        if (bookingOpt.isEmpty()) {
            model.addAttribute("error", "Booking not found!");
            return "bill-error"; // Error page
        }

        CustomerBooking booking = bookingOpt.get();
        double baseFare = booking.getFare(); // ✅ Now this works!
        double tax = baseFare * 0.05; // 5% GST
        double discount = 0.0; // Apply discount logic

        User customer = booking.getCustomer(); // ✅ Now this works!
        if (customer != null && customer.getRole().equals("LOYAL_CUSTOMER")) {
            discount = baseFare * 0.10; // 10% discount for loyal customers
        }

        double totalAmount = baseFare + tax - discount;
        Bill bill = new Bill(bookingId, baseFare, tax, discount, totalAmount);

        model.addAttribute("bill", bill);
        return "bill-summary";
    }
}
