package com.example.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cms.dto.DepartmentDTO;
import com.example.cms.dtoEntityMapper.DepartmentDTOMapper;
import com.example.cms.entity.Department;
import com.example.cms.entity.Faculty;
import com.example.cms.repository.DepartmentRepository;
import com.example.cms.repository.FacultyRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentDTOMapper departmentDTOMapper;
	
	@Autowired
	private FacultyRepository facultyRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	
	
	// create new department
	public Department createDepartment(DepartmentDTO departmentDTO) {
		Department department= departmentDTOMapper.toEntity(departmentDTO);
		Faculty headOfDepartment= facultyRepository.findByFacultyId(departmentDTO.getDepartmentHead());
		department.setHeadOfDepartment(headOfDepartment);
		return departmentRepository.save(department);
	}


	// delete department by Id
	public int deleteById(Long departmentId) throws Exception {
		Department department= departmentRepository.findByDepartmentId(departmentId);
		if(department==null) throw new Exception("department not found");
		
		department.setHeadOfDepartment(null);
		departmentRepository.save(department);
		
		return departmentRepository.deleteByDepartmentId(departmentId);
	}

	// update department by Id
	public Department updateDepartment(Long departmentId, DepartmentDTO departmentDTO) throws Exception {
	    Department oldDepartment = departmentRepository.findByDepartmentId(departmentId);
	    if (departmentDTO.getName() != null && !departmentDTO.getName().isEmpty() && 
	        !departmentDTO.getName().equals(oldDepartment.getName())) {
	        
	        Department existingDepartment = departmentRepository.findByName(departmentDTO.getName());
	        if (existingDepartment != null) {
	            throw new Exception("Department name already exists");
	        }
	        
	        oldDepartment.setName(departmentDTO.getName());
	    }
	    oldDepartment.setDescription(departmentDTO.getDescription() != null && !departmentDTO.getDescription().isEmpty() 
	                                 ? departmentDTO.getDescription() 
	                                 : oldDepartment.getDescription());
	    if (departmentDTO.getDepartmentHead() != null) {
	        Faculty newDepartmentHead = facultyRepository.findByFacultyId(departmentDTO.getDepartmentHead());
	        oldDepartment.setHeadOfDepartment(newDepartmentHead != null ? newDepartmentHead : oldDepartment.getHeadOfDepartment());
	    }
	    return departmentRepository.save(oldDepartment);
	}


	//find department by id
	public Department findDepartmentById(Long departmentId) throws Exception{
		Department department= departmentRepository.findByDepartmentId(departmentId);
		if(department==null) throw new Exception("No department found By ID: "+departmentId);
		return department;
	}

	// find department by name
	public Department findDepartmentByName(String departmentName) throws Exception {
		Department department= departmentRepository.findByName(departmentName);
		if(department==null) throw new Exception("No department found By ID: "+departmentName);
		return department;
	}

	// fetch all departments
	public List<Department> fetchAllDepartments() throws Exception {
		List<Department> departments= departmentRepository.findAll();
		if(departments==null) throw new Exception("No departments found!");
		
		return departments;
	}
	
	




}
