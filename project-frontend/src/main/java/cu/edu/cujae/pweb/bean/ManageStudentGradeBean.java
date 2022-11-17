//package cu.edu.cujae.pweb.bean;
//
//import javax.annotation.PostConstruct;
//import javax.faces.bean.ManagedBean;
//import javax.faces.view.ViewScoped;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import cu.edu.cujae.pweb.dto.StudentGradeDTO;
//import cu.edu.cujae.pweb.service.StudentGradeService;
//
//@Component // Le indica a spring es un componente registrado
//@ManagedBean
//@ViewScoped // Este es el alcance utilizado para trabajar con Ajax
//public class ManageStudentGradeBean {
//
//	private StudentGradeDTO studentGrades;
//	@Autowired
//	private ManageStudentBean studentBean;
//	/*
//	 * @Autowired es la manera para inyectar una dependencia/clase anotada
//	 * con @service en spring
//	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
//	 */
//	@Autowired
//	private StudentGradeService studentGradeService;
//
//	public ManageStudentGradeBean() {
//
//	}
//
//	// Esta anotación permite que se ejecute code luego de haberse ejecuta el
//	// constructor de la clase.
//	// ? CÓMO PASAR EL PARAMETRO AL POSTCONSTRUCT
//	@PostConstruct
//	public void init() {
//
//		int a;
//		int s;
//		int d;
//		int f;
//		// studentGrades = studentGrades == null
//		// ?
//		// studentGradeService.getStudentGradesById(studentBean.getSelectedStudent().getId())
//		// : studentGrades;
//
//	}
//
//	public void setStudentGrades(Integer studentId) {
//		this.studentGrades = studentGradeService.getStudentGradesById(studentId);
//	}
//
//	public StudentGradeDTO getStudentGrade() {
//		return studentGrades;
//	}
//
//	public void setStudentGrades(StudentGradeDTO studentGrades) {
//		this.studentGrades = studentGrades;
//	}
//}