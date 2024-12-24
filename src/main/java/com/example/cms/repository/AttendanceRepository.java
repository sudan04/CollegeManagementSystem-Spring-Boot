package com.example.cms.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.cms.entity.Attendance;
import com.example.cms.entity.Course;
import com.example.cms.entity.Student;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByCourse(Course course);

    List<Attendance> findByStudentAndCourse(Student student, Course course);

   
    List<Attendance> findByStudentAndCourseAndDate(Student student, Course course, LocalDate date);

	List<Attendance> findByCourseAndDate(Course course, LocalDate now);
	
	@Query(
		    value = "SELECT * FROM attendance a WHERE "
		          + "(?1 IS NULL OR a.student_id = ?1) "
		          + "AND (?2 IS NULL OR a.course_id = ?2) "
		          + "AND (?3 IS NULL OR a.date >= ?3) "
		          + "AND (?4 IS NULL OR a.date <= ?4)", 
		    nativeQuery = true
		)
		List<Attendance> filterAttendance(
		    Long studentId,
		    Long courseId,
		    java.sql.Date startDate,
		    java.sql.Date endDate
		);
}
