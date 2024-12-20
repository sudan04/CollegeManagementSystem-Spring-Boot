package com.example.cms.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "user_id", 
    			referencedColumnName= "userId",
    			unique = true,
    			updatable = false
    )
    private Users user;
    

    @CreationTimestamp
    private LocalDate enrollmentDate;
    private String program;
    
    @OneToMany(mappedBy= "student", cascade= CascadeType.REMOVE)
    private List<Attendance> attendance;
     
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", user=" + user + ", enrollmentDate="
				+ enrollmentDate + ", program=" + program + "]";
	}
	
	
}
