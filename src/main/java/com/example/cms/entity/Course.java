package com.example.cms.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(unique = true, nullable = false)
    private String courseCode;
    private String courseName;
    private int credits;
    
    @ManyToOne(optional= true)
    @JoinColumn(name= "department_id", referencedColumnName= "departmentId")
    private Department department;

    @ManyToOne(optional= true)
    @JoinColumn(name = "faculty_id", referencedColumnName = "facultyId")
    private Faculty faculty;
   
    @OneToMany(mappedBy= "course", cascade= CascadeType.REMOVE)
    private List<Attendance> attendance;
    
    @OneToMany(mappedBy= "course", cascade= CascadeType.REMOVE)
    private List<Enrollment> enrollment;
    
    private String courseMaterial;
    
    
    
    
    
	
	public String getCourseMaterial() {
		return courseMaterial;
	}

	public void setCourseMaterial(String courseMaterial) {
		this.courseMaterial = courseMaterial;
	}

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


	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseCode=" + courseCode + ", courseName=" + courseName
				+ ", credits=" + credits + ", department=" + department + ", faculty=" + faculty + ", courseMaterial="
				+ courseMaterial + "]";
	}

	

}
