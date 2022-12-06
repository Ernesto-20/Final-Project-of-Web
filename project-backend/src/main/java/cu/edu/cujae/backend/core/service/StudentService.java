package cu.edu.cujae.backend.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.StudentDTO;

public interface StudentService {
	List<StudentDTO> getStudents() throws SQLException;
	List<StudentDTO> getStudentsByBrigadeCourseYearIds(Integer brigadeId, Integer courseId, Integer yearId)
			throws SQLException;
	StudentDTO getStudentById(Integer studentId) throws SQLException;
	StudentDTO getStudentByIdNum(String studentIdNum) throws SQLException;
	void createStudent(StudentDTO student) throws SQLException;
	void updateStudent(StudentDTO student) throws SQLException;
	void deleteStudent(Integer studentId) throws SQLException;
}
