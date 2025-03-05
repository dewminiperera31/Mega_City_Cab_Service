
	<%@ include file="common/header.jspf" %>
	<%-- ✅ Success message --%>

    <%-- ✅ Success message --%>
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success" id="successMessage">${successMessage}</div>
    </c:if>

    <%-- ✅ JavaScript to hide message after 3 seconds --%>
    <script>
        setTimeout(function() {
            var message = document.getElementById("successMessage");
            if (message) {
                message.style.display = "none";
            }
        }, 3000); // 3000ms = 3 seconds
    </script>

    <%@ include file="common/navigation.jspf" %>
		<div class="container">
			<h1>Cab List</h1>
			<table class="table">
				<thead>
					<tr>
						<th>Cab Id</th>
						<th>Model</th>
						<th>Color</th>
						<th>Seating Capacity</th>
						<th>Available For Booking?</th>
						<th>DriverId</th>
                        <th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cars}" var="car">
						<tr>
							<td>${car.id}</td>
							<td>${car.model}</td>
							<td>${car.color}</td>
							<td>${car.seatingCapacity}</td>
							<td>${car.availableForBooking}</td>
							<td>${car.driverId}</td>
                            <td><a href="delete-car?id=${car.id}" class="btn btn-warning">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a href="add-cab" class="btn btn-success">Add Cab</a>
		</div>
		<%@ include file="common/footer.jspf" %>
