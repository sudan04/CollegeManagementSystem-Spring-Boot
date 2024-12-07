package com.example.cms.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.cms.entity.Department;

@SpringBootTest
class DepartmentRepositoryTest {

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Test
	public void updateDepartment() {
		Department department= departmentRepository.findByDepartmentId(1l);
		department.setDescription("asdsdajs");
		department.setName("IT");
		
		departmentRepository.save(department);
	}
}
