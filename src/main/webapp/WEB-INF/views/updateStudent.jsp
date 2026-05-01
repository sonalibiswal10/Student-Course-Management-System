<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<form action="/student/update" method="post">
    Student ID: <input type="number" name="id" value="${student.id}" readonly/><br/>

    Name: <input type="text" name="name" value="${student.name}"/><br/>
    Email: <input type="text" name="emailId" value="${student.emailId}"/><br/>
    Courses:<br/>
    <c:forEach items="${courses}" var="c">
        <c:set var="checked" value="false"/>
        <c:forEach items="${selectedCourseIds}" var="selectedCourseId">
            <c:if test="${selectedCourseId == c.id}">
                <c:set var="checked" value="true"/>
            </c:if>
        </c:forEach>

        <label>
            <input type="checkbox" name="courseIds" value="${c.id}" ${checked ? 'checked="checked"' : ''}/>
            ${c.courseName}
        </label><br/>
    </c:forEach>
    <button type="submit">Update</button>
</form>
