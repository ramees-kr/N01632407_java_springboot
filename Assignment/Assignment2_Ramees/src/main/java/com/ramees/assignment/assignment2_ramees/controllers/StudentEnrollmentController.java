package com.ramees.assignment.assignment2_ramees.controllers;

import com.ramees.assignment.assignment2_ramees.models.Enrollment;
import com.ramees.assignment.assignment2_ramees.models.Program;
import com.ramees.assignment.assignment2_ramees.models.Student;
import com.ramees.assignment.assignment2_ramees.services.EnrollmentService;
import com.ramees.assignment.assignment2_ramees.services.ProgramService;
import com.ramees.assignment.assignment2_ramees.services.StudentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
public class StudentEnrollmentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentEnrollmentController.class);

    private final StudentService studentService;
    private final ProgramService programService;
    private final EnrollmentService enrollmentService;

    public StudentEnrollmentController(StudentService studentService, ProgramService programService, EnrollmentService enrollmentService) {
        this.studentService = studentService;
        this.programService = programService;
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/")
    public String getIndexPage(@RequestParam(required = false) String message, Model model) {
        model.addAttribute("message", message);
        return "index";
    }

    @GetMapping("/home")
    public String getHomePage(@RequestParam(required = false) String message,Model model, HttpSession session) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (Objects.isNull(studentId)) {
            return "redirect:/";
        }
        Student student = studentService.findById(studentId);
        model.addAttribute("student", student);
        List<Program> programs = enrollmentService.findProgramsByStudent(studentId);

        model.addAttribute("programs", programs);
        model.addAttribute("message", message);
        return "home";
    }

    @PostMapping("/login")
    public String userLogin(@RequestParam String userName, @RequestParam String password, Model model, HttpSession session) {
        logger.info("Received request to login for user: {}", userName);
        Student student = studentService.findByUsernameAndPassword(userName, password);
        if (!Objects.isNull(student)) {
            logger.info("User {}, logged in successfully", userName);
            session.setAttribute("studentId", student.getId());
            return "redirect:/home";
        } else {
            logger.info("Authentication failed for the user or inactive account: {}", userName);
            return "redirect:/?message=failed";
        }
    }



    @GetMapping("/enroll")
    public String enrollStudent(Model model, HttpSession session) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (Objects.isNull(studentId)) {
            return "redirect:/";
        }
        model.addAttribute("programs", programService.findAllActive());
        model.addAttribute("studentId", studentId);
        return "enroll";
    }

    @PostMapping("/enroll")
    public String enrollStudent(@RequestParam Long studentId, @RequestParam String programCode, Model model) {
        Enrollment enrollment = enrollmentService.findByStudentAndProgram(studentId, programCode);
        if (Objects.isNull(enrollment)) {
            enrollment = new Enrollment();
            enrollment.setStudentId(studentId);
            enrollment.setProgramCode(programCode);
            enrollmentService.addEnrollment(enrollment);
            return "redirect:/home?message=enrolled";
        } else if (enrollment.getStatus().equals("INACTIVE")) {
            enrollment.setStatus("ACTIVE");
            enrollmentService.updateEnrollment(enrollment);
        } else {
            model.addAttribute("programs", programService.findAllActive());
            model.addAttribute("studentId", studentId);
            model.addAttribute("response", "already-enrolled");
            return "enroll";
        }
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerStudent(Model model) {
        model.addAttribute("student", new Student());
        return "register";
    }

    @PostMapping("/register")
    public String registerStudent(@ModelAttribute @Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        studentService.addStudent(student);
        return "redirect:/?message=registered";
    }

    @GetMapping("/update-info")
    public String updateStudentInfo(Model model, HttpSession session) {
        Long studentId = (Long) session.getAttribute("studentId");
        if (Objects.isNull(studentId)) {
            return "redirect:/";
        }
        Student student = studentService.findById(studentId);
        model.addAttribute("student", student);
        return "update-info";
    }

    @PostMapping("/update-info")
    public String updateStudentInfo(@ModelAttribute @Valid Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "update-info";
        }
        studentService.updateStudent(student);
        return "redirect:/home?message=updated";
    }
}
