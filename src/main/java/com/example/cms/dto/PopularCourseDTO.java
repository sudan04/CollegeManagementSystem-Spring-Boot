package com.example.cms.dto;

public class PopularCourseDTO {
    private Long courseId;
    private String courseName;
    private Long enrollmentCount;
    
    
    
    
    
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Long getEnrollmentCount() {
		return enrollmentCount;
	}
	public void setEnrollmentCount(Long enrollmentCount) {
		this.enrollmentCount = enrollmentCount;
	}
	
    
    
}
