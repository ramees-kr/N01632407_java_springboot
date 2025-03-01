package com.ramees.assignment.assignment2_ramees.controllers;

import com.ramees.assignment.assignment2_ramees.Repositories.AdminRepository;
import com.ramees.assignment.assignment2_ramees.models.Admin;
import com.ramees.assignment.assignment2_ramees.models.Enrollment;
import com.ramees.assignment.assignment2_ramees.models.Student;
import com.ramees.assignment.assignment2_ramees.services.EnrollmentService;
import com.ramees.assignment.assignment2_ramees.services.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private EnrollmentService enrollmentService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "admin-login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        Admin admin = adminRepository.findByUsernameAndPassword(username, password);
        if (admin != null) {
            session.setAttribute("adminId", admin.getId());
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("error", true);
            return "admin-login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("adminId");
        return "redirect:/admin/login";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        Long adminId = (Long) session.getAttribute("adminId");
        if (adminId == null) {
            return "redirect:/admin/login";
        }

        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        return "admin-dashboard";

    }

    @GetMapping("/students/{id}")
    public String viewStudent(@PathVariable Long id, Model model, HttpSession session) {
        Long adminId = (Long) session.getAttribute("adminId");
        if (adminId == null) {
            return "redirect:/admin/login";
        }
        Student student = studentService.findById(id);
        if(student == null) {
            return "redirect:/admin/dashboard";
        }
        List<Enrollment> enrollments = enrollmentService.findByStudent(id);
        model.addAttribute("student", student);
        model.addAttribute("enrollments", enrollments);
        return "admin-view-student";
    }

    @GetMapping("/students/{studentId}/enrollments/delete/{programCode}")
    public String deleteEnrollment(@PathVariable Long studentId, @PathVariable String programCode, HttpSession session) {
        Long adminId = (Long) session.getAttribute("adminId");
        if (adminId == null) {
            return "redirect:/admin/login";
        }
        Enrollment enrollment = enrollmentService.findByStudentAndProgram(studentId, programCode);
        if (enrollment != null) {
            enrollmentService.deleteEnrollment(enrollment);
        }
        return "redirect:/admin/students/" + studentId;
    }

    @GetMapping("/students/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "admin-edit-student";
    }

    @PostMapping("/students/edit")
    public String updateStudent(@RequestParam Long id, @RequestParam String username, @RequestParam String name, @RequestParam String address, @RequestParam String email, @RequestParam String dob, @RequestParam String status) {
        Student student = studentService.findById(id);
        student.setUsername(username);
        student.setName(name);
        student.setEmail(email);
        student.setAddress(address);
        student.setDob(Date.valueOf(dob));
        student.setStatus(status);

        studentService.updateStudent(student);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        Student student = studentService.findById(id);
        if (student != null) {
            studentService.deleteStudent(student);
        }
        return "redirect:/admin/dashboard";
    }
}