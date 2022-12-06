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
import cu.edu.cujae.backend.core.service.StatusService;
import cu.edu.cujae.backend.core.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private StatusService statusService;
	
	
	@Override
	public void createStudent(StudentDTO student) throws SQLException {

		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			CallableStatement CS = conn.prepareCall(
					"{call student_insert(?, ?, ?, ?, ?, ?)}");

			CS.setString(1, student.getIdNum());
			CS.setString(2, student.getFirstName());
			CS.setString(3, student.getLastName());
			CS.setString(4, student.getGender());
			CS.setString(5, student.getMunicipality());
			int defaultState = 1;
			CS.setInt(6, defaultState);
			CS.executeUpdate();
		}

	}

	
	
	@Override
	public List<StudentDTO> getStudents() throws SQLException {
		List<StudentDTO> studentList = new ArrayList<StudentDTO>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			conn.setAutoCommit(false);

			CallableStatement CS = conn.prepareCall(
					"{?= call select_all_student()}");

			CS.registerOutParameter(1, java.sql.Types.OTHER);
			CS.execute();
			ResultSet rs = (ResultSet) CS.getObject(1);

			while (rs.next()) {
				studentList.add(new StudentDTO(rs.getInt("id"), rs.getString("id_num"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getString("gender"), rs.getString("municipality"),
						rs.getInt("status_id"),
						statusService.findById(rs.getInt("status_id")).getDescription()));
			}
		}
		return studentList;
	}

	
	@Override
	public StudentDTO getStudentById(Integer studentId) throws SQLException {

		StudentDTO student = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			conn.setAutoCommit(false);

			CallableStatement CS = conn.prepareCall(
					"{?= call find_studentbyid(?)}");

			CS.registerOutParameter(1, Types.OTHER);
			CS.setInt(2, studentId);
			CS.execute();

			ResultSet rs = (ResultSet) CS.getObject(1);

			while (rs.next()) {
				student = new StudentDTO(studentId, rs.getString("id_num"), rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("gender"), rs.getString("municipality"), rs.getInt("status_id"),
						statusService.findById(rs.getInt("status_id")).getDescription());
			}
		}

		return student;
	}

	@Override
	public StudentDTO getStudentByIdNum(String studentIdNum) throws SQLException {

		StudentDTO student = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			conn.setAutoCommit(false);

			CallableStatement CS = conn.prepareCall("{?= call find_studentbyid_num(?)}");
			CS.registerOutParameter(1, Types.OTHER);
			CS.setString(2, studentIdNum);
			CS.execute();
			ResultSet rs = (ResultSet) CS.getObject(1);
			while (rs.next()) {
				student = new StudentDTO(rs.getInt("id"), studentIdNum, rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("gender"), rs.getString("municipality"), rs.getInt("status_id"),
						statusService.findById(rs.getInt("status_id")).getDescription());
			}
		}
		return student;
	}

	@Override
	public List<StudentDTO> getStudentsByBrigadeCourseYearIds(Integer brigadeId, Integer courseId, Integer yearId) throws SQLException {

		List<StudentDTO> students = new ArrayList<>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			conn.setAutoCommit(false);

			CallableStatement CS = conn.prepareCall("{?= call find_student_by_brigade_course_year_ids(?, ?, ?)}");
			CS.registerOutParameter(1, Types.OTHER);
			CS.setInt(2, brigadeId);
			CS.setInt(3, courseId);
			CS.setInt(4, yearId);
			CS.execute();
			ResultSet rs = (ResultSet) CS.getObject(1);
			while (rs.next()) {
				students.add(new StudentDTO(rs.getInt("id"), rs.getString("id_num"), rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("gender"), rs.getString("municipality"), rs.getInt("status_id"),
						statusService.findById(rs.getInt("status_id")).getDescription()));
			}
		}
		return students;
	}
	
	@Override
	public void updateStudent(StudentDTO student) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {

			CallableStatement CS = conn.prepareCall(
					"{ call student_update(?, ?, ?, ?, ?, ?, ?)}");

			CS.setInt(1, student.getId());
			CS.setString(2, student.getIdNum());
			CS.setString(3, student.getFirstName());
			CS.setString(4, student.getLastName());
			CS.setString(5, student.getGender());
			CS.setString(6, student.getMunicipality());
			CS.setInt(7, student.getStatusID());

			CS.executeUpdate();
		}
	}

	@Override
	public void deleteStudent(Integer studentId) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			CallableStatement CS = conn.prepareCall(
					"{call student_delete(?)}");

			CS.setInt(1, studentId);
			CS.executeUpdate();
		}
	}
}
