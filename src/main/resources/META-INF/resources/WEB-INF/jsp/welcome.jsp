<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
    <h1>Welcome to Mega City Cab Service, ${username}!</h1>

    <c:choose>
        <c:when test="${role eq 'ADMIN'}">
            <!-- Admin Dashboard -->
            <div class="row mt-4">
                <div class="col-md-3">
                    <div class="card bg-primary text-white text-center p-3">
                        <h4>Total Cars</h4>
                        <h2>${totalCars}</h2>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card bg-success text-white text-center p-3">
                        <h4>Total Drivers</h4>
                        <h2>${totalDrivers}</h2>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card bg-warning text-white text-center p-3">
                        <h4>Total Users</h4>
                        <h2>${totalUsers}</h2>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card bg-danger text-white text-center p-3">
                        <h4>New Bookings</h4>
                        <h2>${totalBookings}</h2>
                    </div>
                </div>
            </div>
        </c:when>

        <c:when test="${role eq 'USER'}">
            <!-- System Usage Guidelines for Customers -->
            <div class="alert alert-info mt-4">
                <h3>🚖 System Usage Guidelines</h3>
                <ul>
                    <li><b>Book a Ride:</b> Click 'Book a Ride' and enter trip details.</li>
                    <li><b>View Bookings:</b> Track your rides in 'My Bookings'.</li>
                    <li><b>Cancel Booking:</b> Click 'Cancel' if the ride is pending.</li>
                    <li><b>Security:</b> Keep your credentials secure and log out after use.</li>
                </ul>
            </div>
        </c:when>
    </c:choose>

</div>

<%@ include file="common/footer.jspf" %>
