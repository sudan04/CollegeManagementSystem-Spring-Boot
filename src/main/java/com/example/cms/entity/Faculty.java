package com.example.cms.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facultyId;

    @OneToOne
    @JoinColumn(
    		name = "user_id",
    		referencedColumnName= "userId",
    		unique = true
    )
    private User user;

    private String department;
    private LocalDate joiningDate;
	public Long getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(Long facultyId) {
		this.facultyId = facultyId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
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
