package cu.edu.cujae.backend.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cu.edu.cujae.backend.core.dto.StudentDto;
import cu.edu.cujae.backend.core.service.RoleService;
import cu.edu.cujae.backend.core.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private RoleService roleService;

	@Override
	public void createStudent(StudentDto student) throws SQLException {
		
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			CallableStatement CS = conn.prepareCall(
				"{call student_insert(?, ?, ?, ?, ?, ?, ?)}");
			
			CS.setString(1, studentDTO.getIdNum());
            CS.setString(2, studentDTO.getFirstName());
            CS.setString(3, studentDTO.getLastName());
            CS.setString(4, studentDTO.getGender());
            CS.setString(5, studentDTO.getMunicipality());
            CS.setInt(6, studentDTO.getStatusId());
			CS.executeUpdate();	
		} 
		
		
	}

	@Override
	public List<StudentDto> listStudents() throws SQLException {
		List<StudentDto> studentList = new ArrayList<StudentDto>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			CallableStatement CS = conn.prepareCall(
				"{?= call select_all_student()}");
			
			CS.registerOutParameter(1, java.sql.Types.OTHER);
			CS.execute();
			ResultSet rs = (ResultSet) CS.getObject(1);
			
			while(rs.next()){
				studentList.add(new StudentDto(rs.getInt("id")
					,rs.getString("id_num")
					,rs.getString("first_name")
					,rs.getString("last_name")
					,rs.getString("gender")
					,rs.getString("municipality")
					,rs.getInt("status_id")));
			}
		} 
		return studentList;
	}
	
	@Override
	public StudentDto getStudentById(String studentId) throws SQLException {
		
		StudentDto student = null; 
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			CallableStatement CS = conn.prepareCall(
				"{?= call find_studentbyid()}");
			
			CS.registerOutParameter(1, Types.OTHER);
			CS.setInt(2, id);
			CS.execute();
	
			ResultSet rs = (ResultSet) CS.getObject(1);
			
			while(rs.next()){
				student = new StudentDto(rs.getString("id_num")
					,rs.getString("first_name")
					,rs.getString("last_name")
					,rs.getString("gender")
					,rs.getString("municipality")
					,rs.getInt("status_id"));
			}
		}
		
		return student;
	}
	public StudentDto getStudentByIdNum(String idNum) throws SQLException {
		
		StudentDto student = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
		
			CallableStatement CS = connection.prepareCall("{?= call find_studentbyid_num(?)}");
			CS.registerOutParameter(1, Types.OTHER);
			CS.setString(2, idNum);
			CS.execute();
			ResultSet rs = (ResultSet) CS.getObject(1);
			while(rs.next()) {
				student = new StudentDto(rs.getString("id")
					,rs.getString("first_name")
					,rs.getString("last_name")
					,rs.getString("gender")
					,rs.getString("municipality")
					,rs.getInt("status_id"));
			}
		}
        return student;
    }
	
	@Override
	public void updateStudent(StudentDto Student) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			
			CallableStatement CS = conn.prepareCall(
				"{ call student_update(?, ?, ?, ?, ?, ?, ?)}");
			
			CS.setInt(1, studentDTO.getId());
			CS.setString(2, studentDTO.getIdNum());
			CS.setString(3, studentDTO.getFirstName());
			CS.setString(4, studentDTO.getLastName());
			CS.setString(5, studentDTO.getGender());
			CS.setString(6, studentDTO.getMunicipality());
	
			CS.executeUpdate();
		}
	}

	@Override
	public void deleteStudent(String studentId) throws SQLException {
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			CallableStatement CS = conn.prepareCall(
				"{call student_delete(?)}");
				
			CS.setInt(1, studentId);
			CS.executeUpdate();	
		}
	}
	
	private String getMd5Hash(String password) {
		MessageDigest md;
		String md5Hash = "";
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
		    byte[] digest = md.digest();
		    md5Hash = DatatypeConverter
		      .printHexBinary(digest).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return md5Hash;
	}

}
