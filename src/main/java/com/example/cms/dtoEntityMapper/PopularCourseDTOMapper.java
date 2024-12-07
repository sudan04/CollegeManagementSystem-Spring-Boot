package com.example.cms.dtoEntityMapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.cms.dto.PopularCourseDTO;

@Component
public class PopularCourseDTOMapper {
	public List<PopularCourseDTO> toPopularCourseDTOs(List<Object[]> courses){

		  List<PopularCourseDTO> courseList= new ArrayList<>();
		  
		     for (Object[] row : courses) {
		    	 	Long courseId= (Long) row[0];
		            String courseName = (String) row[1];  
		            Long enrollmentCount = (Long) row[2];  
		           
		            PopularCourseDTO popularCourseDTO = new PopularCourseDTO();
		            popularCourseDTO.setCourseId(courseId);
		            popularCourseDTO.setCourseName(courseName);
		            popularCourseDTO.setEnrollmentCount(enrollmentCount);
		            
		            
		            courseList.add(popularCourseDTO);
		        }
		        
		        return courseList;
	  }
}

