package cu.edu.cujae.backend.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.StudentGradeDTO;

public interface StudentGradeService {

	List<StudentGradeDTO> getStudentGradesByYearId(Integer studentId, Integer yearId) throws SQLException;
}
