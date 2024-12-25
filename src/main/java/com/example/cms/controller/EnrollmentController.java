package com.example.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cms.entity.Enrollment;
import com.example.cms.service.CourseService;
import com.example.cms.service.EnrollmentService;
import com.example.cms.service.StudentService;

@Controller
@RequestMapping("")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;
    
    @Autowired
    private CourseService courseService;
    
    @Autowired
    private StudentService studentService;

    // Add new enrollment
    @PostMapping("/addEnrollment")
    public String addEnrollment(@RequestParam(name = "studentId") Long studentId, 
    		@RequestParam(name = "courseId") Long courseId, Model model) {
        try {
            Enrollment enrollment = enrollmentService.addEnrollment(studentId, courseId);
//            model.addAttribute("successMessage", "Enrollment added successfully!");
//            model.addAttribute("enrollment", enrollment);
        } catch (Exception e) {
//            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/fetchAllEnrollments";
    }
    
    // show add enrollment page
    @GetMapping("/addEnrollment")
    public String getEnrollmentPage(Model model) throws Exception {
    	model.addAttribute("courses", courseService.fetchAllCourses());
    	model.addAttribute("students", studentService.fetchAllStudents());

    	model.addAttribute("content", "EnrollStudent");
    	return "sidebar";
    }

    // Delete enrollment by id
    @GetMapping("/unenroll")
    public String deleteEnrollmentById(@RequestParam(name = "enrollmentId") Long enrollmentId, Model model) {
        try {
            enrollmentService.deleteEnrollmentById(enrollmentId);
           
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:fetchAllEnrollments";
    }

  

    // Fetch all enrollments
    @GetMapping("/fetchAllEnrollments")
    public String fetchAllEnrollments(Model model) {
        try {
            List<Enrollment> enrollments = enrollmentService.fetchAllEnrollments();
            model.addAttribute("enrollments", enrollments);
            model.addAttribute("courses", courseService.fetchAllCourses());
        } catch (Exception e) {
//            model.addAttribute("errorMessage", e.getMessage());
        }
        model.addAttribute("content", "viewEnrollment");
        return "sidebar";
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
