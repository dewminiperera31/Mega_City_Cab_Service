package com.assignment.cabservice.controller;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.assignment.cabservice.model.CustomerBooking;
import com.assignment.cabservice.service.BookingService;

@RestController
@RequestMapping("/api/pdf")
public class PDFController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/downloadBookingSummary")
    public void downloadBookingSummary(@RequestParam Long bookingId, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=booking-summary.pdf");

        // Fetch booking details
        Optional<CustomerBooking> bookingOpt = bookingService.getBookingById(bookingId);

        if (bookingOpt.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Booking not found!");
            return;
        }

        CustomerBooking booking = bookingOpt.get();

        // Generate PDF using try-with-resources to ensure proper resource closing
        try (PdfWriter writer = new PdfWriter(response.getOutputStream());
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            // Add content
            document.add(new Paragraph("Booking Summary").setBold().setFontSize(18));
            document.add(new Paragraph("Booking ID: " + booking.getId()));
            document.add(new Paragraph("Customer Name: " + booking.getCustomer().getUsername()));
            document.add(new Paragraph("Pickup Location: " + booking.getPickupLocation()));
            document.add(new Paragraph("Drop Location: " + booking.getDropLocation()));
            document.add(new Paragraph("Total Amount: $" + booking.getTotalAmount()));
        }
    }
}
