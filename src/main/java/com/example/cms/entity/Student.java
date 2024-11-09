package com.example.cms.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @OneToOne
    @JoinColumn(name = "user_id", 
    			referencedColumnName= "userId",
    			unique = true
    )
    private User user;

    private LocalDate enrollmentDate;
    private String program;
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
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
		return "Student [studentId=" + studentId + ", user=" + user + ", enrollmentDate=" + enrollmentDate
				+ ", program=" + program + "]";
	}
}
