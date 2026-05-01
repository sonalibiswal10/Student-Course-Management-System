<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<h2>Student Details</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <td>${student.id}</td>
    </tr>
    <tr>
        <th>Name</th>
        <td>${student.name}</td>
    </tr>
    <tr>
        <th>Email</th>
        <td>${student.emailId}</td>
    </tr>
    <tr>
        <th>Courses</th>
        <td>
            <c:forEach items="${student.courseList}" var="course">
                ${course.courseName}<br/>
            </c:forEach>
        </td>
    </tr>
</table>

<a href="/student/edit/${student.id}">Edit</a>
|
<a href="/student/delete/${student.id}">Delete</a>
|
<a href="/student/all">Back to Student List</a>
