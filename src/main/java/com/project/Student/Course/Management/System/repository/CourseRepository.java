package com.project.Student.Course.Management.System.repository;

import com.project.Student.Course.Management.System.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
}
