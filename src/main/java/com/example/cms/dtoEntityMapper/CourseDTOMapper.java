package com.example.cms.dtoEntityMapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.cms.dto.CourseDTO;
import com.example.cms.entity.Course;

@Component
public class CourseDTOMapper {
  public Course toCourse(CourseDTO courseDTO) {
	  Course course= new Course();
	  course.setCourseCode(courseDTO.getCourseCode());
	  course.setCourseName(courseDTO.getCourseName());
	  course.setCourseMaterial(courseDTO.getCourseMaterial());
	  course.setCredits(courseDTO.getCredits());
	  
	  return course;
  }
  
  
  public CourseDTO toDTO(Course course) {
	  CourseDTO courseDTO= new CourseDTO();
	  courseDTO.setCourseName(course.getCourseName());
	  courseDTO.setCourseCode(course.getCourseCode());
	  courseDTO.setCourseMaterial(course.getCourseMaterial());
	  courseDTO.setCourseId(course.getCourseId());
	  courseDTO.setCredits(course.getCredits());
	  courseDTO.setDepartmentId(course.getDepartment().getDepartmentId());
	  courseDTO.setFacultyId(course.getFaculty().getFacultyId());
	  
	  return courseDTO;
  }
  
  public List<CourseDTO> toDTOs(List<Course> courseList) {
	  
	  List<CourseDTO> courseDTOs= new ArrayList();
	  for(Course course: courseList) {
		  CourseDTO courseDTO= new CourseDTO();
		  courseDTO.setCourseName(course.getCourseName());
		  courseDTO.setCourseCode(course.getCourseCode());
		  courseDTO.setCourseMaterial(course.getCourseMaterial());
		  courseDTO.setCourseId(course.getCourseId());
		  courseDTO.setCredits(course.getCredits());
		  courseDTO.setDepartmentId(course.getDepartment().getDepartmentId());
		  courseDTO.setFacultyId(course.getFaculty().getFacultyId());
		  
		  courseDTOs.add(courseDTO);
	  }
	  
	  return courseDTOs;
  }
}
