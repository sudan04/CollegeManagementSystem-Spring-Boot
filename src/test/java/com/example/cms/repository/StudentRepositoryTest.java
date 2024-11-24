package com.example.cms.repository;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cms.entity.Student;

@SpringBootTest
class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private UserRepository userRepository;
	@Test
	public void registerStudent() {
		Student student= new Student();
		
		student.setProgram("BCA");
		student.setUser(userRepository.findByUserId(1));
		student.setEnrollmentDate(LocalDate.now());
		studentRepository.save(student);
	}

}
