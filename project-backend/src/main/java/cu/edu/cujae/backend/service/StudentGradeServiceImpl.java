package cu.edu.cujae.backend.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

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

	@Override
	public StudentGradeDTO getStudentGradesById(Integer studentId) throws SQLException {

		StudentGradeDTO studentGrades = null;
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			conn.setAutoCommit(false);

			// Este método devuelve el resultset ordenado por año de forma ascendente
			CallableStatement CS = conn.prepareCall(
					"{?= call find_student_gradebystudent_id(?)}");

			CS.registerOutParameter(1, Types.OTHER);
			CS.setInt(2, studentId);
			CS.execute();

			ResultSet rs = (ResultSet) CS.getObject(1);

			StudentDTO student = studentService.getStudentById(studentId);
			String fullName = student.getFirstName() + student.getLastName();

			studentGrades = new StudentGradeDTO(studentId, fullName);

			rs.next();

			// "yearPrevIter" El año de la iteración anterior.
			// Primeramente va a tomar el valor del primer
			// año y solamente cambiará su valor cuando el año en el resulset cambie
			Integer yearPrevIter = rs.getInt("year_id");

			// Almacenar las asignaturas con su respectiva nota
			Map<String, Integer> subjectsGrades = new HashMap<>();

			do {
				// Buscar el nombre de la asignatura dado su id
				String subject = subjectService.getSubjectById(rs.getInt("subject_id")).getName();

				subjectsGrades.put(subject, rs.getInt("grade_value"));

				// El año de la iteración actual
				Integer yearNewIter = rs.getInt("year_id");

				// Si los años son distintos entonces ya se han agregado al "subjectsGrades"
				// todas las asignaturas del año de la iter anterior
				if (yearNewIter != yearPrevIter || rs.isLast()) {
					yearPrevIter = rs.getInt("year_id");
					studentGrades.getYearsSubjectsGrades().put(yearPrevIter, subjectsGrades);
					subjectsGrades.clear();
				}

			} while (rs.next());
		}

		return studentGrades;
	}
}
