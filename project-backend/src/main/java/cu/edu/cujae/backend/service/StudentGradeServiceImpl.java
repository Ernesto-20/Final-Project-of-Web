package cu.edu.cujae.backend.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cu.edu.cujae.backend.core.dto.StudentDTO;
import cu.edu.cujae.backend.core.dto.StudentGradeDTO;
import cu.edu.cujae.backend.core.service.StudentGradeService;
import cu.edu.cujae.backend.core.service.StudentService;
import cu.edu.cujae.backend.core.service.SubjectService;

@Service
public class StudentGradeServiceImpl implements StudentGradeService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private StudentService studentService;
	@Autowired
	private SubjectService subjectService;

	private ResultSet rs;

	@Override
	public List<StudentGradeDTO> getStudentGradesByYearId(Integer studentId, Integer yearId) throws SQLException {

		List<StudentGradeDTO> studentGrades = new ArrayList<>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			conn.setAutoCommit(false);

			// Este método devuelve el resultset ordenado por año de forma ascendente
			rs = executeQuery(conn, "{?= call find_student_gradeby_studentid_and_yearid(?, ?)}", studentId, yearId);
			studentGrades = getStudentGrades(studentId, yearId);

		}

		return studentGrades;
	}

	private List<StudentGradeDTO> getStudentGrades(Integer studentId, Integer yearId) throws SQLException {
		List<StudentGradeDTO> studentGrades = new ArrayList<>();

		StudentGradeDTO studentGrade = null;

		while (rs.next()) {
			studentGrade = new StudentGradeDTO(yearId, studentId, getStudentFullName(studentId),
					rs.getInt("subject_id"), getSubject(), getGradeValue());
			studentGrades.add(studentGrade);

		}

		return studentGrades;
	}

	// Execute the query and return the resultSet
	private ResultSet executeQuery(Connection conn, String query, Integer studentId, Integer yearId)
			throws SQLException {
		CallableStatement CS = conn.prepareCall(query);

		CS.registerOutParameter(1, Types.OTHER);
		CS.setInt(2, studentId);
		CS.setInt(3, yearId);
		CS.execute();
		return (ResultSet) CS.getObject(1);
	}

	// Get the student's full name
	private String getStudentFullName(Integer studentId) throws SQLException {
		StudentDTO student = studentService.getStudentById(studentId);
		return student.getFirstName() + student.getLastName();
	}

	// Get the subject name of the current row in the resultSet
	private String getSubject() throws SQLException {
		return subjectService.getSubjectById(rs.getInt("subject_id")).getName();
	}

	// Get the grade value of current row in the resultSet
	private int getGradeValue() throws SQLException {
		return rs.getInt("grade_value");
	}
}
