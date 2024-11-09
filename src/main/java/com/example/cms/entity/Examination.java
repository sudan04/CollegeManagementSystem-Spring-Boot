package com.example.cms.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    @ManyToOne
    @JoinColumn(
    		name = "course_id",
    		referencedColumnName= "courseId"
    )
    private Course course;

    private LocalDate examStartDate;
    private LocalDate examEndDate;

    @Enumerated(EnumType.STRING)
    private ExamType examType;

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public LocalDate getExamStartDate() {
		return examStartDate;
	}

	public void setExamStartDate(LocalDate examStartDate) {
		this.examStartDate = examStartDate;
	}

	public LocalDate getExamEndDate() {
		return examEndDate;
	}

	public void setExamEndDate(LocalDate examEndDate) {
		this.examEndDate = examEndDate;
	}

	public ExamType getExamType() {
		return examType;
	}

	public void setExamType(ExamType examType) {
		this.examType = examType;
	}

	@Override
	public String toString() {
		return "Examination [examId=" + examId + ", course=" + course + ", examStartDate=" + examStartDate
				+ ", examEndDate=" + examEndDate + ", examType=" + examType + "]";
	}
    
   
}
