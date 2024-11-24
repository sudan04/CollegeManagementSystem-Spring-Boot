package com.example.cms.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cms.entity.Course;


@SpringBootTest
class CourseRepositoryTest {

	@Autowired
	private CourseRepository courseRepository;
	
	@Test
	public void createCourse() {
		Course course= new Course();
		course.setCourseName("Data Structures and Algorithm");
		course.setCourseCode("cs201");
		course.setCredits(12);
		course.setCourseMaterial("www.learnDsa.com");
		
		courseRepository.save(course);
		
	}

}
