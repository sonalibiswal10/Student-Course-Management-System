package com.project.Student.Course.Management.System.repository;

import com.project.Student.Course.Management.System.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    @Query("SELECT s FROM Student s JOIN s.courseList  c")
    List<Student> getStudentCourseDetails();

    @Query("SELECT DISTINCT s FROM Student s LEFT JOIN FETCH s.courseList WHERE s.id = :id")
    Optional<Student> findByIdWithCourses(@Param("id") String id);
}
