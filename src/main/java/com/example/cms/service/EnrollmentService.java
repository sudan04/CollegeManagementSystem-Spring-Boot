package com.example.cms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cms.entity.Course;
import com.example.cms.entity.Enrollment;
import com.example.cms.entity.Student;
import com.example.cms.entity.Users;
import com.example.cms.repository.CourseRepository;
import com.example.cms.repository.EnrollmentRepository;
import com.example.cms.repository.StudentRepository;
import com.example.cms.repository.UserRepository;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;
    
    @Autowired
    private UserRepository userRepository;

    // Add new enrollment
    public Enrollment addEnrollment(Long studentId, Long courseId) throws Exception {
        Student student = studentRepository.findByStudentId(studentId);
        if (student == null) {
            throw new Exception("Student not found!");
        }
        Course course = courseRepository.findByCourseId(courseId);
        if (course == null) {
            throw new Exception("Course not found!");
        }
        
        Enrollment courseAndStudentEnrollment= enrollmentRepository.findByCourseAndStudent(course, student);
	        if(courseAndStudentEnrollment == null) {
	        Enrollment enrollment = new Enrollment();
	        enrollment.setStudent(student);
	        enrollment.setCourse(course);
	        return enrollmentRepository.save(enrollment);
        }

        throw new Exception("Enrollment already exist by this "+ course + " and "+ student);
    }

    // Delete enrollment by id
    public void deleteEnrollmentById(Long enrollmentId) throws Exception {
        Enrollment enrollment = enrollmentRepository.findByEnrollmentId(enrollmentId);
        if (enrollment == null) {
            throw new Exception("Enrollment not found!");
        }
        enrollmentRepository.delete(enrollment);
    }

    // Find enrollment by id
    public Enrollment findEnrollmentById(Long enrollmentId) throws Exception {
        Enrollment enrollment = enrollmentRepository.findByEnrollmentId(enrollmentId);
        if (enrollment == null) {
            throw new Exception("Enrollment not found!");
        }
        return enrollment;
    }

    // Fetch all enrollments
    public List<Enrollment> fetchAllEnrollments() throws Exception {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        if (enrollments == null || enrollments.isEmpty()) {
            throw new Exception("No enrollments found!");
        }
        return enrollments;
    }
    
    

    // Fetch enrollments by student ID
    public List<Enrollment> getEnrollmentsByStudent(Long studentId) throws Exception {
        Student student = studentRepository.findByStudentId(studentId);
        List<Enrollment> enrollments = enrollmentRepository.findByStudent(student);
        if (enrollments.isEmpty()) {
            throw new Exception("No enrollments found for the given student!");
        }
        return enrollments;
    }

    
    // Fetch enrollments by course ID
    public List<Enrollment> getEnrollmentsByCourse(Long courseId) throws Exception {
        Course course = courseRepository.findByCourseId(courseId);
        List<Enrollment> enrollments = enrollmentRepository.findByCourse(course);
        if (enrollments.isEmpty()) {
            throw new Exception("No enrollments found for the given course!");
        }
        return enrollments;
    }

    // find students enrolled in particular course
	public List<Student> findStudentByCourseId(Long courseId) throws Exception{
		Course course= courseRepository.findByCourseId(courseId);
//		List<Object[]> students=  enrollmentRepository.findStudentsByCourseId(courseId);
//		
//		List<Student> studentList= new ArrayList<>();
//		for(Object[] row: students) {
//			Student st= new Student();
//			st.setStudentId((Long) row[0]);
//			Users user= userRepository.findByUserId((Long) row[1]);
//			st.setUser(user);
//			studentList.add(st);
//		}
		List<Student> studentList=  enrollmentRepository.findStudentsByCourseId(courseId);
		
		if(studentList == null) throw new Exception("No students found on: "+ course.getCourseName());
		return studentList;
	}
}
