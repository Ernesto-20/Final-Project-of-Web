package cu.edu.cujae.backend.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.StudentGradeDTO;
import cu.edu.cujae.backend.core.dto.StudentGradeOnlyIdDTO;

public interface StudentGradeService {
	List<StudentGradeDTO> getStudentGradesByYearId(Integer studentId, Integer yearId) throws SQLException;

	void insert(StudentGradeOnlyIdDTO studentGradeOnlyIdDTO)throws SQLException;

	void updateGrade(StudentGradeOnlyIdDTO studentGradeOnlyIdDTO) throws SQLException;
}
