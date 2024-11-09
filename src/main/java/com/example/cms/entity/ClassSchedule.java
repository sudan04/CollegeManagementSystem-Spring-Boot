package com.example.cms.entity;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.time.DayOfWeek;

@Entity
public class ClassSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @ManyToOne
    @JoinColumn(
    		name = "course_id",
    		referencedColumnName="courseId"
    )
    private Course course;

    @ManyToOne
    @JoinColumn(
    		name = "faculty_id",
    		referencedColumnName="facultyId"
    )
    private Faculty faculty;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;
    
    private LocalTime startTime;
    private LocalTime endTime;
	public Long getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Faculty getFaculty() {
		return faculty;
	}
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	@Override
	public String toString() {
		return "ClassSchedule [scheduleId=" + scheduleId + ", course=" + course + ", faculty=" + faculty
				+ ", dayOfWeek=" + dayOfWeek + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
    
}
