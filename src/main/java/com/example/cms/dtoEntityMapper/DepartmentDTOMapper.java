package com.example.cms.dtoEntityMapper;

import org.springframework.stereotype.Component;

import com.example.cms.dto.DepartmentDTO;
import com.example.cms.entity.Department;

@Component
public class DepartmentDTOMapper {
	public Department toEntity(DepartmentDTO departmentDTO) {
		Department department= new Department();
		 department.setName(departmentDTO.getName());
		 department.setDescription(departmentDTO.getDescription());
		 
		 
		 return department;
	}
}
