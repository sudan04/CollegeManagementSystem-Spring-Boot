package com.example.cms.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @ManyToOne
    @JoinColumn(
    		name = "generated_by",
    		referencedColumnName= "userId"
    )
    private User generatedBy;

    @CreationTimestamp
    private LocalDate reportDate;
    private String details;
	public Long getReportId() {
		return reportId;
	}
	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}
	
	public User getGeneratedBy() {
		return generatedBy;
	}
	public void setGeneratedBy(User generatedBy) {
		this.generatedBy = generatedBy;
	}
	public LocalDate getReportDate() {
		return reportDate;
	}
	public void setReportDate(LocalDate reportDate) {
		this.reportDate = reportDate;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	@Override
	public String toString() {
		return "Report [reportId=" + reportId + ", generatedBy=" + generatedBy
				+ ", reportDate=" + reportDate + ", details=" + details + "]";
	}

}

