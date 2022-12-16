package cu.edu.cujae.pweb.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

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
	private DropOutDTO dropout;
	
	private List<StudentDropOutNamedDTO> studentDropouts;
	private StudentDropOutNamedDTO studentDropout;

	private Integer course = 1;
	
	@Autowired
	private DropOutService dropoutService;
	
	@Autowired
	private StudentDropOutService studentDropoutService;
	
	@Autowired
	private StudentService studentService;

	public DropOutBean() {

	}
	
//	public void dropStudents(Integer courseId, List<StudentDTO> students) {
////		if(dropout.getCause())
//		for(StudentDTO student: students) {
//			studentDropoutService.createStudentDropOut(new StudentDropOutDTO(dropout.getId(), courseId, student.getId()));
//			
////			Change students status to "Baja". 
//			student.setStatusID(4);
//			studentService.updateStudent(student);
//		}
//		
//		if(students.size() == 1)
//			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_studentDropOut_added");
//		else
//			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_studentDropOuts_added");		
//	}
	
	public List<DropOutDTO> getDropouts() {
		dropouts = dropoutService.getDropOuts();
		return dropouts;
	}

	public void setDropouts(List<DropOutDTO> dropouts) {
		this.dropouts = dropouts;
	}
	
	public DropOutDTO getDropout() {
		return dropout;
	}

	public void setDropout(DropOutDTO dropout) {
		this.dropout = dropout;
	}
	
	public List<StudentDropOutNamedDTO> getStudentDropouts() {
		studentDropouts = studentDropoutService.getNamedStudentDropOutByCourseId(course);
		System.out.println(studentDropouts);
		System.out.println("Size:" + studentDropouts.size());
		
		for(StudentDropOutNamedDTO st: studentDropouts)
			System.out.println("Size:" + st.getStudentName());
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
}