<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<h2>Courses</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Course Name</th>
        <th>Action</th>
    </tr>

    <c:forEach items="${courses}" var="course">
        <tr>
            <td>${course.id}</td>
            <td>${course.courseName}</td>
            <td>
                <a href="/course/edit/${course.id}">Edit</a>
                |
                <a href="/course/delete/${course.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="/course/add">Add Course</a>
|
<a href="/student/all">Student List</a>
|
<a href="/dashboard">Dashboard</a>
