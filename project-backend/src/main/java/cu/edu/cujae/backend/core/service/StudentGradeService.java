package cu.edu.cujae.backend.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.StudentGradeDTO;
import cu.edu.cujae.backend.core.dto.StudentGradeOnlyIdDTO;
import cu.edu.cujae.backend.core.dto.SubjectDTO;

public interface StudentGradeService {

	List<StudentGradeOnlyIdDTO> getStudentGradesByCourseId(Integer courseId) throws SQLException;
	List<StudentGradeDTO> getStudentGradesByYearId(Integer studentId, Integer yearId) throws SQLException;
}
