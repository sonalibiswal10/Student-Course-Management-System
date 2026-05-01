<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<form action="/student/save" method="post">
    Student ID: <input type="text" name="id" required/><br/>
    Name: <input type="text" name="name"/><br/>
    Email: <input type="text" name="emailId"/><br/>

    Courses:<br/>
    <c:forEach items="${courses}" var="c">
        <label>
            <input type="checkbox" name="courseIds" value="${c.id}"/>
            ${c.courseName}
        </label><br/>
    </c:forEach>

    <button type="submit">Save</button>
</form>

<a href="/dashboard">Dashboard</a>
