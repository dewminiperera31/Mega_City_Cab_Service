package com.assignment.cabservice.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "customer_bookings")
public class CustomerBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double fare;


    @Column(unique = true, nullable = false)
    private String orderNumber;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "customer_id") // Ensure the foreign key is set up correctly
    private User customer; // Assuming 'User' is your customer entity

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private String status = "Pending";

    @Column(nullable = false)
    private LocalDate bookingDate;

    @Column(nullable = false)
    private LocalTime bookingTime;

    @Column(nullable = false)
    private String pickupLocation;
    private Double baseFare;
    private Double tax;
    private Double totalAmount;


        private String name;
        private String dropLocation;

        public String getName() {
            return name;
        }

        public String getDropLocation() {
            return dropLocation;
        }



    // Default values before saving
    @PrePersist
    protected void onCreate() {
        if (this.status == null) {
            this.status = "Pending";  // Always set status to "Pending"
        }
        if (this.orderNumber == null) {
            this.orderNumber = "ORD-" + UUID.randomUUID().toString().substring(0, 8); // Generate unique order number
        }
    }
    public CustomerBooking() {}

    public CustomerBooking(double fare, User customer, String status) {
        this.fare = fare;
        this.customer = customer;
        this.status = status;
    }

    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }


    public double getFare() { return fare; } // ✅ Now this method exists

    public void setFare(double fare) { this.fare = fare; }

    public User getCustomer() { return customer; } // ✅ Now this method exists

    public void setCustomer(User customer) { this.customer = customer; }


    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }

    public LocalTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalTime bookingTime) { this.bookingTime = bookingTime; }

    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }

    public double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(double baseFare) {
        this.baseFare = baseFare;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public Double getTotalAmount() {
        return totalAmount != null ? totalAmount : 0.0;
    }


    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

}
