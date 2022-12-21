package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.StudentGradeDTO;
import cu.edu.cujae.pweb.dto.StudentGradeOnlyIdDTO;

public interface StudentGradeService {

	List<StudentGradeOnlyIdDTO> getStudentGradesByCourseId(Integer courseId);
	List<StudentGradeDTO> getStudentGradesByYearId(Integer studentId, Integer yearId);
	List<StudentGradeDTO> getStudentGradesAll();
	void updateStudentGrade (StudentGradeOnlyIdDTO studentGrade);
}
