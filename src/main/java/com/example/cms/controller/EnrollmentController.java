package com.example.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.cms.entity.Enrollment;
import com.example.cms.service.EnrollmentService;

@Controller
@RequestMapping("/admin")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    // Add new enrollment
    @PostMapping("/addEnrollment/{studentId}/{courseId}")
    public String addEnrollment(@PathVariable(name = "studentId") Long studentId, 
                                 @PathVariable(name = "courseId") Long courseId, Model model) {
        try {
            Enrollment enrollment = enrollmentService.addEnrollment(studentId, courseId);
            model.addAttribute("successMessage", "Enrollment added successfully!");
            model.addAttribute("enrollment", enrollment);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "enrollmentManagementPage";
    }

    // Delete enrollment by id
    @DeleteMapping("/deleteEnrollmentById/{enrollmentId}")
    public String deleteEnrollmentById(@PathVariable(name = "enrollmentId") Long enrollmentId, Model model) {
        try {
            boolean isDeleted = enrollmentService.deleteEnrollmentById(enrollmentId);
            if (isDeleted) {
                model.addAttribute("successMessage", "Enrollment deleted successfully!");
            } else {
                model.addAttribute("errorMessage", "Failed to delete enrollment!");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "enrollmentManagementPage";
    }

    // Find enrollment by id
    @GetMapping("/findEnrollmentById/{enrollmentId}")
    public String findEnrollmentById(@PathVariable(name = "enrollmentId") Long enrollmentId, Model model) {
        try {
            Enrollment enrollment = enrollmentService.findEnrollmentById(enrollmentId);
            model.addAttribute("enrollment", enrollment);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "enrollmentDetailsPage";
    }

    // Fetch all enrollments
    @GetMapping("/fetchAllEnrollments")
    public String fetchAllEnrollments(Model model) {
        try {
            List<Enrollment> enrollments = enrollmentService.fetchAllEnrollments();
            model.addAttribute("enrollments", enrollments);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "enrollmentManagementPage";
    }
    
    
    
 // Get enrollments by student
    @GetMapping("/getEnrollmentsByStudent/{studentId}")
    public String getEnrollmentsByStudent(@PathVariable(name = "studentId") Long studentId, Model model) {
        try {
            List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(studentId);
            model.addAttribute("enrollments", enrollments);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "enrollmentByStudentPage";
    }

    
    // Get enrollments by course
    @GetMapping("/getEnrollmentsByCourse/{courseId}")
    public String getEnrollmentsByCourse(@PathVariable(name = "courseId") Long courseId, Model model) {
        try {
            List<Enrollment> enrollments = enrollmentService.getEnrollmentsByCourse(courseId);
            model.addAttribute("enrollments", enrollments);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "enrollmentByCoursePage";
    }
}
