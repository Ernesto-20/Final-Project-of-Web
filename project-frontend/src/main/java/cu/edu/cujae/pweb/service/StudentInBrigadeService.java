package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.StudentDTO;
import cu.edu.cujae.pweb.dto.StudentInBrigadeDTO;

import java.util.List;

public interface StudentInBrigadeService {
	void createStudentInBrigade(StudentInBrigadeDTO studentInBrigade);
	List<Integer> getStudentsIdsByCourseAndYearId(int courseId, int yearId);
}
