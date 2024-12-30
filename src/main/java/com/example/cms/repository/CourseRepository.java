package com.example.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.cms.entity.Course;
import com.example.cms.entity.Users;

public interface CourseRepository extends JpaRepository<Course, Long>{

	@Transactional
	int deleteByCourseId(Long courseId);
	
    Course findByCourseId(Long courseId);
    
	Course findByCourseCode(String courseCode);

	List<Course> findByFacultyUser(Users user);
}
