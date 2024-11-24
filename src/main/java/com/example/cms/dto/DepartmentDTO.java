package com.example.cms.dto;

import java.time.LocalDate;

public class DepartmentDTO {
	private Long departmentId;
	private LocalDate addedDate;
	private String name;
	private Long departmentHead;
	private String description;
	
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public LocalDate getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(LocalDate addedDate) {
		this.addedDate = addedDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getDepartmentHead() {
		return departmentHead;
	}
	public void setDepartmentHead(Long departmentHead) {
		this.departmentHead = departmentHead;
	}
	
	
}
