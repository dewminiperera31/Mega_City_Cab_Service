
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<%@ include file="common/header.jspf" %>
    <%@ include file="common/navigation.jspf" %>
		<div class="container">
			<h1>Cab Bookings</h1>
			<table class="table">
				<thead>
					<tr>
						<th>RequestId</th>
						<th>DriverId</th>
						<th>CarId</th>
						<th>Request Status</th>
						<th>Approve</th>
						<th>Reject</th>
					</tr>
				</thead>
				<tbody>
				 <!-- ✅ Show message if there are no car requests -->
                            <c:if test="${empty car_requests}">
                                <tr>
                                    <td colspan="6" style="text-align: center; color: red;">
                                        No car requests found.
                                    </td>
                                </tr>
                            </c:if>
					<c:forEach items="${car_requests}" var="cr">
						<tr>
							<td>${cr.id}</td>
							<td>${cr.driver_id}</td>
							<td>${cr.car_id}</td>
							<td>${cr.status}</td>
                            <td><a href="approve-request?requestId=${cr.id}&driverId=${cr.driverId}&carId=${cr.carId}" class="btn btn-warning">Approve</a></td>
                            <td><a href="reject-request?requestId=${cr.id}" class="btn btn-warning">Reject</a></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
		<%@ include file="common/footer.jspf" %>
