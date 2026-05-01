package com.project.Student.Course.Management.System.controller;

import com.project.Student.Course.Management.System.entity.Course;
import com.project.Student.Course.Management.System.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        return "listCourses";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        return "addCourse";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute Course course) {
        courseService.saveCourse(course);
        return "redirect:/course/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        model.addAttribute("course", courseService.getCourseById(id));
        return "updateCourse";
    }

    @PostMapping("/update")
    public String updateCourse(@ModelAttribute Course course) {
        courseService.saveCourse(course);
        return "redirect:/course/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable String id) {
        courseService.deleteCourseById(id);
        return "redirect:/course/all";
    }
}
