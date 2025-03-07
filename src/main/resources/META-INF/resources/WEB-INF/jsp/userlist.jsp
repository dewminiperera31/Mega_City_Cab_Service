<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
    <h1>User List</h1>
   <div class="table-responsive mt-4">
       <table class="table table-bordered table-striped table-hover align-middle shadow-sm">
           <thead class="table-primary text-center">
               <tr>
                   <th>ID</th>
                   <th>Username</th>
                   <th>Role</th>
                   <th>Action</th>
               </tr>
           </thead>
           <tbody>
               <c:forEach items="${users}" var="user">
                   <tr>
                       <td class="fw-bold text-center">${user.id}</td>
                       <td>${user.username}</td>
                       <td>
                           <span class="badge
                               <c:choose>
                                   <c:when test="${user.role == 'Admin'}">bg-danger</c:when>
                                   <c:when test="${user.role == 'Driver'}">bg-info</c:when>
                                   <c:otherwise>bg-secondary</c:otherwise>
                               </c:choose>">
                               ${user.role}
                           </span>
                       </td>
                       <td class="text-center">
                           <div class="btn-group">
                               <form action="${pageContext.request.contextPath}/edit/${user.id}" method="get">
                                   <button type="submit" class="btn btn-warning btn-sm">Edit</button>
                               </form>
                               <form action="${pageContext.request.contextPath}/delete/${user.id}" method="post"
                                     onsubmit="return confirm('Are you sure you want to delete this user?');">
                                   <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                               </form>
                           </div>
                       </td>
                   </tr>
               </c:forEach>
           </tbody>
       </table>
   </div>


    <a href="/register" class="btn btn-success">Add Users</a>
</div>

<%@ include file="common/footer.jspf" %>