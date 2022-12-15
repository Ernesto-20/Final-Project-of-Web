package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.SubjectDTO;
import cu.edu.cujae.pweb.dto.SubjectInCourseDTO;
import cu.edu.cujae.pweb.dto.SubjectInCourseNamedDTO;
import cu.edu.cujae.pweb.dto.YearDTO;
import cu.edu.cujae.pweb.service.SubjectInCourseService;
import cu.edu.cujae.pweb.service.YearService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageSubjectInCourseBean {

//	@ManagedProperty("#{manageCourseBean}")
//	private ManageCourseBean manageCourseBean;

	private SubjectInCourseDTO subjectInCourseDTO;
	private SubjectInCourseDTO selectedSubjectInCourse;
	private SubjectInCourseNamedDTO selectedSubjectInCourseNamed;
	private List<SubjectInCourseDTO> subjectsInCourse;
	private List<SubjectInCourseNamedDTO> subjectsInCourseNamed;

	private List<YearDTO> years;

	/* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private SubjectInCourseService subjectInCourseService;

	@Autowired
	private YearService yearService;

	public ManageSubjectInCourseBean() {
		
	}

//	public void setManageCourseBean(ManageCourseBean manageCourseBean) {
//		this.manageCourseBean = manageCourseBean;
//	}
	

	//Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase. 
	@PostConstruct
    public void init() {
	    subjectsInCourse = subjectsInCourse == null ? subjectInCourseService.getSubjectsInCourse() : subjectsInCourse;
    	subjectsInCourseNamed = subjectsInCourseNamed == null ? subjectInCourseService.getSubjectsInCourseNamed() : subjectsInCourseNamed;
		
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

	public void onCourseChange(){
		System.out.println("Hubo un cambio en el select del curso");
	}

	public void openForEditNamed() {
		System.out.println("Aqui se supone que se abra para edit algun NamedDTO");
	}
	
	//Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
	public void saveSubjectInCourse() {
        if (this.selectedSubjectInCourse.getSubjectId() == null && this.selectedSubjectInCourse.getCourseId() == null
		&& this.selectedSubjectInCourse.getYearId() == null) {

        	JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_edited");
        }

        PrimeFaces.current().executeScript("PF('manageSubjectInCourseDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-subjectsInCourse");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    }

	public void saveSubjectInCourseNamed(){
		subjectInCourseService.updateSubjectInCourse(new SubjectInCourseDTO(
				selectedSubjectInCourseNamed.getSubjectId(),
				selectedSubjectInCourseNamed.getCourseId(),
				selectedSubjectInCourseNamed.getYearId(),
				selectedSubjectInCourseNamed.getHoursLong()
		));
		JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "edited_message_subject_in_courses");
		PrimeFaces.current().executeScript("PF('manageSubjectDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
		PrimeFaces.current().ajax().update("form:dt-subjects");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form

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

	public void deleteSubjectInCourseNamed(){
		System.out.println("Aqui se supone que se va a borrar algo I believe");
		this.subjectsInCourseNamed.remove(this.selectedSubjectInCourseNamed);
		subjectInCourseService.deleteSubjectInCourse(
				selectedSubjectInCourseNamed.getSubjectId(),
				selectedSubjectInCourseNamed.getCourseId(),
				selectedSubjectInCourseNamed.getYearId()
		);
		JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "deleted_message_subject_in_courses");
		PrimeFaces.current().executeScript("PF('manageSubjectDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
		PrimeFaces.current().ajax().update("form:dt-subjects");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
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

	public SubjectInCourseNamedDTO getSelectedSubjectInCourseNamed() {
		return selectedSubjectInCourseNamed;
	}

	public void setSelectedSubjectInCourseNamed(SubjectInCourseNamedDTO selectedSubjectInCourseNamed) {
		this.selectedSubjectInCourseNamed = selectedSubjectInCourseNamed;
	}

	public List<SubjectInCourseDTO> getSubjectsInCourse() {
		return subjectsInCourse;
	}

	public List<SubjectInCourseNamedDTO> getSubjectsInCourseNamed() {
		return subjectsInCourseNamed;
	}

	public void setSubjectsInCourse(List<SubjectInCourseDTO> subjectsInCourse) {
		this.subjectsInCourse = subjectsInCourse;
	}

	public void setSubjectsInCourseNamed(List<SubjectInCourseNamedDTO> subjectsInCourseNamed) {
		this.subjectsInCourseNamed = subjectsInCourseNamed;
	}

	public List<YearDTO> getYears() {
		return years = years == null ? yearService.getYears() : years;
	}

	public void setYears(List<YearDTO> years) {
		this.years = years;
	}

}
