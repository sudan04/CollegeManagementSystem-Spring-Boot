package com.example.cms.controller;

import com.example.cms.entity.Attendance;
import com.example.cms.entity.Course;
import com.example.cms.entity.Faculty;
import com.example.cms.entity.Student;
import com.example.cms.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // Mark attendance for a student in a course
    @PostMapping("/mark/{studentId}/{courseId}/{isPresent}")
    public String markAttendance(@PathVariable Long studentId,
    								@PathVariable Long courseId,
                                  @PathVariable Long facultyId,
                                  @PathVariable Boolean isPresent,
                                  Model model) {
        try {
            Attendance attendance = attendanceService.markAttendance(studentId, courseId, isPresent);
            model.addAttribute("successMessage", "Attendance marked successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error marking attendance: " + e.getMessage());
        }
        return "attendanceManagement";
    }
    
    
 // Render the page to mark attendance for a student in a specific course
    @GetMapping("/mark")
    public String showMarkAttendanceForm() {
        return "markattendance";  
    }

    
  

    // Get all attendance records for a specific course
    @GetMapping("/course/{courseId}")
    public String getAttendanceByCourse(@PathVariable Long courseId, Model model) {
        try {
            List<Attendance> attendanceList = attendanceService.getAttendanceByCourse(courseId);
            model.addAttribute("attendanceList", attendanceList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error fetching attendance for the course: " + e.getMessage());
        }
        return "attendanceList";
    }

    // Get all attendance records for a specific student in a specific course
    @GetMapping("/student/{studentId}/course/{courseId}")
    public String getAttendanceByStudentAndCourse(@PathVariable Long studentId,
                                                  @PathVariable Long courseId,
                                                  Model model) {
        try {
            List<Attendance> attendanceList = attendanceService.getAttendanceByStudentAndCourse(studentId, courseId);
            model.addAttribute("attendanceList", attendanceList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error fetching attendance: " + e.getMessage());
        }
        return "attendanceList";
    }

    // Get all attendance records by a specific faculty
    @GetMapping("/faculty/{facultyId}")
    public String getAttendanceByFaculty(@PathVariable Long facultyId, Model model) {
        try {
            List<Attendance> attendanceList = attendanceService.getAttendanceByFaculty(facultyId);
            model.addAttribute("attendanceList", attendanceList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error fetching attendance by faculty: " + e.getMessage());
        }
        return "attendanceList";
    }

    
    
    // Get attendance for a specific student in a course on a specific date
    @GetMapping("/student/{studentId}/course/{courseId}/date/{date}")
    public String getAttendanceByStudentCourseAndDate(@PathVariable Long studentId,
                                                      @PathVariable Long courseId,
                                                      @PathVariable String date,
                                                      Model model) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            List<Attendance> attendanceList = attendanceService.getAttendanceByStudentCourseAndDate(studentId, courseId, localDate);
            model.addAttribute("attendanceList", attendanceList);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error fetching attendance by date: " + e.getMessage());
        }
        return "attendanceList";
    }
}
