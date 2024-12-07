package com.example.cms.controller;

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

import com.example.cms.dto.CourseDTO;
import com.example.cms.dtoEntityMapper.CourseDTOMapper;
import com.example.cms.dtoEntityMapper.PopularCourseDTOMapper;
import com.example.cms.entity.Course;
import com.example.cms.entity.Department;
import com.example.cms.service.CourseService;
import com.example.cms.service.DepartmentService;
import com.example.cms.service.FacultyService;

@Controller
@RequestMapping("/admin")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseDTOMapper courseDTOMapper;
    
    @Autowired
    private FacultyService facultyService;
    
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PopularCourseDTOMapper popularCourseDTOMapper;

    // Create a new course
    @PostMapping("/saveCourse")
    public String createNewCourse(@ModelAttribute CourseDTO courseDetails, Model model) throws Exception {
        try {
        	Long id= courseDetails.getCourseId();
        	if(id != null)
        		courseService.updateCourseById(id, courseDetails);
        	else
        		courseService.createNewCourse(courseDetails);
//            model.addAttribute("course", courseDTOMapper.toDTO(newCourse));
//            model.addAttribute("successMessage", "Course created successfully!");
        } catch (Exception e) {
//            model.addAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/fetchAllCourse"; 
    }
    
    
    @GetMapping("/saveCourse")
    public String showAddCourseForm(@RequestParam(name="courseId", required = false) Long courseId, Model model) {
    	Course course;
    	try {
    		if(courseId != null) {
    			course= courseService.findByCourseId(courseId);
    		}else {
    			course= new Course();
    		}
    		model.addAttribute("course", course);
	        model.addAttribute("departments", departmentService.fetchAllDepartments());
	        model.addAttribute("faculties", facultyService.fetchAllFaculty());
    	}catch(Exception e) {
    		model.addAttribute("errorMessage", "Something went wrong!");
    	}
    	
    	model.addAttribute("content", "addCourse");
    	
        return "sidebar"; 
    }
    

    // Delete course by ID
    @GetMapping("/deleteCourse")
    public String deleteCourse(@RequestParam(name = "courseId") Long courseId, Model model) {
        int rowsAffected = courseService.deleteCourse(courseId);
        if (rowsAffected>=1) {
            model.addAttribute("successMessage", "Course deleted successfully!");
        } else {
            model.addAttribute("errorMessage", "Failed to delete course.");
        }
        return "redirect:/admin/fetchAllCourse"; 
    }

    // Update course by ID
    @PostMapping("/updateCourse/courseId/{courseId}")
    public String updateCourseById(@PathVariable(name = "courseId") Long courseId, @ModelAttribute CourseDTO courseDTO, Model model) {
        try {
            Course course = courseService.updateCourseById(courseId, courseDTO);
            if (course == null) throw new Exception("Course not found");
            model.addAttribute("course", courseDTOMapper.toDTO(course));
            model.addAttribute("successMessage", "Course updated successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "courseManagement";
    }

    // Fetch all courses
    @GetMapping("/fetchAllCourse")
    public String fetchAllCourse(Model model) throws Exception {
        List<Course> courseList = courseService.fetchAllCourses();
        if (courseList != null && !courseList.isEmpty()) {
            model.addAttribute("courses", courseList);
        } else {
//            model.addAttribute("errorMessage", "No courses found.");
        }
        model.addAttribute("content", "courseList");
        return "sidebar"; 
    }

    // Fetch course by courseId
    @GetMapping("/fetchCourseById/courseId/{courseId}")
    public String fetchCourseById(@PathVariable(name = "courseId") Long courseId, Model model) {
        try {
            Course course = courseService.findByCourseId(courseId);
            if (course == null) throw new Exception("Course not found");
            model.addAttribute("course", courseDTOMapper.toDTO(course));
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "courseDetails";
    }

    // Fetch course by courseCode
    @GetMapping("/fetchCourseByCourseCode/courseCode/{courseCode}")
    public String fetchCourseByCode(@PathVariable(name = "courseCode") String courseCode, Model model) {
        try {
            Course course = courseService.findByCourseCode(courseCode);
            if (course == null) throw new Exception("Course not found");
            model.addAttribute("course", courseDTOMapper.toDTO(course));
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "courseDetails";
    }

  
    
    
//    // return courseList page
//    @GetMapping("/courseList")
//    public String viewCourses(Model model)  {
//		try {
//			List<Course> courses = courseService.fetchAllCourses();
//			List<Department> departments = departmentService.fetchAllDepartments();
//			 
//	        model.addAttribute("courses", courses);
//	        model.addAttribute("departments", departments);
//		} catch (Exception e) {
//		}
//		model.addAttribute("content", "courseList");
//		
//        return "sidebar";
//    }

}
