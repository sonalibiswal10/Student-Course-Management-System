package com.project.Student.Course.Management.System.controller;

import com.project.Student.Course.Management.System.repository.CourseRepository;
import com.project.Student.Course.Management.System.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("studentCount", studentRepository.count());
        model.addAttribute("courseCount", courseRepository.count());
        return "dashboard";
    }

    @GetMapping("/dashboard/student/details")
    public String studentDetails(@RequestParam Long id) {
        return "redirect:/student/details/" + id;
    }

    @GetMapping("/dashboard/student/edit")
    public String studentEdit(@RequestParam Long id) {
        return "redirect:/student/edit/" + id;
    }

    @GetMapping("/dashboard/student/delete")
    public String studentDelete(@RequestParam Long id) {
        return "redirect:/student/delete/" + id;
    }

    @GetMapping("/dashboard/course/edit")
    public String courseEdit(@RequestParam Long id) {
        return "redirect:/course/edit/" + id;
    }

    @GetMapping("/dashboard/course/delete")
    public String courseDelete(@RequestParam Long id) {
        return "redirect:/course/delete/" + id;
    }
}
