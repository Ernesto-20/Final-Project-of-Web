package cu.edu.cujae.pweb.bean;

import com.fasterxml.jackson.databind.JsonNode;
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
import javax.faces.view.ViewScoped;
import java.util.List;
import java.util.UUID;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageYearTabView {

	private SubjectInCourseNamedDTO subjectInCourseDTO;
	private SubjectInCourseNamedDTO selectedSubjectInCourse;
	private List<SubjectInCourseNamedDTO> subjectsInCourse;
	private List<SubjectInCourseNamedDTO> selectedSubjectsInCourse;
	private List<YearDTO> years;

	private List<List<SubjectInCourseNamedDTO>> subjectsInCourseList;
	private int currentIndex;


	/* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private SubjectInCourseService subjectInCourseService;

	@Autowired
	private YearService yearService;

	@Autowired
	private ManageSubjectBean manageSubjectBean;


	public ManageYearTabView() {
		
	}

	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
        this.selectedSubjectInCourse = new SubjectInCourseNamedDTO();
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

	public boolean hasSelectedSubjectsInCourse() {
		return this.selectedSubjectsInCourse != null && !this.selectedSubjectsInCourse.isEmpty();
	}

	public void subjectRemove() {

	}

    public void subjectAssign(List<SubjectDTO> subjectsToAssign) {
        
    }

	public List<List<SubjectInCourseNamedDTO>> getSubjectsInCourseList() {
		return subjectsInCourseList;
	}

	public void setSubjectsInCourseList(List<List<SubjectInCourseNamedDTO>> subjectsInCourseList) {
		this.subjectsInCourseList = subjectsInCourseList;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	public ManageSubjectBean getManageSubjectBean() {
		return manageSubjectBean;
	}

	public void setManageSubjectBean(ManageSubjectBean manageSubjectBean) {
		this.manageSubjectBean = manageSubjectBean;
	}

	public SubjectInCourseNamedDTO getSubjectInCourseDTO() {
		return subjectInCourseDTO;
	}

	public void setSubjectInCourseDTO(SubjectInCourseNamedDTO subjectInCourseDTO) {
		this.subjectInCourseDTO = subjectInCourseDTO;
	}

	public SubjectInCourseNamedDTO getSelectedSubjectInCourse() {
		return selectedSubjectInCourse;
	}

	public void setSelectedSubjectInCourse(SubjectInCourseNamedDTO selectedSubjectInCourse) {
		this.selectedSubjectInCourse = selectedSubjectInCourse;
	}

	public List<SubjectInCourseNamedDTO> getSubjectsInCourse() {
		return subjectsInCourse;
	}

	public void setSubjectsInCourse(List<SubjectInCourseNamedDTO> subjectsInCourse) {
		this.subjectsInCourse = subjectsInCourse;
	}

	public List<SubjectInCourseNamedDTO> getSelectedSubjectsInCourse() {
		return selectedSubjectsInCourse;
	}

	public void setSelectedSubjectsInCourse(List<SubjectInCourseNamedDTO> selectedSubjectsInCourse) {
		this.selectedSubjectsInCourse = selectedSubjectsInCourse;
	}

	public List<YearDTO> getYears() {
		years = years == null ? yearService.getYears() : years;
		return years;
	}

	public void setYears(List<YearDTO> years) {
		this.years = years;
	}
}
