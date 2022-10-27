package cu.edu.cujae.pweb.bean;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.CourseDTO;
import cu.edu.cujae.pweb.service.CourseService;
import cu.edu.cujae.pweb.utils.JsfUtils;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageCourseBean {
	
	private CourseDTO courseDTO;
	private CourseDTO selectedCourse;
	private List<CourseDTO> courses;
	private Map<String, String> courses2;
	
	/* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private CourseService courseService;
		
	
	public ManageCourseBean() {
		
	}
	

	//Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase. 
	@PostConstruct
    public void init() {
	    courses = courses == null ? courseService.getCourses() : courses;
	    courses2 = courses2 == null ? courseService.getCourses2() : courses2;
    }
	
	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
        this.selectedCourse = new CourseDTO();
    }
	
	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
//		List<RoleDto> roles = this.selectedUser.getRoles();
//		this.selectedRoles = roles.stream().map(r -> r.getId()).toArray(Long[]::new);
	}
	
	//Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
	public void saveCourse() {
        if (this.selectedCourse.getId() == null) {
            this.selectedCourse.setId(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
            this.selectedCourse.setNewRecord(true);
            
            this.courses.add(this.selectedCourse);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_edited");
        }

        PrimeFaces.current().executeScript("PF('manageStudentDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-courses");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    }

	//Permite eliminar un usuario
    public void deleteStudent() {
    	try {
    		this.courses.remove(this.selectedCourse);
            this.selectedCourse = null;
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_removed");
            PrimeFaces.current().ajax().update("form:dt-courses");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
        
    }

	public CourseDTO getCourseDTO() {
		return courseDTO;
	}

	public void setCourseDTO(CourseDTO courseDTO) {
		this.courseDTO = courseDTO;
	}

	public CourseDTO getselectedCourse() {
		return selectedCourse;
	}

	public void setselectedCourse(CourseDTO selectedCourse) {
		this.selectedCourse = selectedCourse;
	}

	public List<CourseDTO> getCourses() {
		return courses;
	}

	public void setCourses(List<CourseDTO> courses) {
		this.courses = courses;
	}


	public Map<String, String> getCourses2() {
		return courses2;
	}


	public void setCourses2(Map<String, String> courses2) {
		this.courses2 = courses2;
	}


	public CourseService getCourseService() {
		return courseService;
	}


	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}

}
