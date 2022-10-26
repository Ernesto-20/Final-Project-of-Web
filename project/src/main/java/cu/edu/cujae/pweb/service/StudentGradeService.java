package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.StudentGradeDTO;

public interface StudentGradeService {
	List<StudentGradeDTO> getStudentGrades();
	StudentGradeDTO getStudentById(String studentId);
	void createStudent(StudentGradeDTO student);
	void updateStudent(StudentGradeDTO student);
	void deleteStudent(String id);
}
