package cu.edu.cujae.pweb.bean.student;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cu.edu.cujae.pweb.dto.StudentDTO;
import cu.edu.cujae.pweb.dto.StudentInBrigadeDTO;
import cu.edu.cujae.pweb.service.BrigadeService;
import cu.edu.cujae.pweb.service.CourseService;
import cu.edu.cujae.pweb.service.StudentInBrigadeService;
import cu.edu.cujae.pweb.service.StudentService;
import cu.edu.cujae.pweb.service.YearService;
import cu.edu.cujae.pweb.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ManageStudentBean {

	private StudentDTO studentDTO;
	private StudentDTO selectedStudent;
	private List<StudentDTO> students;
	private List<StudentDTO> selectedStudents;

	// Por defecto mostrar los estudiantes del curso, grupo y año con id = 1
	private Integer course = 1;
	private Integer year = 1;
	private Integer brigade = 1;

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentInBrigadeService studentInBrigadeService;

	public ManageStudentBean() {
	}

	@PostConstruct
	public void init() {
		this.selectedStudent = new StudentDTO();
	}

	// Se ejecuta al dar clic en el button Nuevo
	// ! Este método nunca se llega a ejecutar, por algún error que desconozco
	public void openNew() {
		this.selectedStudent = new StudentDTO();
	}

	public void openForEdit() {
	}

	public void saveStudent() {
		// Si un estudiante con el mismo ID ya está en la bd no se crea el nuevo
		// estudiante.
//		if (ValidateInput.isName()) {
			if(studentByIdNumDontExist()) {
				if (this.selectedStudent.getId() == null) {
					
					
					studentService.createStudent(this.selectedStudent);
					
					// Crear el estudiante dentro de la tabla student_in_brigade
					
					StudentInBrigadeDTO studentInBrigade = new StudentInBrigadeDTO(
							studentService.getStudentByIdNum(selectedStudent.getIdNum()).getId(), course, brigade, 2);
					studentInBrigadeService.createStudentInBrigade(studentInBrigade);
					
					JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_student_added");
					
				} else {
					studentService.updateStudent(this.selectedStudent);
					
					this.selectedStudent = new StudentDTO();
					JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_student_edited");
				}
				students = studentService.getStudentsByBrigadeCourseYearIds(this.brigade, this.course, this.year);
				
				this.selectedStudent = new StudentDTO();
				PrimeFaces.current().executeScript("PF('manageStudentDialog').hide()");
				PrimeFaces.current().ajax().update("form:dt-students");
			} else 
				JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_student_already_exist");
//		} else 
//			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_student_already_exist");
		
	}

	public void deleteStudent() {
		try {
			studentService.deleteStudent(this.selectedStudent.getId());
			this.selectedStudent = new StudentDTO();

			// load datatable again with new values
			students = studentService.getStudentsByBrigadeCourseYearIds(this.brigade, this.course, this.year);

			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_student_deleted");
			PrimeFaces.current().ajax().update("form:dt-students");

		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}

	}

	public void reloadListStudent() {
		students = studentService.getStudentsByBrigadeCourseYearIds(this.brigade, this.course, this.year);
		PrimeFaces.current().ajax().update("form:dt-students");
	}

	// Usado para deshabilitar el botón de Dar Baja
	public boolean hasSelectedStudents() {
		return this.selectedStudents != null && !this.selectedStudents.isEmpty();
	}

	private boolean studentByIdNumDontExist() {
		StudentDTO student = studentService.getStudentByIdNum(selectedStudent.getIdNum());
//		if(student != null || student.getId() != selectedStudent.getId())
		return student == null || student.getId() != selectedStudent.getId();
	}

	public void cancel() {
		this.selectedStudent = new StudentDTO();
	}

	public List<StudentDTO> getStudents() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		String url = request.getRequestURL().toString().substring(39);
		students = studentService.getStudentsByBrigadeCourseYearIds(year, brigade, course);
		switch (url) {
			// Vista de Sandy
			case "students":
				students = studentService.getStudentsByBrigadeCourseYearIds(year, brigade, course);
				break;
			// Vista de Ernesto
			case "init-course/selection-student":
				students = new ArrayList<>();
				// students = studentService.getStudentsByBrigadeCourseYearIds(year, brigade,
				// course);
				break;
			// Vista de Daniel
			case "":
				students = studentService.getStudents();
				break;
		}
		return students;
	}

	public void setStudents(List<StudentDTO> students) {
		this.students = students;
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

	public List<StudentDTO> getSelectedStudents() {
		return selectedStudents;
	}

	public void setSelectedStudents(List<StudentDTO> selectedStudents) {
		this.selectedStudents = selectedStudents;
	}

	public Integer getBrigade() {
		return brigade;
	}

	public void setBrigade(Integer brigade) {
		this.brigade = brigade;
	}

	public Integer getCourse() {
		return course;
	}

	public void setCourse(Integer course) {
		this.course = course;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

}
