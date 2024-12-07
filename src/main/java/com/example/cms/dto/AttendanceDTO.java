package com.example.cms.dto;

import java.util.HashMap;
import java.util.Map;

public class AttendanceDTO {
	private Long courseId;
	private Map<Long, String> attendance = new HashMap<>();
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public Map<Long, String> getAttendance() {
		return attendance;
	}
	public void setAttendance(Map<Long, String> attendance) {
		this.attendance = attendance;
	}
	
	
}
