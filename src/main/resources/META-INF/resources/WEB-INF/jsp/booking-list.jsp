<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<style>
    .status-pending { background-color: yellow; color: black; font-weight: bold; padding: 5px; border-radius: 5px; }
    .status-confirmed { background-color: green; color: white; font-weight: bold; padding: 5px; border-radius: 5px; }
    .status-cancelled { background-color: red; color: white; font-weight: bold; padding: 5px; border-radius: 5px; }
</style>

<div class="container">
    <h2>Booking List</h2>
   <div class="table-responsive mt-4">
       <table class="table table-hover table-striped align-middle shadow-sm">
           <thead class="table-primary text-center">
               <tr>
                   <th>Order #</th>
                   <th>Customer</th>
                   <th>Address</th>
                   <th>Phone</th>
                   <th>Destination</th>
                   <th>Pickup</th>
                   <th>Date</th>
                   <th>Time</th>
                   <th>Status</th>
                   <th>Action</th>
               </tr>
           </thead>
           <tbody>
               <c:forEach items="${bookings}" var="booking">
                   <tr>
                       <td class="fw-semibold text-center">${booking.orderNumber}</td>
                       <td>${booking.customerName}</td>
                       <td>${booking.address}</td>
                       <td>${booking.telephone}</td>
                       <td>${booking.destination}</td>
                       <td>${booking.pickupLocation}</td>
                       <td>${booking.bookingDate}</td>
                       <td>${booking.bookingTime}</td>
                       <td class="text-center">
                           <c:choose>
                               <c:when test="${booking.status == 'Pending'}">
                                   <span class="badge bg-warning text-dark">${booking.status}</span>
                               </c:when>
                               <c:when test="${booking.status == 'Confirmed'}">
                                   <span class="badge bg-success">${booking.status}</span>
                               </c:when>
                               <c:when test="${booking.status == 'Cancelled'}">
                                   <span class="badge bg-danger">${booking.status}</span>
                               </c:when>
                               <c:otherwise>
                                   <span class="badge bg-secondary">${booking.status}</span>
                               </c:otherwise>
                           </c:choose>
                       </td>
                       <td class="text-center">
                           <c:if test="${booking.status == 'Pending'}">
                               <form action="/bookings/cancel/${booking.id}" method="post"
                                     onsubmit="return confirm('Are you sure you want to cancel this booking?');">
                                   <button type="submit" class="btn btn-outline-danger btn-sm">
                                       Cancel
                                   </button>
                               </form>
                           </c:if>
                       </td>
                   </tr>
               </c:forEach>
           </tbody>
       </table>
   </div>

</div>

<%@ include file="common/footer.jspf" %>