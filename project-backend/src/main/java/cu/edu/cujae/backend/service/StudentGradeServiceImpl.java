package cu.edu.cujae.backend.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import cu.edu.cujae.backend.core.dto.StudentGradeOnlyIdDTO;
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
	public List<StudentGradeOnlyIdDTO> getStudentGradesByCourseId(Integer courseId) throws SQLException {
		LinkedList<StudentGradeOnlyIdDTO> studentGrades = new LinkedList<>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			conn.setAutoCommit(false);
			String function = "{?= call find_student_gradebycourse_id(?)}";

			CallableStatement preparedFunction = conn.prepareCall(function);
			preparedFunction.setInt(2, courseId);
			preparedFunction.registerOutParameter(1, Types.OTHER);
			preparedFunction.execute();
			ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
			while (resultSet.next()) {
				Integer yearId = resultSet.getInt("year_id");
				Integer studentId = resultSet.getInt("student_id");
				Integer subjectId = resultSet.getInt("subject_id");
				Integer gradeValue = resultSet.getInt("grade_value");
				studentGrades.add(new StudentGradeOnlyIdDTO(yearId, studentId, subjectId, courseId, gradeValue));
			}
			resultSet.close();
			preparedFunction.close();
		}
		return studentGrades;
	}

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

	@Override
	public void insert(StudentGradeOnlyIdDTO studentGradeOnlyIdDTO) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			String function = "{ call student_grade_insert(?, ?, ?, ?, ?)}";
			CallableStatement preparedFunction = null;
			preparedFunction = conn.prepareCall(function);
			preparedFunction.setInt(1, studentGradeOnlyIdDTO.getStudentId());
			preparedFunction.setInt(2, studentGradeOnlyIdDTO.getGradeValue());
			preparedFunction.setInt(3, studentGradeOnlyIdDTO.getSubjectId());
			preparedFunction.setInt(4, studentGradeOnlyIdDTO.getCourseId());
			preparedFunction.setInt(5, studentGradeOnlyIdDTO.getYearId());
			preparedFunction.execute();

			preparedFunction.close();
		}
	}

	@Override
	public void updateGrade(StudentGradeOnlyIdDTO studentGradeOnlyIdDTO) throws SQLException {

		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			//Update hours_long in a row located by subjectId, courseId and yearId
			String function = "{ call student_grade_update_grade(?, ?, ?, ?, ?)}";
			CallableStatement preparedFunction = null;
			//
			preparedFunction = conn.prepareCall(function);
			preparedFunction.setInt(1, studentGradeOnlyIdDTO.getStudentId());
			preparedFunction.setInt(2, studentGradeOnlyIdDTO.getGradeValue());
			preparedFunction.setInt(3, studentGradeOnlyIdDTO.getSubjectId());
			preparedFunction.setInt(4, studentGradeOnlyIdDTO.getCourseId());
			preparedFunction.setInt(5, studentGradeOnlyIdDTO.getYearId());
			preparedFunction.execute();

			preparedFunction.close();
		}

	}

	@Override
	public List<StudentGradeDTO> getStudentGrades() throws SQLException {

		List<StudentGradeDTO> studentGrades = new ArrayList<>();
		try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
			conn.setAutoCommit(false);

			CallableStatement CS = conn.prepareCall(
					"{?= call select_all_student_grade()}");

			CS.registerOutParameter(1, java.sql.Types.OTHER);
			CS.execute();
			ResultSet rs = (ResultSet) CS.getObject(1);

			while (rs.next()) {
				studentGrades.add(new StudentGradeDTO(
						rs.getInt("year_id"),
						rs.getInt("student_id"),
						studentService.getStudentById(rs.getInt("student_id")).getFullName(),
						rs.getInt("subject_id"),
						subjectService.getSubjectById(rs.getInt("subject_id")).getName(),
						rs.getInt("grade_value")
				));
			}
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

	@Override
	public List<StudentGradeOnlyIdDTO> getStudentGradesOnlyId() throws SQLException {

		List<StudentGradeOnlyIdDTO> studentGrades = new ArrayList<>();
		try(Connection conn = jdbcTemplate.getDataSource().getConnection()){
			conn.setAutoCommit(false);

			CallableStatement CS = conn.prepareCall(
					"{?= call select_all_student_grade()}");

			CS.registerOutParameter(1, java.sql.Types.OTHER);
			CS.execute();
			ResultSet rs = (ResultSet) CS.getObject(1);

			while (rs.next()) {
				studentGrades.add(new StudentGradeOnlyIdDTO(
						rs.getInt("year_id"),
						rs.getInt("student_id"),
						rs.getInt("subject_id"),
						rs.getInt("course_id"),
						rs.getInt("grade_value")
				));
			}
		}

		return studentGrades;
	}
}
