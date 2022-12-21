package cu.edu.cujae.pweb.bean.student;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.DropOutDTO;
import cu.edu.cujae.pweb.dto.StudentDTO;
import cu.edu.cujae.pweb.dto.StudentDropOutDTO;
import cu.edu.cujae.pweb.dto.StudentDropOutNamedDTO;
import cu.edu.cujae.pweb.service.DropOutService;
import cu.edu.cujae.pweb.service.StudentDropOutService;
import cu.edu.cujae.pweb.service.StudentService;
import cu.edu.cujae.pweb.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class DropOutBean {

	private List<DropOutDTO> dropouts;

	private List<StudentDropOutNamedDTO> studentDropouts;
	private StudentDropOutNamedDTO studentDropout;
	private StudentDropOutNamedDTO selectedStudentDropout;

	private Integer course = 1;
	private Integer dropout = 1;

	@Autowired
	private DropOutService dropoutService;

	@Autowired
	private StudentDropOutService studentDropoutService;

	@Autowired
	private StudentService studentService;

	public DropOutBean() {

	}

	public void dropStudents(Integer courseId, List<StudentDTO> selectedStudents) {
		try {
			for (StudentDTO student : selectedStudents) {

				// Si el estudiante existe en la tabla de 'student_dropout' en el curso dado,
				// entonces no crearlo
				if (!studentDropoutExist(courseId, student)) {
					studentDropoutService
							.createStudentDropOut(new StudentDropOutDTO(dropout, courseId, student.getId()));

					// Change students status to "Baja".
					student.setStatusID(4);
					studentService.updateStudent(student);
				}
			}

			if (selectedStudents.size() == 1)
				JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_studentDropOut_added");
			else if (selectedStudents.size() > 1)
				JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_studentDropOuts_added");

			PrimeFaces.current().ajax().update(":form:dt-students");
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}

		PrimeFaces.current().executeScript("PF('studentDropoutDialog').hide()");
	}

	private boolean studentDropoutExist(Integer courseId, StudentDTO student) {
		return studentDropoutService.getNamedStudentDropOutByStudentId(student.getId()).stream()
				.anyMatch(st -> st.getCourseId() == courseId);
	}

	public void openForEdit() {
	}

	public void deleteStudentDropOut() {
		try {
			studentDropoutService.deleteStudentDropOut(this.selectedStudentDropout);
			this.selectedStudentDropout = new StudentDropOutNamedDTO();

			// load datatable again with new values
			studentDropouts = studentDropoutService.getNamedStudentDropOutByCourseId(course);

			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_studentDropOut_deleted");
			PrimeFaces.current().ajax().update("student-dropout-list:dt-students-dropouts");

		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}

	}

	public List<DropOutDTO> getDropouts() {
		dropouts = dropoutService.getDropOuts();
		return dropouts;
	}

	public void setDropouts(List<DropOutDTO> dropouts) {
		this.dropouts = dropouts;
	}

	public Integer getDropout() {
		return dropout;
	}

	public void setDropout(Integer dropout) {
		this.dropout = dropout;
	}

	public List<StudentDropOutNamedDTO> getStudentDropouts() {
		studentDropouts = studentDropoutService.getNamedStudentDropOutByCourseId(course);
		return studentDropouts;
	}

	public void setStudentDropouts(List<StudentDropOutNamedDTO> studentDropouts) {
		this.studentDropouts = studentDropouts;
	}

	public StudentDropOutNamedDTO getStudentDropout() {
		return studentDropout;
	}

	public void setStudentDropout(StudentDropOutNamedDTO studentDropout) {
		this.studentDropout = studentDropout;
	}

	public Integer getCourse() {
		return course;
	}

	public void setCourse(Integer course) {
		this.course = course;
	}

	public StudentDropOutNamedDTO getSelectedStudentDropout() {
		return selectedStudentDropout;
	}

	public void setSelectedStudentDropout(StudentDropOutNamedDTO selectedStudentDropout) {
		this.selectedStudentDropout = selectedStudentDropout;
	}
}