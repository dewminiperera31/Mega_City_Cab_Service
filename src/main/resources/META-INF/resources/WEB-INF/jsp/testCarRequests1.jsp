<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
    <h1>Booked Cars</h1>

    <c:if test="${empty bookings}">
        <p style="color:red;">No booked cars found.</p>
    </c:if>

    <table class="table">
        <thead>
            <tr>
                <th>Booking ID</th>
                <th>Car ID</th>
                <th>Driver ID</th>
                <th>Status</th>
                <th>Username</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${bookings}" var="booking">
                <tr>
                    <td>${booking.id}</td>
                    <td>${booking.carId}</td>
                    <td>${booking.driverId}</td>
                    <td>${booking.status}</td>
                    <td>${booking.username}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <p>Debug: ${bookings}</p> <!-- Print raw data for debugging -->
</div>

<%@ include file="common/footer.jspf" %>
