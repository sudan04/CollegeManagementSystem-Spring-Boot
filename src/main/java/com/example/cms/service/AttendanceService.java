package com.example.cms.service;

import com.example.cms.entity.Attendance;
import com.example.cms.entity.Course;
import com.example.cms.entity.Faculty;
import com.example.cms.entity.Student;
import com.example.cms.repository.AttendanceRepository;
import com.example.cms.repository.CourseRepository;
import com.example.cms.repository.FacultyRepository;
import com.example.cms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    // Mark attendance
    public Attendance markAttendance(Long studentId, Long courseId, Boolean isPresent) throws Exception {
        Student student = studentRepository.findByStudentId(studentId);
               
        Course course = courseRepository.findByCourseId(courseId);

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setCourse(course);
        attendance.setIsPresent(isPresent);

        return attendanceRepository.save(attendance);
    }

    // Get all attendance records for a specific course
    public List<Attendance> getAttendanceByCourse(Long courseId) throws Exception {
        Course course = courseRepository.findByCourseId(courseId);
        return attendanceRepository.findByCourse(course);
    }

    
    // Get all attendance records for a specific student in a specific course
    public List<Attendance> getAttendanceByStudentAndCourse(Long studentId, Long courseId) throws Exception {
        Student student = studentRepository.findByStudentId(studentId);
        Course course = courseRepository.findByCourseId(courseId);
                
        return attendanceRepository.findByStudentAndCourse(student, course);
    }
    


    
    // Get attendance for a specific student in a course on a specific date
    public List<Attendance> getAttendanceByStudentCourseAndDate(Long studentId, Long courseId, LocalDate date) throws Exception {
        Student student = studentRepository.findByStudentId(studentId);
        Course course = courseRepository.findByCourseId(courseId);
        
        return attendanceRepository.findByStudentAndCourseAndDate(student, course, date);
    }
}
