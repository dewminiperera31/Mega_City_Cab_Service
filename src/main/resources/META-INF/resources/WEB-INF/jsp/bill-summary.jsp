<%@ include file="common/header.jspf" %>
<div class="container">
    <h2>Bill Summary</h2>
    <table class="table">
        <tr><th>Booking ID</th><td>${bill.bookingId}</td></tr>
        <tr><th>Base Fare</th><td>${bill.baseFare}</td></tr>
        <tr><th>Tax (5%)</th><td>${bill.taxAmount}</td></tr>
        <tr><th>Discount</th><td>${bill.discount}</td></tr>
        <tr><th>Total Amount</th><td><strong>${bill.totalAmount}</strong></td></tr>
    </table>
</div>
<%@ include file="common/footer.jspf" %>
