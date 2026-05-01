package com.project.Student.Course.Management.System.controller;

import com.project.Student.Course.Management.System.entity.Student;
import com.project.Student.Course.Management.System.repository.CourseRepository;
import com.project.Student.Course.Management.System.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @Autowired
    private CourseRepository courseRepo;

    // Read
    @GetMapping("/all")
    public String listStudents(Model model) {
        model.addAttribute("students", service.getAllStudents());
        return "listStudents";
    }

    @GetMapping("/details/{id}")
    public String studentDetails(@PathVariable String id, Model model) {
        model.addAttribute("student", service.getStudentByIdWithCourses(id));
        return "studentDetails";
    }

    // Create Form
    @GetMapping("/add")
    public String showForm(Model model) {
        System.out.println("showform");
        model.addAttribute("student", new Student());
        model.addAttribute("courses", courseRepo.findAll());
        return "addStudent";
    }

    // Save
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute Student student,
                              @RequestParam(value = "courseIds", required = false) List<String> courseIds) {
        try {
            service.saveStudent(student,courseIds);
        } catch (Exception e) {
            System.out.println("Error while saving the student data: " + e.getMessage());
        }
        return "redirect:/student/all";
    }

    // Update Form
    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable String id, Model model) {
        Student student = service.getStudentByIdWithCourses(id);
        List<String> selectedCourseIds = student.getCourseList() == null
                ? List.of()
                : student.getCourseList().stream()
                        .map(course -> course.getId())
                        .collect(Collectors.toList());

        model.addAttribute("student", student);
        model.addAttribute("courses", courseRepo.findAll());
        model.addAttribute("selectedCourseIds", selectedCourseIds);
        return "updateStudent";
    }

    // Update Save
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student,
                                @RequestParam(value = "courseIds", required = false) List<String> courseIds) {
        service.saveStudent(student,courseIds);
        return "redirect:/student/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        service.deleteStudentById(id);
        return "redirect:/student/all";
    }
}
