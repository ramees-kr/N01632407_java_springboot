package com.example.studentrecordmanagement.controller;
import com.example.studentrecordmanagement.entity.Student;
import com.example.studentrecordmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // GET /students -> Show student list
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "student_list";  // Thymeleaf template name
    }

    // GET /students/new -> Show form to add a new student
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "student_form";
    }

    // POST /students -> Handle form submission to add a student
    @PostMapping
    public String addStudent(@ModelAttribute("student") Student student) {
        studentService.addStudent(student);
        return "redirect:/students";  // Redirect to the students list
    }

    // Example: GET /students/{id} -> Retrieve a specific student by ID
    @GetMapping("/{id}")
    public String getStudentById(@PathVariable String id, Model model) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            // handle not found scenario
            return "error"; // or a custom error page
        }
        model.addAttribute("student", student);
        return "student_detail"; // You can create a "student_detail.html" template for this
    }
}
