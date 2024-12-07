package com.example.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cms.entity.Student;
import com.example.cms.securityConfig.MyUserDetails;
import com.example.cms.service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Delete student by id
    @DeleteMapping("/deleteStudentById/{studentId}")
    public String deleteStudentById(@PathVariable(name = "studentId") Long studentId, Model model) {
        try {
            boolean isDeleted = studentService.deleteByStudentId(studentId);
            if (isDeleted) {
                model.addAttribute("successMessage", "Student deleted successfully!!");
            } else {
                model.addAttribute("errorMessage", "Student could not be deleted!");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "studentManagementPage"; 
    }

    // Assign program to student
    @PutMapping("/assignProgramToStudent/{studentId}/{program}")
    public String assignProgramToStudent(@PathVariable(name = "studentId") Long studentId, 
                                         @PathVariable(name = "program") String program, 
                                         Model model) {
        try {
            Student student = studentService.assignProgramToStudent(studentId, program);
            model.addAttribute("successMessage", "Program assigned to student successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "studentManagementPage"; 
    }

    // Find student by id
    @GetMapping("/findStudentById/{studentId}")
    public String findStudentById(@PathVariable(name = "studentId") Long studentId, Model model) {
        try {
            Student student = studentService.findStudentById(studentId);
            model.addAttribute("student", student);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "studentDetailsPage"; 
    }

    // Fetch all students
    @GetMapping("/fetchAllStudents")
    public String fetchAllStudents(Model model) {
        try {
            List<Student> students = studentService.fetchAllStudents();
            model.addAttribute("students", students);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "studentManagementPage"; 
    }
    
 
    
    
    
//    @GetMapping("/viewStudentDetails")
//    public String getStudentDataa(@AuthenticationPrincipal MyUserDetails user,HttpSession session) {
//    	session.getAttribute("session");
//    	Student student =studentService.findStudentDetailByUserId(user);
//    	return "done";
//    }
//    
    
}
