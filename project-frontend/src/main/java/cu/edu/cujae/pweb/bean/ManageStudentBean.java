package cu.edu.cujae.pweb.bean;

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
import cu.edu.cujae.pweb.service.BrigadeService;
import cu.edu.cujae.pweb.service.CourseService;
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
	
	// Por defecto mostrar los estudiantes del curso, grupo y año con id = 1
	private Integer course = 1;
	private Integer year = 1;
	private Integer brigade = 1;
	
	public void reloadListStudent() {
		System.out.println("Brigada:" + brigade);
		System.out.println("Curso:" + course);
		System.out.println("Año:" + year);
		students = studentService.getStudentsByBrigadeCourseYearIds(this.brigade, this.course, this.year);
		System.out.println("Estudiantes:" + students);
		PrimeFaces.current().ajax().update("form:dt-students");
	}
	
	@Autowired
	private StudentService studentService;

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
		System.out.println(selectedStudent);
	}

	public void openForEdit() {
	}

	public void saveStudent() {
		if (this.selectedStudent.getId() == null) {
			studentService.createStudent(this.selectedStudent);
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
	}

	public void cancel() {
		this.selectedStudent = new StudentDTO();
	}

	public void deleteStudent() {
		try {
			studentService.deleteStudent(this.selectedStudent.getId());
			this.selectedStudent = new StudentDTO();

			// load datatable again with new values
			students = studentService.getStudentsByBrigadeCourseYearIds(this.brigade, this.course, this.year);

			PrimeFaces.current().ajax().update("form:dt-students");
			
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
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String url = request.getRequestURL().toString().substring(39);
		System.out.println(url);
		students = studentService.getStudentsByBrigadeCourseYearIds(year, brigade, course);
		switch(url) {
//			Vista de Sandy
			case "students":
				students = studentService.getStudentsByBrigadeCourseYearIds(year, brigade, course);
				break;
//			Vista de Ernesto
			case "init-course/selection-student":
				students = new ArrayList<>();
//				students = studentService.getStudentsByBrigadeCourseYearIds(year, brigade, course);
				break;
//			Vista de Daniel
			case "":
				students = studentService.getStudents() ;
				break;
		}
		return students;
	}

	public void setStudents(List<StudentDTO> students) {
		this.students = students;
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
