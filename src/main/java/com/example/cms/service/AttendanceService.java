package com.example.cms.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cms.dto.AttendanceDTO;
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

    // save attendance
	public void saveAttendance(AttendanceDTO attendanceDTO) throws Exception {
		Course course = courseRepository.findByCourseId(attendanceDTO.getCourseId());
		Map<Long, String> attendance= attendanceDTO.getAttendance();
		
		
		List<Attendance> atdList= attendanceRepository.findByCourseAndDate(course, LocalDate.now());
		
		if(atdList != null && !atdList.isEmpty()) {
			throw new Exception("Attendance is already marked for Course "+ course.getCourseName()+" for today");
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
}
