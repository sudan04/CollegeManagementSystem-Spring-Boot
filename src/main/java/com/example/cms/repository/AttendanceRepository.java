package com.example.cms.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.Attendance;
import com.example.cms.entity.Course;
import com.example.cms.entity.Student;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	List<Attendance> findByDateBetweenAndCourse(Date startDate, Date endDate, Course course);

	List<Attendance> findByDateBetweenAndStudent(Date startDate, Date endDate, Student student);

	List<Attendance> findByDateBetween(Date startDate, Date endDate);

	List<Attendance> findByCourse(Course course);

	List<Attendance> findByStudent(Student student);

	List<Attendance> findByCourseAndStudent(Course course, Student student);

	List<Attendance> findByCourseAndDate(Course course, LocalDate now);

	List<Attendance> findByDateBetweenAndCourseAndStudent(Date startDate, Date endDate, Course course, Student student);

   
	
	
}
