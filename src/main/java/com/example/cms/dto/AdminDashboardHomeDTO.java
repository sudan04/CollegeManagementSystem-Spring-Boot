package com.example.cms.dto;

import java.util.HashMap;

public class AdminDashboardHomeDTO {

	private Long student;
	private Long course;
	private Long faculties;
	
	
	private HashMap<String, Long> enrollmentCourse;
	public Long getStudent() {
		return student;
	}
	public void setStudent(Long student) {
		this.student = student;
	}
	public Long getCourse() {
		return course;
	}
	public void setCourse(Long course) {
		this.course = course;
	}
	public Long getFaculties() {
		return faculties;
	}
	public void setFaculties(Long faculties) {
		this.faculties = faculties;
	}
	public HashMap<String, Long> getEnrollmentCourse() {
		return enrollmentCourse;
	}
	public void setEnrollmentCourse(HashMap<String, Long> enrollmentCourse) {
		this.enrollmentCourse = enrollmentCourse;
	}
	

}
