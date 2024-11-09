package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
}
