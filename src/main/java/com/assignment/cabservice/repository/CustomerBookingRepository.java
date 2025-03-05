package com.assignment.cabservice.repository;

import com.assignment.cabservice.model.CustomerBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomerBookingRepository extends JpaRepository<CustomerBooking, Long> {
    List<CustomerBooking> findByCustomerName(String customerName);  // Fetch bookings for a user
}
