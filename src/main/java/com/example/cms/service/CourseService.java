package com.example.cms.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cms.dto.CourseDTO;
import com.example.cms.dtoEntityMapper.CourseDTOMapper;
import com.example.cms.entity.Course;
import com.example.cms.entity.Department;
import com.example.cms.entity.Faculty;
import com.example.cms.repository.CourseRepository;
import com.example.cms.repository.DepartmentRepository;
import com.example.cms.repository.EnrollmentRepository;
import com.example.cms.repository.FacultyRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CourseDTOMapper courseDTOMapper;
	
	@Autowired
	private FacultyRepository facultyRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	
	// creating new course
	public Course createNewCourse(CourseDTO courseDetails) {
		if(courseDetails==null) return null;
		
		Faculty faculty= facultyRepository.findByFacultyId(courseDetails.getFacultyId());
		Department department= departmentRepository.findByDepartmentId(courseDetails.getDepartmentId());
		
		Course newCourse= courseDTOMapper.toCourse(courseDetails);
		newCourse.setFaculty(faculty);
		newCourse.setDepartment(department);
		
		return courseRepository.save(newCourse);
	}

	
	//delete course by courseId
	public int deleteCourse(Long courseId) {
		return courseRepository.deleteByCourseId(courseId);
	}


	
	// update course by courseId
	public Course updateCourseById(Long courseId, CourseDTO courseDTO) {
		Course oldCourse= courseRepository.findByCourseId(courseId);
		
		Course newCourse= courseDTOMapper.toCourse(courseDTO);
		
		Department department= departmentRepository.findByDepartmentId(courseDTO.getDepartmentId());
		Faculty faculty= facultyRepository.findByFacultyId(courseDTO.getFacultyId());
		
		oldCourse.setCourseName(newCourse.getCourseName()!=null && !newCourse.getCourseName().equals("")? newCourse.getCourseName():oldCourse.getCourseName());
		oldCourse.setCourseCode(newCourse.getCourseCode()!=null && !newCourse.getCourseCode().equals("")? newCourse.getCourseCode():oldCourse.getCourseCode());
		oldCourse.setCourseMaterial(newCourse.getCourseMaterial() != null && !newCourse.getCourseMaterial().equals("")? newCourse.getCourseMaterial():oldCourse.getCourseMaterial());
		oldCourse.setCredits(newCourse.getCredits()!=0?newCourse.getCredits():oldCourse.getCredits());
		oldCourse.setFaculty(faculty!=null? faculty: oldCourse.getFaculty());
		oldCourse.setDepartment(department!=null? department: oldCourse.getDepartment());
		return courseRepository.save(oldCourse);
	}

	// fetch all courses
	public List<Course> fetchAllCourses() throws Exception {
		List<Course> courseList= courseRepository.findAll();
		
		if(courseList==null) throw new Exception("No courses available");
		
		return courseList;
	}


	// fetch course by id
	public Course findByCourseId(Long courseId) throws Exception {
		Course course= courseRepository.findByCourseId(courseId);
		if(course==null) throw new Exception("Course not found");
		return course;
	}


	public Course findByCourseCode(String courseCode) throws Exception {
		Course course= courseRepository.findByCourseCode(courseCode);
		if(course==null) throw new Exception("Course not found");
		return course;
	}


	//fetch topN most popular courses

    public HashMap<String, Integer> getMostPopularCourses(int topN) {
        List<Object[]> results = enrollmentRepository.getMostPopularCourses(topN);
        HashMap<String, Integer> studentEnrollment = new HashMap<>();

        for (Object[] result : results) {
            String courseName = (String) result[0];
            Integer enrollmentCount = ((Number) result[1]).intValue();
            studentEnrollment.put(courseName, enrollmentCount);
        }

        return studentEnrollment;
    }



	// get total course count
	public Long getCourseCount() {
		return courseRepository.count();
	}

}
