<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <h2>Bill Summary</h2>
    <p>Pickup Location: ${booking.pickupLocation}</p>
    <p>Destination: ${booking.destination}</p>
    <p>Base Fare: ${booking.baseFare}</p>
    <p>Tax (10%): ${booking.tax}</p>
    <p><strong>Total Amount: ${booking.totalAmount}</strong></p>

    <!-- Confirm Booking Button -->
    <form action="/bookings/user-confirm/${booking.id}" method="post">
        <button type="submit">Confirm Booking</button>
    </form>
</div>
<%@ include file="common/footer.jspf" %>
