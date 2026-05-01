<form action="/course/update" method="post">
    Course ID: <input type="number" name="id" value="${course.id}" readonly/><br/>

    Course Name: <input type="text" name="courseName" value="${course.courseName}"/><br/>

    <button type="submit">Update</button>
</form>

<a href="/course/all">Back to Course List</a>
