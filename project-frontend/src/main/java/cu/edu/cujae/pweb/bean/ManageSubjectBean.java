package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.SubjectDTO;
import cu.edu.cujae.pweb.service.SubjectService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.List;

@Component // Le indica a spring es un componente registrado
@ManagedBean
@ViewScoped // Este es el alcance utilizado para trabajar con Ajax
public class ManageSubjectBean {

	private SubjectDTO subjectDTO;
	private SubjectDTO selectedSubject;
	private List<SubjectDTO> subjects;

	/*
	 * @Autowired es la manera para inyectar una dependencia/clase anotada
	 * con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private SubjectService subjectService;

	public ManageSubjectBean() {

	}

	// Esta anotación permite que se ejecute code luego de haberse ejecuta el
	// constructor de la clase.
	@PostConstruct
	public void init() {
		subjects = subjects == null ? subjectService.getSubjects() : subjects;
	}

	// Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
		this.selectedSubject = new SubjectDTO();
	}

	// Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
		// List<RoleDto> roles = this.selectedUser.getRoles();
		// this.selectedRoles = roles.stream().map(r -> r.getId()).toArray(Long[]::new);
	}

	// Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar
	// al usuario
	public void saveSubject() {
		if (this.selectedSubject.getId() == null) {

			// register subject
			subjectService.createSubject(this.selectedSubject);

			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_added"); // Este code permite
																									// mostrar un
																									// mensaje exitoso
																									// (FacesMessage.SEVERITY_INFO)
																									// obteniendo el
																									// mensage desde el
																									// fichero de
																									// recursos, con la
																									// llave
																									// message_user_added
		} else {
			// register subject
			subjectService.updateSubject(this.selectedSubject);
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_edited");
		}

		// load datatable again with new values
		subjects = subjectService.getSubjects();
		PrimeFaces.current().executeScript("PF('manageSubjectDialog').hide()");// Este code permite cerrar el dialog
																				// cuyo id es manageUserDialog. Este
																				// identificador es el widgetVar
		PrimeFaces.current().ajax().update("formSelectionSubject:dt-subjects");// Este code es para refrescar el componente con id
																// dt-users que se encuentra dentro del formulario con
																// id form
	}

	// Permite eliminar una asignatura
	public void deleteSubject() {
		try {
			// delete subject
			System.out.println(selectedSubject.getName());
			subjectService.deleteSubject(this.selectedSubject.getId());
			this.selectedSubject = null;
			// load datatable again with new values
			subjects = subjectService.getSubjects();
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_deleted");
			PrimeFaces.current().ajax().update("formSelectionSubject:dt-subjects");// Este code es para refrescar el componente con id
																	// dt-users que se encuentra dentro del formulario
																	// con id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}

	}

	public SubjectDTO getSubjectDTO() {
		return subjectDTO;
	}

	public void setSubjectDTO(SubjectDTO subjectDTO) {
		this.subjectDTO = subjectDTO;
	}

	public SubjectDTO getSelectedSubject() {
		return selectedSubject;
	}

	public void setSelectedSubject(SubjectDTO selectedSubject) {
		this.selectedSubject = selectedSubject;
	}

	public List<SubjectDTO> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectDTO> subjects) {
		this.subjects = subjects;
	}

}
