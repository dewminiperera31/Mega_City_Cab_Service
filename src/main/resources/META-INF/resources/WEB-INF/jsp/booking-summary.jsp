<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
    <h2>Booking Summary</h2>

    <p><strong>Order Number:</strong> <span>${booking.orderNumber}</span></p>
    <p><strong>Pickup Location:</strong> <span>${booking.pickupLocation}</span></p>
    <p><strong>Destination:</strong> <span>${booking.destination}</span></p>
    <p><strong>Booking Date:</strong> <span>${booking.bookingDate}</span></p>

    <p><strong>Total Fare:</strong>
        <c:choose>
            <c:when test="${not empty booking.totalAmount}">
                $<span>${booking.totalAmount}</span>
            </c:when>
            <c:otherwise>
                <span>N/A</span>
            </c:otherwise>
        </c:choose>
    </p>

    <!-- Buttons Section -->
    <div class="mt-3">
        <a href="/booking-list" class="btn btn-secondary">Go Back</a>

        <!-- Download PDF Button -->
        <a href="/downloadBookingSummary?bookingId=${booking.id}" class="btn btn-primary">
            Download PDF
        </a>
    </div>
</div>

<%@ include file="common/footer.jspf" %>
