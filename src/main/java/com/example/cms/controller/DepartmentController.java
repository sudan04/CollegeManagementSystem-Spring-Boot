package com.example.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.cms.dto.DepartmentDTO;
import com.example.cms.dtoEntityMapper.DepartmentDTOMapper;
import com.example.cms.entity.Department;
import com.example.cms.service.DepartmentService;

@Controller
@RequestMapping("/admin")
public class DepartmentController {
  @Autowired
  private DepartmentService departmentService;
  
  @Autowired
  DepartmentDTOMapper departmentDTOMapper;
  
  
  // create new department
  @PostMapping("/createDepartment")
  public String createDepartment(@ModelAttribute DepartmentDTO departmentDTO, Model model) throws Exception{
	 
	  Department newDepartment= departmentService.createDepartment(departmentDTO);
	  
	  if(newDepartment!= null) {
			model.addAttribute("department", newDepartment);
			model.addAttribute("successMessage", "Department created successfully");
	  }
	  model.addAttribute("errorMessage", "Can't create department");
	 return "";
  }
  
  
  // delete department by ID
  @DeleteMapping("/deleteById")
  public String deleteDepartmentById(@PathVariable(name= "departmentId") Long departmentId, Model model) throws Exception{
	  boolean isDeleted= departmentService.deleteById(departmentId);
	  if(isDeleted) {
		  model.addAttribute("department", "Department deleted successfully!");
	  }else {
	  model.addAttribute("errorMessage", "Department successfully deleted!");
	  }
	  
	  return "";
  }
  
  
  // update department by id 
  @PutMapping("/updateById/{departmentId}")
  public String updateDepartmentById(@PathVariable(name= "departmentID") Long departmentId,@ModelAttribute DepartmentDTO departmentDTO, Model model){
	  try {
		  Department updatedDepartment= departmentService.updateDepartment(departmentId, departmentDTO);
		  if(updatedDepartment==null) throw new Exception("Can't update department!");
		  model.addAttribute("department", updatedDepartment);
		  model.addAttribute("successMessage", "Department updated successfully!");
	  }catch (Exception e) {
		  model.addAttribute("errorMessage", e.getMessage());
	  }
	  return "";
  }
  
  
  // Find department by Id
  @GetMapping("/findDepartmentById/{departmentId}")
  public String findDepartmentById(@PathVariable(name="departmentId") Long departmentId, Model model) {
	  try {
		  Department department= departmentService.findDepartmentById(departmentId);
		  model.addAttribute("department", department);
	  }catch (Exception e) {
		  model.addAttribute("errorMessage", e.getMessage());
	  }
	  
	  return "";
  }
  
  
//Find department by name
 @GetMapping("/findDepartmentByName/{departmentName}")
 public String findDepartmentByName(@PathVariable(name="departmentName") String departmentName, Model model) {
	  try {
		  Department department= departmentService.findDepartmentByName(departmentName);
		  model.addAttribute("department", department);
	  }catch (Exception e) {
		  model.addAttribute("errorMessage", e.getMessage());
	  }
	  
	  return "";
 }
  
 
 // fetch all departments
 @GetMapping("/fetchAllDepartments")
 public String fetchAllDepartments(Model model) {
	 try {
		 List<Department> departments= departmentService.fetchAllDepartments();
		 model.addAttribute("departments", departments);
	 }catch (Exception e) {
		 model.addAttribute("errorMessage", e.getMessage());
	 }
	 return "";
 }
  
}



