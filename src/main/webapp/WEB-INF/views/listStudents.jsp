<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<table border="1">
<tr>
    <th>ID</th><th>Name</th><th>Email</th><th>Course</th><th>Action</th>
</tr>

<c:forEach items="${students}" var="s">
<tr>
    <td>${s.id}</td>
    <td>${s.name}</td>
    <td>${s.emailId}</td>
     <td>
           <c:forEach items="${s.courseList}" var="c">
               ${c.courseName}<br/>
           </c:forEach>
     </td>
    <td>
        <a href="/student/details/${s.id}">View</a>
        |
        <a href="/student/edit/${s.id}">Edit</a>
        |
        <a href="/student/delete/${s.id}">Delete</a>
    </td>
</tr>
</c:forEach>

</table>

<a href="add">Add Student</a>
|
<a href="/course/all">Course List</a>
|
<a href="/dashboard">Dashboard</a>
