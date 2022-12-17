package cu.edu.cujae.pweb.bean;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.CourseDTO;
import cu.edu.cujae.pweb.service.CourseService;
import cu.edu.cujae.pweb.utils.JsfUtils;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@Named
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageCourseBean {

	private String selectOption;
	private CourseDTO courseDTO;
	private CourseDTO selectedCourse;
	private List<CourseDTO> courses;
	
	/* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private CourseService courseService;
		
	public ManageCourseBean() {
		
	}
	
	public CourseDTO getCourseDTO() {
		return courseDTO;
	}

	public void setCourseDTO(CourseDTO courseDTO) {
		this.courseDTO = courseDTO;
	}

	public CourseDTO getSelectedCourse() {
		return selectedCourse;
	}

	public void setSelectedCourse(CourseDTO selectedCourse) {
		this.selectedCourse = selectedCourse;
	}

	public List<CourseDTO> getCourses() {
		return courses = courses == null ? courseService.getCourses() : courses;
	}

	public void setCourses(List<CourseDTO> courses) {
		this.courses = courses;
	}

	public CourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

	public String getSelectOption() {
		return selectOption;
	}

	public void setSelectOption(String selectOption) {
		this.selectOption = selectOption;

	}
}
