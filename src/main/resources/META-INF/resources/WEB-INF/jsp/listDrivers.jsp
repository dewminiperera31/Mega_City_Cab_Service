
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
			<h1>List of Drivers</h1>
			<div class="table-responsive mt-4">
                <table class="table table-bordered table-striped table-hover align-middle shadow-sm">
                    <thead class="table-primary text-center">
                        <tr>
                            <th>Driver ID</th>
                            <th>Username</th>
                            <th>Assigned Car ID</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${drivers}" var="driver">
                            <tr>
                                <td class="fw-bold text-center">${driver.id}</td>
                                <td>${driver.username}</td>
                                <td class="text-center">${driver.assignedCarId != null ? driver.assignedCarId : 'Not Assigned'}</td>
                                <td class="text-center">
                                    <div class="btn-group">
                                        <a href="delete-driver?id=${driver.id}" class="btn btn-danger btn-sm"
                                           onclick="return confirm('Are you sure you want to delete this driver?');">
                                            <i class="fas fa-trash-alt"></i> Delete
                                        </a>
                                        <a href="driver/used-cars?driverId=${driver.id}" class="btn btn-info btn-sm">
                                            <i class="fas fa-car"></i> Used Cars
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

			<a href="add-driver" class="btn btn-success">Add Driver</a>
		</div>
		<%@ include file="common/footer.jspf" %>
