<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<style>
    .status-pending { background-color: yellow; color: black; font-weight: bold; padding: 5px; border-radius: 5px; }
    .status-confirmed { background-color: green; color: white; font-weight: bold; padding: 5px; border-radius: 5px; }
    .status-cancelled { background-color: red; color: white; font-weight: bold; padding: 5px; border-radius: 5px; }
    .btn-group { display: flex; gap: 5px; } /* Fix button alignment */
</style>

<div class="container">
    <h2>Admin Booking List</h2>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Order Number</th>
                <th>Customer Name</th>
                <th>Address</th>
                <th>Telephone</th>
                <th>Destination</th>
                <th>Pickup Location</th>
                <th>Booking Date</th>
                <th>Booking Time</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${bookings}" var="booking">
                <tr>
                    <td>${booking.orderNumber}</td>
                    <td>${booking.customerName}</td>
                    <td>${booking.address}</td>
                    <td>${booking.telephone}</td>
                    <td>${booking.destination}</td>
                    <td>${booking.pickupLocation}</td>
                    <td>${booking.bookingDate}</td>
                    <td>${booking.bookingTime}</td>
                    <td>
                        <c:choose>
                            <c:when test="${booking.status == 'Pending'}">
                                <span class="status-pending">${booking.status}</span>
                            </c:when>
                            <c:when test="${booking.status == 'Confirmed'}">
                                <span class="status-confirmed">${booking.status}</span>
                            </c:when>
                            <c:when test="${booking.status == 'Cancelled'}">
                                <span class="status-cancelled">${booking.status}</span>
                            </c:when>
                            <c:otherwise>
                                ${booking.status}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:if test="${booking.status == 'Pending'}">
                            <div class="btn-group">
                                <!-- Confirm Booking -->
                                <form action="${pageContext.request.contextPath}/confirm/{id}" method="Post">
                                    <button type="submit" class="btn btn-success">Confirm</button>
                                </form>
                                <!-- Cancel Booking -->
                                <form action="${pageContext.request.contextPath}/cancel/{id}" method="post" onsubmit="return confirm('Are you sure you want to cancel this booking?');">
                                    <button type="submit" class="btn btn-danger">Cancel</button>
                                </form>
                            </div>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="common/footer.jspf" %>
