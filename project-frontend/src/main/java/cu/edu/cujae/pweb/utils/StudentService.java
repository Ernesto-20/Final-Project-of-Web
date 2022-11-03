package cu.edu.cujae.pweb.utils;

import java.util.List;

import cu.edu.cujae.backend.core.dto.StudentDTO;

public interface StudentService {
	List<StudentDTO> getStudents();
	StudentDTO getStudentById(String studentId);
	void createStudent(StudentDTO student);
	void updateStudent(StudentDTO student);
	void deleteStudent(String studentId);
}
