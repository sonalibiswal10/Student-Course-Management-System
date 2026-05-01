package com.project.Student.Course.Management.System.entity;

import jakarta.persistence.*;


import java.util.List;

@Entity
public class Student {

    @Id
    private String id;

    private String name;

    private String emailId;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courseList;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }



    public List<Course> getCourseList() {
        return courseList;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
