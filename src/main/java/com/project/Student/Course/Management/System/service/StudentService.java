package com.project.Student.Course.Management.System.service;

import com.project.Student.Course.Management.System.entity.Course;
import com.project.Student.Course.Management.System.entity.Student;
import com.project.Student.Course.Management.System.repository.CourseRepository;
import com.project.Student.Course.Management.System.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private CourseRepository courseRepo;

    // ===============================
    // Get All Students
    // ===============================
    public List<Student> getAllStudents() {
        try {
            return studentRepo.findAll();
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to fetch student list from database", e);
        }
    }

    // ===============================
    // Save Student
    // ===============================
    public void saveStudent(Student student,List<String> courseIds) {

        if (student == null) {
            throw new IllegalArgumentException("Student object cannot be null");
        }

        try {
            System.out.println("student data   "+student.getEmailId()+"  "+student.getName());
            courseIds = courseIds == null ? List.of() : courseIds;
            List<Course> courses = courseRepo.findAllById(courseIds);
            System.out.println("courses "+courses);
            student.setCourseList(courses);
            studentRepo.save(student);


        } catch (DataAccessException e) {
            throw new RuntimeException("Error while saving student data", e);
        }
    }

    // ===============================
    // Get Student By ID
    // ===============================
    public Student getStudentById(String id) {

        if (id == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }

        try {
            return studentRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
        } catch (DataAccessException e) {
            throw new RuntimeException("Error while fetching student by ID", e);
        }
    }

    public Student getStudentByIdWithCourses(String id) {

        if (id == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }

        try {
            return studentRepo.findByIdWithCourses(id)
                    .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
        } catch (DataAccessException e) {
            throw new RuntimeException("Error while fetching student by ID", e);
        }
    }

    @Transactional
    public void deleteStudentById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }

        try {
            Student student = getStudentByIdWithCourses(id);
            student.setCourseList(new ArrayList<>());
            studentRepo.save(student);
            studentRepo.delete(student);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error while deleting student", e);
        }
    }

    // ===============================
    // Custom Join Query
    // ===============================
    public List<Student> getStudentCourseJoin() {
        try {
            return studentRepo.getStudentCourseDetails();
        } catch (DataAccessException e) {
            throw new RuntimeException("Error while fetching student-course details", e);
        }
    }
}
