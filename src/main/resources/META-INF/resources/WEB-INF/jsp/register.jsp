
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<style>
    .form-group {
        margin-bottom: 15px; /* Adds spacing between form elements */
    }

    .btn-container {
        margin-top: 20px; /* Moves buttons down }
</style>

<div class="container">
    <h2>Add User</h2>
    <form action="/register" method="post">
        <div class="form-group">
            <label>Username:</label>
            <input type="text" name="username" class="form-control" required>
        </div>
        <div class="form-group">
            <label>Password:</label>
            <input type="password" name="password" class="form-control" required>
        </div>
        <div class="form-group">
            <label>Role:</label>
            <select name="role" class="form-control">
                <option value="USER">User</option>
                <option value="ADMIN">Admin</option>
            </select>
        </div>

        <button type="submit" class="btn btn-success">Save</button>
        <a href="/userlist" class="btn btn-secondary">Cancel</a>
    </form>
</div>

<%@ include file="common/footer.jspf" %>