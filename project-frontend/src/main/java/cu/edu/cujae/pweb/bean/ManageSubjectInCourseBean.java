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
import java.util.stream.Collectors;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageSubjectInCourseBean {

	private SubjectInCourseDTO subjectInCourseDTO;
	private SubjectInCourseDTO selectedSubjectInCourse;
	private SubjectInCourseNamedDTO selectedSubjectInCourseNamed;
	private List<SubjectInCourseDTO> subjectsInCourse;
	private List<SubjectInCourseNamedDTO> subjectsInCourseNamed;
	private int courseSelectOption = 1;
	private int yearSelectOption = 1;

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

	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
        this.selectedSubjectInCourse = new SubjectInCourseDTO();
    }
	
	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
//		List<RoleDto> roles = this.selectedUser.getRoles();
//		this.selectedRoles = roles.stream().map(r -> r.getId()).toArray(Long[]::new);
	}

	public List<SubjectInCourseNamedDTO> filterByCourseAndYear(int courseId, int yearId){
		return subjectInCourseService.getSubjectsInCourseNamed()
				.stream()
				.filter(dto -> dto.getCourseId() == courseSelectOption && dto.getYearId() == yearSelectOption)
				.collect(Collectors.toList());
	}

	public void onCourseOrYearChange() {
		subjectsInCourseNamed = filterByCourseAndYear(courseSelectOption, yearSelectOption);

		PrimeFaces.current().ajax().update("dataTable:dt-subjects");
	}

	public void openForEditNamed() {

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
				String.valueOf(selectedSubjectInCourseNamed.getSubjectId()),
				String.valueOf(selectedSubjectInCourseNamed.getCourseId()),
				String.valueOf(selectedSubjectInCourseNamed.getYearId()),
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

		this.subjectsInCourseNamed.remove(this.selectedSubjectInCourseNamed);
		subjectInCourseService.deleteSubjectInCourse(
				String.valueOf(selectedSubjectInCourseNamed.getSubjectId()),
				String.valueOf(selectedSubjectInCourseNamed.getCourseId()),
				String.valueOf(selectedSubjectInCourseNamed.getYearId())
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
//		Select options for course and year are 1 by default
		return filterByCourseAndYear(courseSelectOption, yearSelectOption);
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

	public int getCourseSelectOption() {
		return courseSelectOption;
	}

	public void setCourseSelectOption(int courseSelectOption) {
		this.courseSelectOption = courseSelectOption;
	}

	public int getYearSelectOption() {
		return yearSelectOption;
	}

	public void setYearSelectOption(int yearSelectOption) {
		this.yearSelectOption = yearSelectOption;
	}
}
