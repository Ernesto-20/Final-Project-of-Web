package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.StudentGradeDTO;

public interface StudentGradeService {

	List<StudentGradeDTO> getStudentGradesByYearId(Integer studentId, Integer yearId);
}
