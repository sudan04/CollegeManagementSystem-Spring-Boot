package com.example.cms.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cms.entity.Attendance;
import com.example.cms.entity.Course;
import com.example.cms.entity.Student;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // Find all attendance records for a specific course
    List<Attendance> findByCourse(Course course);

    // Find all attendance records for a specific student in a specific course
    List<Attendance> findByStudentAndCourse(Student student, Course course);

    // Find attendance by student, course, and date
    List<Attendance> findByStudentAndCourseAndDate(Student student, Course course, LocalDate date);
}
