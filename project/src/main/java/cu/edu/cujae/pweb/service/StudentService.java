package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.StudentDTO;

public interface StudentService {
	List<StudentDTO> getStudents();
	StudentDTO getStudentById(String studentId);
	void createStudent(StudentDTO student);
	void updateStudent(StudentDTO student);
	void deleteStudent(String id);
}
