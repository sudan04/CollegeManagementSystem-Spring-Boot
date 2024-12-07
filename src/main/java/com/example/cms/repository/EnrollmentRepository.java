package com.example.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.cms.entity.Course;
import com.example.cms.entity.Enrollment;
import com.example.cms.entity.Student;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{

	    @Query(value = """
	        SELECT c.course_name, COUNT(e.student_id) AS enrollment_count
	        FROM enrollment e
	        JOIN course c ON e.course_id = c.course_id
	        GROUP BY c.course_id
	        ORDER BY enrollment_count DESC
	        LIMIT :topN
	        """, nativeQuery = true)
	    List<Object[]> getMostPopularCourses(@Param("topN") int topN);
	



		Enrollment findByEnrollmentId(Long enrollmentId);

		List<Enrollment> findByStudent(Student student);

		List<Enrollment> findByCourse(Course course);

//		@Query(value = "SELECT s.* FROM student s " +
//	               "JOIN enrollment e ON s.student_id = e.student_id " +
//	               "WHERE e.course_id = :courseId", nativeQuery = true)
//		List<Object[]> findStudentsByCourseId(@Param("courseId") Long courseId);

		@Query("SELECT e.student FROM Enrollment e WHERE e.course.courseId = :courseId")
		List<Student> findStudentsByCourseId(@Param("courseId") Long courseId);

		
		Enrollment findByCourseAndStudent(Course course, Student student);
}
