package com.example.cms.entity;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;

@Entity
public class Department {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @OneToOne
    @JoinColumn(name="department_head", referencedColumnName= "facultyId")
    private Faculty headOfDepartment; 
    
    @CreationTimestamp
    private LocalDate addedDate;

    @Lob
    private String description;

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Faculty getHeadOfDepartment() {
		return headOfDepartment;
	}

	public void setHeadOfDepartment(Faculty headOfDepartment) {
		this.headOfDepartment = headOfDepartment;
	}

	public LocalDate getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(LocalDate addedDate) {
		this.addedDate = addedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", name=" + name + ", headOfDepartment=" + headOfDepartment
				+ ", addedDate=" + addedDate + ", description=" + description + "]";
	}

	
  
}