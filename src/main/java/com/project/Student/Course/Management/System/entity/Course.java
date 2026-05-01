package com.project.Student.Course.Management.System.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Course {

    @Id
    private String id;

    private String courseName;



    @ManyToMany(mappedBy = "courseList")
    private List<Student> students;

    public void setId(String id) {
        this.id = id;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public List<Student> getStudents() {
        return students;
    }
}
