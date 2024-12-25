package com.example.cms.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cms.dto.AttendanceDTO;
import com.example.cms.dto.AttendanceFilterDTO;
import com.example.cms.entity.Attendance;
import com.example.cms.entity.Course;
import com.example.cms.entity.Student;
import com.example.cms.repository.AttendanceRepository;
import com.example.cms.repository.CourseRepository;
import com.example.cms.repository.FacultyRepository;
import com.example.cms.repository.StudentRepository;

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

   

//    // Get all attendance records for a specific course
//    public List<Attendance> getAttendanceByCourse(Long courseId) throws Exception {
//        Course course = courseRepository.findByCourseId(courseId);
//        return attendanceRepository.findByCourse(course);
//    }

//    
//    // Get all attendance records for a specific student in a specific course
//    public List<Attendance> getAttendanceByStudentAndCourse(Long studentId, Long courseId) throws Exception {
//        Student student = studentRepository.findByStudentId(studentId);
//        Course course = courseRepository.findByCourseId(courseId);
//                
//        return attendanceRepository.findByStudentAndCourse(student, course);
//    }
    


    
//    // Get attendance for a specific student in a course on a specific date
//    public List<Attendance> getAttendanceByStudentCourseAndDate(Long studentId, Long courseId, LocalDate date) throws Exception {
//        Student student = studentRepository.findByStudentId(studentId);
//        Course course = courseRepository.findByCourseId(courseId);
//        
//        return attendanceRepository.findByStudentAndCourseAndDate(student, course, date);
//    }

    // save attendance
	public void saveAttendance(AttendanceDTO attendanceDTO) throws Exception {
		Course course = courseRepository.findByCourseId(attendanceDTO.getCourseId());
		Map<Long, String> attendance= attendanceDTO.getAttendance();
		
		
		List<Attendance> atdList= attendanceRepository.findByCourseAndDate(course, LocalDate.now());
		
		if(atdList != null && !atdList.isEmpty()) {
			throw new Exception("Today's attendance is already marked for Course "+ course.getCourseName());
		}
		
		for(Map.Entry<Long, String> entry : attendance.entrySet()) {
			System.out.println(entry);
			Student student= studentRepository.findByStudentId(entry.getKey());
			Boolean status= "P".equals(entry.getValue())? true: false;
			
			Attendance atd= new Attendance();
			atd.setCourse(course);
			atd.setStudent(student);
			atd.setIsPresent(status);
			
			attendanceRepository.save(atd);
		}
	}
	
	
	// filter attendance records 
	public List<Attendance> filterAttendanceRecords(AttendanceFilterDTO filterDto) throws Exception {
	  
		Long studentId= filterDto.getStudentId();
		Long courseId= filterDto.getCourseId();
		
		Course course= courseRepository.findByCourseId(courseId);
		Student student= studentRepository.findByStudentId(studentId);
		
		Date startDate= (filterDto.getStartDate() != null)? java.sql.Date.valueOf(filterDto.getStartDate()): null;
		Date endDate= (filterDto.getEndDate() != null)? java.sql.Date.valueOf(filterDto.getEndDate()): null;
		
		System.out.println(startDate);
		System.out.println(endDate);
		
		
		if (startDate != null && endDate != null && courseId != null && studentId != null) {
            return attendanceRepository.findByDateBetweenAndCourseAndStudent(startDate, endDate, course, student);
        } 
		else if (startDate != null && endDate != null && courseId != null) {
            return attendanceRepository.findByDateBetweenAndCourse(startDate, endDate, course);
        } 
		else if (startDate != null && endDate != null && studentId != null) {
            return attendanceRepository.findByDateBetweenAndCourse(startDate, endDate, course);
        } 
		else if (startDate != null && endDate != null) {
            return attendanceRepository.findByDateBetween(startDate, endDate);
        } 
		else if(courseId != null && studentId !=null) {
			return attendanceRepository.findByCourseAndStudent(course, student);
		}
		else if (courseId != null) {
            return attendanceRepository.findByCourse(course);
        } 
		else if (studentId != null) {
            return attendanceRepository.findByStudent(student);
        } 
		else {
            return attendanceRepository.findAll();
        }
	}
}
