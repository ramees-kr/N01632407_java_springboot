package com.humber.studentrecordmanagement.controller;

import com.humber.studentrecordmanagement.entity.Course;
import com.humber.studentrecordmanagement.entity.Enrollment;
import com.humber.studentrecordmanagement.entity.Student;
import com.humber.studentrecordmanagement.service.CourseService;
import com.humber.studentrecordmanagement.service.EnrollmentService;
import com.humber.studentrecordmanagement.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    // Get student details
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // Update student details
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        return studentService.updateStudent(id, updatedStudent);
    }

    // View available courses
    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // Enroll in a course
    @PostMapping("/{studentId}/courses/{courseId}")
    public Enrollment enrollCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        return enrollmentService.enrollStudentToCourse(studentId, courseId);
    }

    // Drop a course
    @DeleteMapping("/{studentId}/courses/{courseId}")
    public String dropCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        return enrollmentService.dropEnrollment(studentId, courseId);
    }

    // View student enrollments
    @GetMapping("/{studentId}/enrollments")
    public List<Enrollment> getEnrollments(@PathVariable Long studentId) {
        return enrollmentService.getEnrollmentsByStudent(studentId);
    }
}
