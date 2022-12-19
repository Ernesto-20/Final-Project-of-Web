package cu.edu.cujae.pweb.bean.student;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.StudentGradeDTO;
import cu.edu.cujae.pweb.service.StudentGradeService;

@Component
@ManagedBean
@ViewScoped
public class ManageStudentGradeBean {

	private List<StudentGradeDTO> studentGrades;

	@Autowired
	private StudentGradeService studentGradeService;

	public ManageStudentGradeBean() {

	}
	
	public void searchStudentGrades(Integer studentId, Integer yearId) {
		studentGrades = studentGradeService.getStudentGradesByYearId(studentId, yearId);
//		return studentGrades;
	}
	
	public List<StudentGradeDTO> getStudentGrades() {
		return studentGrades;
	}

	public void setStudentGrades(List<StudentGradeDTO> studentGrades) {
		this.studentGrades = studentGrades;
	}
}