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

import com.example.cms.dto.CourseDTO;
import com.example.cms.dtoEntityMapper.CourseDTOMapper;
import com.example.cms.dtoEntityMapper.PopularCourseDTOMapper;
import com.example.cms.entity.Course;
import com.example.cms.service.CourseService;

@Controller
@RequestMapping("/admin")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseDTOMapper courseDTOMapper;

    @Autowired
    private PopularCourseDTOMapper popularCourseDTOMapper;

    // Create a new course
    @PostMapping("/createNewCourse")
    public String createNewCourse(@ModelAttribute CourseDTO courseDetails, Model model) {
        try {
            Course newCourse = courseService.createNewCourse(courseDetails);
            if (newCourse == null) throw new Exception("Course creation denied");
            model.addAttribute("course", courseDTOMapper.toDTO(newCourse));
            model.addAttribute("successMessage", "Course created successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "courseManagement"; 
    }

    // Delete course by ID
    @GetMapping("/deleteCourse/courseId/{courseId}")
    public String deleteCourse(@PathVariable(name = "courseId") Long courseId, Model model) {
        boolean isDeleted = courseService.deleteCourse(courseId);
        if (isDeleted) {
            model.addAttribute("successMessage", "Course deleted successfully!");
        } else {
            model.addAttribute("errorMessage", "Failed to delete course.");
        }
        return "courseManagement"; 
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
            model.addAttribute("courses", courseDTOMapper.toDTOs(courseList));
        } else {
            model.addAttribute("errorMessage", "No courses found.");
        }
        return "courseList"; 
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

    // Find top N most enrolled courses
    @GetMapping("/topNEnrolledCourse/{n}")
    public String fetchTopNEnrolledCourses(@PathVariable(name = "n") int n, Model model) {
        List<Object[]> topNCourses = courseService.fetchTopNCourse(n);
        if (topNCourses != null && !topNCourses.isEmpty()) {
            model.addAttribute("popularCourses", popularCourseDTOMapper.toPopularCourseDTOs(topNCourses));
        } else {
            model.addAttribute("errorMessage", "No popular courses found.");
        }
        return "popularCourses";
    }
}
