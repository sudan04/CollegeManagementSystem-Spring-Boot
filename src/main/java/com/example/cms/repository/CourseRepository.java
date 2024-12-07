package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.cms.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

	@Transactional
	int deleteByCourseId(Long courseId);
	
    Course findByCourseId(Long courseId);
    
	Course findByCourseCode(String courseCode);
}
