package cu.edu.cujae.pweb.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.StudentGradeDTO;
import cu.edu.cujae.pweb.service.StudentGradeService;

@Component // Le indica a spring es un componente registrado
@ManagedBean
@ViewScoped // Este es el alcance utilizado para trabajar con Ajax
public class ManageStudentGradeBean {

	private List<StudentGradeDTO> studentGrades;

	@Autowired
	private ManageStudentBean studentBean;
	/*
	 * @Autowired es la manera para inyectar una dependencia/clase anotada
	 * con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private StudentGradeService studentGradeService;

	public ManageStudentGradeBean() {

	}

	// public void setStudentGrades(Integer studentId, Integer yearId) {
	// this.studentGrades = studentGradeService.getStudentGradesByYearId(studentId,
	// yearId);
	// }

	public List<StudentGradeDTO> getStudentGrade() {

		studentGrades = studentGradeService.getStudentGradesByYearId(studentBean.getSelectedStudent().getId(),
				studentBean.getYear());
		return studentGrades;
	}

	public void setStudentGrades(List<StudentGradeDTO> studentGrades) {
		this.studentGrades = studentGrades;
	}
}