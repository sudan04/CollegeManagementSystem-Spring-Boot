package com.example.cms.entity;

import jakarta.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(unique = true, nullable = false)
    private String courseCode;

    private String courseName;
    private int credits;
    

    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL)
    private CourseMaterial courseMaterial;

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


	public CourseMaterial getCourseMaterial() {
		return courseMaterial;
	}

	public void setCourseMaterial(CourseMaterial courseMaterial) {
		this.courseMaterial = courseMaterial;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseCode=" + courseCode + ", courseName=" + courseName
				+ ", credits=" + credits +", courseMaterial=" + courseMaterial + "]";
	}
    
    
    

}
