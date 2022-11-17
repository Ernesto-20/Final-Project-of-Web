//package cu.edu.cujae.backend.service;
//
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Types;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//
//import cu.edu.cujae.backend.core.dto.StudentDTO;
//import cu.edu.cujae.backend.core.dto.StudentGradeDTO;
//import cu.edu.cujae.backend.core.service.StudentGradeService;
//import cu.edu.cujae.backend.core.service.StudentService;
//import cu.edu.cujae.backend.core.service.SubjectService;
//
//@Service
//public class StudentGradeServiceImpl implements StudentGradeService {
//
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//
//	@Autowired
//	private StudentService studentService;
//	@Autowired
//	private SubjectService subjectService;
//	
//	private ResultSet rs;
//	
//	@Override
//	public StudentGradeDTO getStudentGradesById(Integer studentId) throws SQLException {
//
//		StudentGradeDTO studentGrades = null;
//		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
//			conn.setAutoCommit(false);
//
//			// Este método devuelve el resultset ordenado por año de forma ascendente
//			rs = executeQuery(conn, "{?= call find_student_gradebystudent_id(?)}", studentId);
//			studentGrades = getStudentGrades(studentId);
//			
//		}
//
//		return studentGrades;
//	}
//
//	private StudentGradeDTO getStudentGrades(Integer studentId) throws SQLException {
//		StudentGradeDTO studentGrades;
//		studentGrades = new StudentGradeDTO(studentId, studentFullName(studentId));
//
//		// "yearPrevIter" El año de la iteración anterior.
//		// Primeramente va a tomar el valor del primer
//		// año y solamente cambiará su valor cuando el año en el resulset cambie
//		Integer yearPrevIter = getYear();
//
//		// Almacenar las asignaturas con su respectiva nota
//		Map<String, Integer> subjectsGrades = new HashMap<>();
//		rs.next();
//		do {
//
//			subjectsGrades.put(getSubject(), getGradeValue());
//
//			// Si los años son distintos entonces ya se han agregado al "subjectsGrades"
//			// todas las asignaturas del año de la iter anterior
//			if (getYear() != yearPrevIter) {
//				studentGrades.addYearAndSubjectsGrades(yearPrevIter, subjectsGrades);
//				yearPrevIter = getYear();
//				subjectsGrades.clear();
//			}
//
//		} while (rs.next());
//		return studentGrades;
//	}
//
//	private int getGradeValue() throws SQLException {
//		return rs.getInt("grade_value");
//	}
//
//	private ResultSet executeQuery(Connection conn, String query, Integer studentId) throws SQLException {
//		CallableStatement CS = conn.prepareCall(query);
//
//		CS.registerOutParameter(1, Types.OTHER);
//		CS.setInt(2, studentId);
//		CS.execute();
//		return (ResultSet) CS.getObject(1);
//	}
//
//	private String getSubject() throws SQLException {
//		return subjectService.getSubjectById(rs.getInt("subject_id")).getName();
//	}
//
//	private String studentFullName(Integer studentId) throws SQLException {
//		StudentDTO student = studentService.getStudentById(studentId);
//		return student.getFirstName() + student.getLastName();
//	}
//	
//	private Integer getYear() throws SQLException {
//		return rs.getInt("year_id");
//	}
//
//}
