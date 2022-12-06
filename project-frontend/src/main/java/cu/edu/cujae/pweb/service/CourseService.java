package cu.edu.cujae.pweb.service;

import java.util.List;
import java.util.Map;

import cu.edu.cujae.pweb.dto.CourseDTO;

public interface CourseService {
	List<CourseDTO> getCourses();
	CourseDTO getCourseById(Integer courseId);
	void createCourse(CourseDTO course);
	void updateCourse(CourseDTO course);
	void deleteCourse(Integer courseId);
}
