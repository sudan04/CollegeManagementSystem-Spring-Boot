package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.Student;
import com.example.cms.entity.Users;

public interface StudentRepository extends JpaRepository<Student, Long>{

	Student findByStudentId(Long studentId);

	Student findByUser(Users users);

	

	
}
