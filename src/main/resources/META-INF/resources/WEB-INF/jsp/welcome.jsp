<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
	<h1>Welcome to the mega city cab service, ${username} ${id}</h1>
	<div class="container">


        <div class="row">
            <div class="col-md-3">
                <div class="card bg-primary text-white text-center">
                    <h4>Total Cars</h4>
                    <h2>${totalCars}</h2>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card bg-success text-white text-center">
                    <h4>Total Drivers</h4>
                    <h2>${totalDrivers}</h2>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card bg-warning text-white text-center">
                    <h4>Total Users</h4>
                    <h2>${totalUsers}</h2>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card bg-danger text-white text-center">
                    <h4>New Bookings</h4>
                    <h2>${totalBookings}</h2>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf" %>

