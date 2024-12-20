package com.example.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cms.dto.DepartmentDTO;
import com.example.cms.dtoEntityMapper.DepartmentDTOMapper;
import com.example.cms.entity.Department;
import com.example.cms.service.DepartmentService;
import com.example.cms.service.FacultyService;

@Controller
@RequestMapping("/admin")
public class DepartmentController {
  @Autowired
  private DepartmentService departmentService;
  
  @Autowired
  private DepartmentDTOMapper departmentDTOMapper;
  
  @Autowired
  private FacultyService facultyService;
  
  
  
  @PostMapping("/saveDepartment")
  public String saveDepartment(@ModelAttribute DepartmentDTO departmentDTO, Model model) throws Exception {
	  
      Department department;
      System.out.println(departmentDTO.getDepartmentHead());
      Long id= departmentDTO.getDepartmentId();
      if (id != null) {
          department = departmentService.updateDepartment(id, departmentDTO); 
      } else {
          department = departmentService.createDepartment(departmentDTO);
      }
//      model.addAttribute("departments", departmentService.fetchAllDepartments());
//      model.addAttribute("content", "viewDepartment");
      return "redirect:/admin/viewDepartments";
  }


  
  
  // show department form 
  @GetMapping("/saveDepartment")
  public String showForm(@RequestParam(name="id", required = false) Long id, Model model) throws Exception {
      Department department;
      if (id != null) {
          department = departmentService.findDepartmentById(id);
      } else {
          department = null;
      }
      model.addAttribute("faculties", facultyService.fetchAllFaculty());
      model.addAttribute("department", department);
      model.addAttribute("content", "addDepartment");
      return "sidebar"; 
  }
  
  
  // delete department by ID
  @GetMapping("/deleteById")
  public String deleteDepartmentById(@RequestParam(name= "departmentId") Long departmentId, Model model) throws Exception{
	  int rowsAffected= departmentService.deleteById(departmentId);
	  if(rowsAffected>=1) {
//		  model.addAttribute("department", "Department deleted successfully!");
	  }else {
//	  model.addAttribute("errorMessage", "Department successfully deleted!");
	  }
//	  model.addAttribute("departments", departmentService.fetchAllDepartments());
//	  model.addAttribute("content", "viewDepartment");
	  return "redirect:/admin/viewDepartments"; 
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
 
 
 
// 
// // create and update department
// @PostMapping("/saveDepartment")
// public String saveDepartment(@ModelAttribute DepartmentDTO department) throws Exception {
//     if (department.getDepartmentId() == null) {
//         departmentService.createDepartment(department);
//     } else {
//         departmentService.updateDepartment(department.getDepartmentId(), department);
//     }
//     return "redirect:/departments";
// }

  
 
 // fetch all departments
 @GetMapping("/viewDepartments")
 public String fetchAllDepartments(Model model) {
	 try {
		 List<Department> departments= departmentService.fetchAllDepartments();
		 model.addAttribute("departments", departments);
	 }catch (Exception e) {
		 model.addAttribute("errorMessage", e.getMessage());
	 }
	 model.addAttribute("content", "viewDepartment");
	 return "sidebar";
 }
  
}



