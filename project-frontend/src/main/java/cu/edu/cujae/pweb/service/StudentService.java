package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.StudentDTO;

public interface StudentService {
	List<StudentDTO> getStudents();
	List<StudentDTO> getStudentsByBrigadeCourseYearIds(Integer brigadeId, Integer courseId, Integer yearId);
	StudentDTO getStudentById(Integer studentId);
	StudentDTO getStudentByIdNum(String studentIdNum);
	void createStudent(StudentDTO student);
	void updateStudent(StudentDTO student);
	void deleteStudent(Integer studentId);
}
