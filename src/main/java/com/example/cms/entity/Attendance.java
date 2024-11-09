package com.example.cms.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attendanceId;

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
    private LocalDate date;
    private Boolean isPresent;
	public Long getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
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
	public LocalDate getDate() {
		return date;
	}
	
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Boolean getIsPresent() {
		return isPresent;
	}
	public void setIsPresent(Boolean isPresent) {
		this.isPresent = isPresent;
	}
	@Override
	public String toString() {
		return "Attendance [attendanceId=" + attendanceId + ", student=" + student + ", course=" + course + ", date="
				+ date + ", isPresent=" + isPresent + "]";
	}

   
    
}

