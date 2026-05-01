package com.project.Student.Course.Management.System.service;

import com.project.Student.Course.Management.System.entity.Course;
import com.project.Student.Course.Management.System.entity.Student;
import com.project.Student.Course.Management.System.repository.CourseRepository;
import com.project.Student.Course.Management.System.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    public List<Course> getAllCourses() {
        try {
            return courseRepository.findAll();
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to fetch course list from database", e);
        }
    }

    public Course getCourseById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }

        try {
            return courseRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Course not found with ID: " + id));
        } catch (DataAccessException e) {
            throw new RuntimeException("Error while fetching course by ID", e);
        }
    }

    public Course saveCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course object cannot be null");
        }

        try {
            return courseRepository.save(course);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error while saving course data", e);
        }
    }

    @Transactional
    public void deleteCourseById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Course ID cannot be null");
        }

        Course course = getCourseById(id);

        try {
            List<Student> students = studentRepository.findAll();
            for (Student student : students) {
                if (student.getCourseList() == null) {
                    continue;
                }

                List<Course> remainingCourses = student.getCourseList().stream()
                        .filter(existingCourse -> !Objects.equals(existingCourse.getId(), id))
                        .collect(Collectors.toList());

                if (remainingCourses.size() != student.getCourseList().size()) {
                    student.setCourseList(remainingCourses);
                    studentRepository.save(student);
                }
            }

            courseRepository.delete(course);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error while deleting course", e);
        }
    }
}
