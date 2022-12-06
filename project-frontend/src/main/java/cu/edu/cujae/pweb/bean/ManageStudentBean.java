package cu.edu.cujae.pweb.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.StudentDTO;
import cu.edu.cujae.pweb.service.StudentService;
import cu.edu.cujae.pweb.utils.JsfUtils;

@Component // Le indica a spring es un componente registrado
@ManagedBean
@ViewScoped // Este es el alcance utilizado para trabajar con Ajax
public class ManageStudentBean {

	private StudentDTO studentDTO;
	private StudentDTO selectedStudent;
	private List<StudentDTO> students;
	
	/*
	 * @Autowired es la manera para inyectar una dependencia/clase anotada
	 * con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private StudentService studentService;

	public ManageStudentBean() {
	}

	// Esta anotación permite que se ejecute code luego de haberse ejecuta el
	// constructor de la clase.
	@PostConstruct
	public void init() {
		students = students == null ? studentService.getStudents() : students;
		this.selectedStudent = new StudentDTO();
	}

	// Se ejecuta al dar clic en el button Nuevo
	// ! Este método nunca se llega a ejecutar, por algún error que desconozco
	public void openNew() {
		this.selectedStudent = new StudentDTO();
		System.out.println(selectedStudent);
	}

	// Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
	}

	// Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar
	// al usuario
	public void saveStudent() {
		if (this.selectedStudent.getId() == null) {
			studentService.createStudent(this.selectedStudent);
			// Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO)
			// obteniendo el mensaje
			// desde el fichero de recursos, con la llave message_student_added
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_student_added");
		} else {
			// register student
			studentService.updateStudent(this.selectedStudent);

			this.selectedStudent = new StudentDTO();
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_student_edited");
		}
		// load datatable again with new values
		students = studentService.getStudents();

		this.selectedStudent = new StudentDTO();
		// Este code permite cerrar el dialog cuyo id es managestudentDialog. Este
		// identificador es el widgetVar
		PrimeFaces.current().executeScript("PF('manageStudentDialog').hide()");
		// Este code es para refrescar el componente con id dt-students que se encuentra
		// dentro del formulario con id form
		PrimeFaces.current().ajax().update("form:dt-students");
	}

	public void cancel() {
		this.selectedStudent = new StudentDTO();
	}

	// Permite eliminar un estudiante
	public void deleteStudent() {
		try {
			// delete student
			studentService.deleteStudent(this.selectedStudent.getId());
			this.selectedStudent = new StudentDTO();

			// load datatable again with new values
			students = studentService.getStudents();

			// JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO,
			// "message_student_removed");
			PrimeFaces.current().ajax().update("form:dt-students");// Este code es para refrescar el componente con id
																	// dt-students que se encuentra dentro del
																	// formulario
																	// con id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}

	}

	public StudentDTO getStudentDto() {
		return studentDTO;
	}

	public void setStudentDto(StudentDTO studentDTO) {
		this.studentDTO = studentDTO;
	}

	public StudentDTO getSelectedStudent() {
		return selectedStudent;
	}

	public void setSelectedStudent(StudentDTO selectedStudent) {
		this.selectedStudent = selectedStudent;
	}

	public List<StudentDTO> getStudents() {
		return students;
	}

	public void setStudents(List<StudentDTO> students) {
		this.students = students;
	}

}
