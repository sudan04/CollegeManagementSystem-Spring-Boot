package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Long>{
 public Faculty findByFacultyId(Long facultyId);

public boolean deleteByFacultyId(Long facultyId);
}  
