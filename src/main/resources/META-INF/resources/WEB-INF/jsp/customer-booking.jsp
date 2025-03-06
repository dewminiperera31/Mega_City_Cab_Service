<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>


<style>
    .form-group {
        margin-bottom: 15px; /* Adds spacing between input fields */
    }

    .btn-container {
        margin-top: 20px; /* Moves buttons down */
    }

    .container {
        max-width: 600px; /* Sets form width */
        margin-top: 30px; /* Adds space from the top */
        padding: 20px;
        background: #f8f9fa; /* Light gray background */
        border-radius: 8px;
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); /* Subtle shadow */
    }

    h2 {
        text-align: center;
        margin-bottom: 20px;
    }
</style>

<div class="container">
    <h2>Customer Booking</h2>
    <form action="/add-booking" method="post">

        <div class="form-group">
            <label>Customer Name:</label>
            <input type="text" name="customerName" class="form-control" required>
        </div>
        <div class="form-group">
            <label>Address:</label>
            <input type="text" name="address" class="form-control" required>
        </div>
        <div class="form-group">
            <label>Telephone:</label>
            <input type="tel" name="telephone" class="form-control" required>
        </div>
        <div class="form-group">
            <label>Pickup Location:</label>
            <input type="text" name="pickupLocation" class="form-control" required>
        </div>

        <div class="form-group">
            <label>Destination:</label>
            <input type="text" name="destination" class="form-control" required>
        </div>
        <div class="form-group">
            <label>Date:</label>
            <input type="date" name="date" class="form-control" required>
        </div>
        <div class="form-group">
            <label>Time:</label>
            <input type="time" name="time" class="form-control" required>
        </div>

        <!-- Buttons with spacing -->
        <div class="btn-container">
            <button type="submit" class="btn btn-success">Book Now</button>
            <a href="/booking-list" class="btn btn-secondary">Cancel</a>
        </div>
    </form>
</div>

<%@ include file="common/footer.jspf" %>