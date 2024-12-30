package com.example.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.example.cms.entity.Student;
import com.example.cms.entity.Users;
import com.example.cms.repository.StudentRepository;
import com.example.cms.securityConfig.MyUserDetails;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // Add a new student
    public Student addStudent(Users newUser) throws Exception {
        if (newUser == null) {
            throw new Exception("Cannot create student!");
        }
        Student newStudent = new Student();
        newStudent.setUser(newUser);

        return studentRepository.save(newStudent);
    }

    // Delete student by id
    public boolean deleteByStudentId(Long studentId) throws Exception {
        Student student = studentRepository.findByStudentId(studentId);
        if (student == null) {
            throw new Exception("Student not found!");
        }
        studentRepository.delete(student);
        return true;
    }

    // Assign program to student
    public Student assignProgramToStudent(Long studentId, String program) throws Exception {
        Student student = studentRepository.findByStudentId(studentId);
        if (student == null) {
            throw new Exception("Student not found!");
        }
        
        Student updatedStudent = studentRepository.save(student);

        if (updatedStudent == null) {
            throw new Exception("Cannot assign program to student!");
        }
        return updatedStudent;
    }

    // Find student by id
    public Student findStudentById(Long studentId) throws Exception {
        Student student = studentRepository.findByStudentId(studentId);
        if (student == null) {
            throw new Exception("Cannot find student!");
        }
        return student;
    }

    // Fetch all students
    public List<Student> fetchAllStudents() throws Exception {
        List<Student> students = studentRepository.findAll();
        if (students == null || students.isEmpty()) {
            throw new Exception("No students exist!");
        }
        return students;
    }
//
//	public Student findStudentDetailByUserId(MyUserDetails user) {
//		
//	return	studentRepository.findByUser(user.getUser());
//	}

	public Long getStudentCount() {
		return  studentRepository.count();
	}
}
