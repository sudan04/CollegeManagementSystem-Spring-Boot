package com.example.cms.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    @ManyToOne
    @JoinColumn(
    		name = "student_id",
    		referencedColumnName= "studentId"
    )
    private Student student;

    @ManyToOne
    @JoinColumn(
    		name = "course_id",
    		referencedColumnName= "courseId"
    )
    private Course course;

    @CreationTimestamp
    private LocalDate enrollmentDate;
	public Long getEnrollmentId() {
		return enrollmentId;
	}
	public void setEnrollmentId(Long enrollmentId) {
		this.enrollmentId = enrollmentId;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	
	@Override
	public String toString() {
		return "Enrollment [enrollmentId=" + enrollmentId + ", student=" + student + ", course=" + course
				+ ", enrollmentDate=" + enrollmentDate + "]";
	}

	
	
}

