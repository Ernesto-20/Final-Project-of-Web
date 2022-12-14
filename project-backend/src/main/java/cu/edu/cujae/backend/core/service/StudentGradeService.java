package cu.edu.cujae.backend.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.StudentGradeCourseIdDTO;
import cu.edu.cujae.backend.core.dto.StudentGradeDTO;
import cu.edu.cujae.backend.core.dto.StudentGradeOnlyIdDTO;

public interface StudentGradeService {

	List<StudentGradeOnlyIdDTO> getStudentGradesByCourseId(Integer courseId) throws SQLException;
	List<StudentGradeDTO> getStudentGradesByYearId(Integer studentId, Integer yearId) throws SQLException;
	List<StudentGradeDTO> getStudentGrades() throws SQLException;
	List<StudentGradeCourseIdDTO> getStudentGradesCourseId() throws SQLException;

	void insert(StudentGradeOnlyIdDTO studentGradeOnlyIdDTO)throws SQLException;

	void updateGrade(StudentGradeOnlyIdDTO studentGradeOnlyIdDTO) throws SQLException;

	List<StudentGradeOnlyIdDTO> getStudentGradesOnlyId() throws SQLException;
}
