package com.example.cms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.cms.entity.Course;
import com.example.cms.entity.Enrollment;
import com.example.cms.entity.Student;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{
	    @Query(value = """
	        SELECT c.course_id, c.course_name, COUNT(e.student_id) AS enrollment_count
	        FROM enrollment e
	        JOIN course c ON e.course_id = c.course_id
	        GROUP BY c.course_id
	        ORDER BY enrollment_count DESC
	        LIMIT :topN
	        """, nativeQuery = true)
	    List<Object[]> getMostPopularCourses(int topN);

		Enrollment findByEnrollmentId(Long enrollmentId);

		List<Enrollment> findByStudent(Student student);

		List<Enrollment> findByCourse(Course course);
}
