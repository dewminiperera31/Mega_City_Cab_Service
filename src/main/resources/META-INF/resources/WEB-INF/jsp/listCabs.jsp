
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
			<div class="table-responsive mt-4">
                <table class="table table-bordered table-striped table-hover align-middle shadow-sm">
                    <thead class="table-primary text-center">
                        <tr>
                            <th>Cab ID</th>
                            <th>Model</th>
                            <th>Color</th>
                            <th>Seating Capacity</th>
                            <th>Available for Booking?</th>
                            <th>Driver ID</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cars}" var="car">
                            <tr>
                                <td class="fw-bold text-center">${car.id}</td>
                                <td>${car.model}</td>
                                <td>${car.color}</td>
                                <td class="text-center">${car.seatingCapacity}</td>
                                <td class="text-center">
                                    <span class="badge ${car.availableForBooking ? 'bg-success' : 'bg-danger'}">
                                        ${car.availableForBooking ? 'Yes' : 'No'}
                                    </span>
                                </td>
                                <td class="text-center">${car.driverId != null ? car.driverId : 'Unassigned'}</td>
                                <td class="text-center">
                                    <a href="delete-car?id=${car.id}" class="btn btn-danger btn-sm"
                                       onclick="return confirm('Are you sure you want to delete this car?');">
                                        <i class="fas fa-trash-alt"></i> Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

			<a href="add-cab" class="btn btn-success">Add Cab</a>
		</div>
		<%@ include file="common/footer.jspf" %>
