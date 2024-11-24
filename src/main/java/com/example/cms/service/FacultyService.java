package com.example.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cms.entity.Department;
import com.example.cms.entity.Faculty;
import com.example.cms.entity.Users;
import com.example.cms.repository.DepartmentRepository;
import com.example.cms.repository.FacultyRepository;

@Service
public class FacultyService {

	@Autowired
	private FacultyRepository facultyRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	// add new faculty
	public Faculty addFaculty(Users newUser) throws Exception {
		if(newUser==null) throw new Exception("Cant create faculty!");
		Faculty newFaculty= new Faculty();
		newFaculty.setUser(newUser);
		
		return facultyRepository.save(newFaculty);
	}

	//delete faculty by id
	public boolean deleteByFacultyId(Long facultyId) throws Exception {
		boolean isDeleted= facultyRepository.deleteByFacultyId(facultyId);
		if(!isDeleted) throw new Exception("Error occured while deleting the faculty!!");
		return true;
	}

	
	
	
	//assign faculty to department
	public Faculty assignFacultyToDepartment(Long facultyId, Long departmentId) throws Exception {
		Faculty faculty= facultyRepository.findByFacultyId(facultyId);
		Department department= departmentRepository.findByDepartmentId(departmentId);
		
		faculty.setDepartment(department);
		Faculty updatedFaculty= facultyRepository.save(faculty);
		if(updatedFaculty==null) throw new Exception("Can't assign faculty to department");
		
		return updatedFaculty;
	}

	
	// find faculty by Id
	public Faculty findFacultyById(Long facultyId) throws Exception {
			Faculty faculty= facultyRepository.findByFacultyId(facultyId);
			if(faculty==null) throw new Exception("Cant find faculty!!");
			
			return faculty;
	}
	
	
	// fetch all faculty
	public List<Faculty> fetchAllFaculty() throws Exception{
		List<Faculty> faculties= facultyRepository.findAll();
		if(faculties==null) throw new Exception("No faculties exist!!");
		
		return faculties;
	}

}
