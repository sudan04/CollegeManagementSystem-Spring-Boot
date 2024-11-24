package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

	boolean deleteByCourseId(Long courseId);
	
    Course findByCourseId(Long courseId);
    
	Course findByCourseCode(String courseCode);
}
