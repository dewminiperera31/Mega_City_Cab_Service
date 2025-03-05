<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
    <h1>Test Car Requests</h1>

    <c:if test="${empty car_requests}">
        <p>No car requests found.</p>
    </c:if>

    <table class="table">
        <thead>
            <tr>
                <th>Request ID</th>
                <th>Driver ID</th>
                <th>Car ID</th>
                <th>Request Status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${car_requests}" var="cr">
                <tr>
                    <td>${cr.id}</td>
                    <td>${cr.driverId}</td>
                    <td>${cr.carId}</td>
                    <td>${cr.requestStatus}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="common/footer.jspf" %>
