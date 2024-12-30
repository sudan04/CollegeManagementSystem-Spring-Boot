package com.example.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cms.dto.AttendanceDTO;
import com.example.cms.dto.AttendanceFilterDTO;
import com.example.cms.entity.Attendance;
import com.example.cms.entity.Course;
import com.example.cms.entity.Student;
import com.example.cms.entity.Users;
import com.example.cms.repository.CourseRepository;
import com.example.cms.repository.UserRepository;
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
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CourseRepository courseORepository;
    
    
    
    

 // Mark attendance for a student in a course
    @PostMapping("/faculty/markAttendance")
    public String handleAttendanceSubmission( @ModelAttribute AttendanceDTO attendanceDTO, Model model) {
    	try {
         attendanceService.saveAttendance(attendanceDTO);
    	}catch(Exception e) {
    		model.addAttribute("errMessage", e.getMessage());
    		return "redirect:/faculty/markAttendance?courseId=" + attendanceDTO.getCourseId();
    	}
        return "redirect:/attendance/view";  
    }



    // Render the page to mark attendance for a student in a specific course
    @GetMapping("/faculty/markAttendance")
    public String showMarkAttendanceForm(@RequestParam(name = "courseId", required = false) Long courseId, Model model) throws Exception {
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name;

        if (principal instanceof UserDetails) {
            name = ((UserDetails) principal).getUsername();
        } else {
            name = principal.toString();
        }
        
        Users user = userRepository.findByUserName(name);
    	
    	if (courseId != null) {
    		Course selectedCourse= courseService.findByCourseId(courseId);
    		List<Student> students= enrollmentService.findStudentByCourseId(courseId);
   
            model.addAttribute("students", students);
            model.addAttribute("selectedCourse", selectedCourse);
        }
        model.addAttribute("attenDTO", new AttendanceDTO());
        model.addAttribute("courses", courseORepository.findByFacultyUser(user));
        model.addAttribute("content", "markAttendance");
        return "faculty_nav";
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
