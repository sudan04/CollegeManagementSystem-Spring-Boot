package com.example.cms.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facultyId;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(
    		name = "user_id",
    		referencedColumnName= "userId",
    		unique = true
    )
    private Users user;

   @ManyToOne
   @JoinColumn(name= "department_id", referencedColumnName= "departmentId")
   private Department department;
   
   @CreationTimestamp
   private LocalDate joiningDate;
    
	
   	public Department getDepartment() {
		return department;
	}
   
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Long getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(Long facultyId) {
		this.facultyId = facultyId;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	public LocalDate getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}
	@Override
	public String toString() {
		return "Faculty [facultyId=" + facultyId + ", user=" + user + ", department=" + department + ", joiningDate="
				+ joiningDate + "]";
	}

    
}
