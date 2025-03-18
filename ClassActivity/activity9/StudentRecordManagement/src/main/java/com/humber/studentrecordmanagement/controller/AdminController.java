package com.humber.studentrecordmanagement.controller;

import com.humber.studentrecordmanagement.entity.Course;
import com.humber.studentrecordmanagement.entity.Student;
import com.humber.studentrecordmanagement.service.CourseService;
import com.humber.studentrecordmanagement.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final StudentService studentService;
    private final CourseService courseService;

    // STUDENT MANAGEMENT

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return studentService.registerStudent(student);
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        return studentService.updateStudent(id, updatedStudent);
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        student.setActive(false);
        studentService.updateStudent(id, student);
        return "Student soft-deleted successfully";
    }

    // COURSE MANAGEMENT

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/courses/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @PutMapping("/courses/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        return courseService.updateCourse(id, updatedCourse);
    }

    @DeleteMapping("/courses/{id}")
    public String deleteCourse(@PathVariable Long id) {
        return courseService.deleteCourse(id);
    }
}
