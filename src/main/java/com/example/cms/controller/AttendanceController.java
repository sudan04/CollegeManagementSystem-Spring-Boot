package com.example.cms.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cms.dto.AttendanceDTO;
import com.example.cms.dto.AttendanceFilterDTO;
import com.example.cms.entity.Attendance;
import com.example.cms.entity.Course;
import com.example.cms.entity.Student;
import com.example.cms.service.AttendanceService;
import com.example.cms.service.CourseService;
import com.example.cms.service.EnrollmentService;
import com.example.cms.service.StudentService;

@Controller
@RequestMapping("")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;
    
    @Autowired
    private StudentService studentService;
    
    @Autowired 
    private CourseService courseService;
    
    @Autowired
    private EnrollmentService enrollmentService;
    
    

 // Mark attendance for a student in a course
    @PostMapping("/markAttendance")
    public String handleAttendanceSubmission( @ModelAttribute AttendanceDTO attendanceDTO, Model model) {
    	try {
         attendanceService.saveAttendance(attendanceDTO);
    	}catch(Exception e) {
    		model.addAttribute("errMessage", e.getMessage());
    		return "redirect:/markAttendance?courseId=" + attendanceDTO.getCourseId();
    	}
        return "redirect:/admin/adminHomeData";  
    }



    // Render the page to mark attendance for a student in a specific course
    @GetMapping("/markAttendance")
    public String showMarkAttendanceForm(@RequestParam(name = "courseId", required = false) Long courseId, Model model) throws Exception {
        Course selectedCourse;
        List<Student> students;
    	
    	if (courseId != null) {
    		selectedCourse= courseService.findByCourseId(courseId);
    		students= enrollmentService.findStudentByCourseId(courseId);
    		
            model.addAttribute("students", students);
            model.addAttribute("selectedCourse", selectedCourse);
        }
        model.addAttribute("attenDTO", new AttendanceDTO());
        model.addAttribute("courses", courseService.fetchAllCourses());
        model.addAttribute("content", "markAttendance");
        return "sidebar";  // Adjusted view name
    }


    
  
//
//    // Get all attendance records for a specific course
//    @GetMapping("/course/{courseId}")
//    public String getAttendanceByCourse(@PathVariable Long courseId, Model model) {
//        try {
//            List<Attendance> attendanceList = attendanceService.getAttendanceByCourse(courseId);
//            model.addAttribute("attendanceList", attendanceList);
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", "Error fetching attendance for the course: " + e.getMessage());
//        }
//        return "attendanceList";
//    }

   

    // get viewAttendance form
    @GetMapping("attendance/view")
    public String viewAttendance(@ModelAttribute AttendanceFilterDTO filterDto, Model model) {
        List<Attendance> attendanceList = null;
//        System.out.println(filterDto.getStartDate());
//        System.out.println(filterDto.getEndDate());

        try {
            List<Student> students = studentService.fetchAllStudents();
            List<Course> courses = courseService.fetchAllCourses();

            model.addAttribute("students", students);
            model.addAttribute("courses", courses);

            attendanceList = attendanceService.filterAttendanceRecords(filterDto);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }

        model.addAttribute("attendanceList", attendanceList);
        model.addAttribute("content", "viewAttendance");

        return "sidebar";
    }

    
    
//    // Get attendance for a specific student in a course on a specific date
//    @GetMapping("/student/{studentId}/course/{courseId}/date/{date}")
//    public String getAttendanceByStudentCourseAndDate(@PathVariable Long studentId,
//                                                      @PathVariable Long courseId,
//                                                      @PathVariable String date,
//                                                      Model model) {
//        try {
//            LocalDate localDate = LocalDate.parse(date);
//            List<Attendance> attendanceList = attendanceService.getAttendanceByStudentCourseAndDate(studentId, courseId, localDate);
//            model.addAttribute("attendanceList", attendanceList);
//        } catch (Exception e) {
//            model.addAttribute("errorMessage", "Error fetching attendance by date: " + e.getMessage());
//        }
//        return "attendanceList";
//    }
}
