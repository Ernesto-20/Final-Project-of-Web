package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.SubjectDTO;
import cu.edu.cujae.pweb.dto.SubjectInCourseDTO;
import cu.edu.cujae.pweb.service.SubjectInCourseService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.List;
import java.util.UUID;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageSubjectInCourseBean {

	private SubjectInCourseDTO subjectInCourseDTO;
	private SubjectInCourseDTO selectedSubjectInCourse;
	private List<SubjectInCourseDTO> subjectsInCourse;


	/* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private SubjectInCourseService subjectInCourseService;


	public ManageSubjectInCourseBean() {
		
	}
	

	//Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase. 
	@PostConstruct
    public void init() {
	    subjectsInCourse = subjectsInCourse == null ? subjectInCourseService.getSubjectsInCourse() : subjectsInCourse;
    }
	
	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
        this.selectedSubjectInCourse = new SubjectInCourseDTO();
    }
	
	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
//		List<RoleDto> roles = this.selectedUser.getRoles();
//		this.selectedRoles = roles.stream().map(r -> r.getId()).toArray(Long[]::new);
	}
	
	//Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
	public void saveSubjectInCourse() {
        if (this.selectedSubjectInCourse.getSubjectId() == null && this.selectedSubjectInCourse.getCourseId() == null
		&& this.selectedSubjectInCourse.getYearId() == null) {
            this.selectedSubjectInCourse.setSubjectId(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
			this.selectedSubjectInCourse.setCourseId(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
			this.selectedSubjectInCourse.setYearId(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
			this.selectedSubjectInCourse.setNewRecord(true);
            
            this.subjectsInCourse.add(this.selectedSubjectInCourse);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_edited");
        }

        PrimeFaces.current().executeScript("PF('manageSubjectInCourseDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-subjectsInCourse");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    }

	//Permite eliminar un usuario
    public void deleteSubjectInCourse() {
    	try {
    		this.subjectsInCourse.remove(this.selectedSubjectInCourse);
            this.selectedSubjectInCourse = null;
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_removed");
            PrimeFaces.current().ajax().update("form:dt-subjectsInCourse");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
        
    }

	public SubjectInCourseDTO getSubjectInCourseDTO() {
		return subjectInCourseDTO;
	}

	public void setSubjectInCourseDTO(SubjectInCourseDTO subjectInCourseDTO) {
		this.subjectInCourseDTO = subjectInCourseDTO;
	}

	public SubjectInCourseDTO getSelectedSubjectInCourse() {
		return selectedSubjectInCourse;
	}

	public void setSelectedSubjectInCourse(SubjectInCourseDTO selectedSubjectInCourse) {
		this.selectedSubjectInCourse = selectedSubjectInCourse;
	}

	public List<SubjectInCourseDTO> getSubjectsInCourse() {
		return subjectsInCourse;
	}

	public void setSubjectsInCourse(List<SubjectInCourseDTO> subjectsInCourse) {
		this.subjectsInCourse = subjectsInCourse;
	}


}
