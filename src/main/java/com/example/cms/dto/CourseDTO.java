package com.example.cms.dto;

public class CourseDTO {
	private Long courseId;
    private String courseCode;
    private String courseName;
    private int credits;
    private Long departmentId;
    private Long facultyId;
    private String courseMaterial;
    
    
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(Long facultyId) {
		this.facultyId = facultyId;
	}
	public String getCourseMaterial() {
		return courseMaterial;
	}
	public void setCourseMaterial(String courseMaterial) {
		this.courseMaterial = courseMaterial;
	}
    
    
    
}
