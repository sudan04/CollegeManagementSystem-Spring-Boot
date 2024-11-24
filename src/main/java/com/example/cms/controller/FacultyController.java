package com.example.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cms.entity.Faculty;
import com.example.cms.service.FacultyService;

@Controller
@RequestMapping("/admin")
public class FacultyController {
	
	@Autowired
	private FacultyService facultyService;
	
	//delete faculty by id
	@DeleteMapping("/deleteFacultyById/{facultyId}")
	public String deleteFacultyById(@PathVariable(name = "facultyId") Long facultyId, Model model) {
	    try {
	        boolean isDeleted = facultyService.deleteByFacultyId(facultyId);
	        if (isDeleted) {
	            model.addAttribute("successMessage", "Faculty deleted successfully!!");
	        } else {
	            model.addAttribute("errorMessage", "Faculty could not be deleted!");
	        }
	    } catch (Exception e) {
	        model.addAttribute("errorMessage", e.getMessage());
	    }
	    return "facultyManagementPage"; 
	}
	
	
	@PutMapping("/assignFacultyToDepartment/{facultyId}/{departmentId}")
	public String assignFacultyToDepartment(@PathVariable(name="facultyId") Long facultyId, 
	                                         @PathVariable(name="departmentId") Long departmentId, 
	                                         Model model) {
	    try {
	        Faculty faculty = facultyService.assignFacultyToDepartment(facultyId, departmentId);
	        model.addAttribute("successMessage", "Faculty assigned to department successfully!");
	    } catch (Exception e) {
	        model.addAttribute("errorMessage", e.getMessage());
	    }
	    return "facultyManagementPage"; 
	}
	
	
		// find faculty by Id
		@GetMapping("/fingFacultyById")
		public String findFacultyById(@PathVariable(name="facultyId") Long facultyId, Model model) {
			try {
				Faculty faculty= facultyService.findFacultyById(facultyId);
				model.addAttribute("faculty", faculty);
			}catch(Exception e) {
				model.addAttribute("errorMessage", e.getMessage());
			}
			return "";
		}
		
		
		
		// fetch all faculty
		@GetMapping("/fetchAllFaculty")
		public String fetchAllFaculty(Model model) throws Exception{
			try {
				List<Faculty> faculties= facultyService.fetchAllFaculty();
				model.addAttribute("faculties", faculties);
			}catch (Exception e) {
				model.addAttribute("errorMessage", e.getMessage());
			}
			return "";
		}


}
