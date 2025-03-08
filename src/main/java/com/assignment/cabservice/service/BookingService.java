package com.assignment.cabservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.assignment.cabservice.model.CustomerBooking;
import com.assignment.cabservice.repository.CustomerBookingRepository;

@Service
public class BookingService {

    @Autowired
    private CustomerBookingRepository customerBookingRepository;

    public Optional<CustomerBooking> getBookingById(Long id) {
        return customerBookingRepository.findById(id);
    }
}
