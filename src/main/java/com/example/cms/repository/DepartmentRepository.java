package com.example.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.cms.entity.Department;



@Transactional
public interface DepartmentRepository extends JpaRepository<Department, Long>{
	
	Department findByDepartmentId(Long departmentId);

	int deleteByDepartmentId(Long departmentId);

	Department findByName(String departmentName);

}
