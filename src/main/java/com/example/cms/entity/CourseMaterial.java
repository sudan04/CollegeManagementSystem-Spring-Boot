package com.example.cms.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class CourseMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialId;

    @OneToOne
    @JoinColumn(
     name = "course_id",
     referencedColumnName= "courseId",
     unique = true
    )
    private Course course;

    private String url;
    
    @CreationTimestamp
    private LocalDate uploadDate;
	public Long getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Long materialId) {
		this.materialId = materialId;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public LocalDate getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(LocalDate uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "CourseMaterial [materialId=" + materialId + ", course=" + course + ", url=" + url + ", uploadDate="
				+ uploadDate + "]";
	}
	
}

