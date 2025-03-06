package com.assignment.cabservice.model;

public class Bill {
    private Long bookingId;
    private double baseFare;
    private double taxAmount;
    private double discount;
    private double totalAmount;

    public Bill(Long bookingId, double baseFare, double taxAmount, double discount, double totalAmount) {
        this.bookingId = bookingId;
        this.baseFare = baseFare;
        this.taxAmount = taxAmount;
        this.discount = discount;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters
    public Long getBookingId() { return bookingId; }
    public double getBaseFare() { return baseFare; }
    public double getTaxAmount() { return taxAmount; }
    public double getDiscount() { return discount; }
    public double getTotalAmount() { return totalAmount; }
}
