package cu.edu.cujae.pweb.bean.initCourse;

import cu.edu.cujae.pweb.dto.SubjectDTO;
import cu.edu.cujae.pweb.service.SubjectService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@Component // Le indica a spring es un componente registrado
@ManagedBean
@ViewScoped // Este es el alcance utilizado para trabajar con Ajax
public class ManageSubjectBean {

	private SubjectDTO subjectDTO;
	private SubjectDTO selectedSubject;
	private List<SubjectDTO> subjects;
	private List<SubjectDTO> selectedSubjects;

	@Autowired
	private ManageSubjectInYearBean manageSubjectInYearBean;

	/*
	 * @Autowired es la manera para inyectar una dependencia/clase anotada
	 * con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private SubjectService subjectService;

	public ManageSubjectBean() {

	}


	// Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
		this.selectedSubject = new SubjectDTO();
	}

	// Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
	}

	// Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar
	// al usuario
	public void saveSubject() {
		if (!existSubjectName()) {
			if (this.selectedSubject.getId() == null) {
				// register subject
				subjectService.createSubject(this.selectedSubject);
				// load datatable again with new values
				List<SubjectDTO> temp = subjectService.getSubjects();
				subjects.add(temp.get(temp.size() - 1));
				JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_subject_added");
			}
			else {
				// register subject
				subjectService.updateSubject(this.selectedSubject);

				JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_subject_edited");
			}
			PrimeFaces.current().executeScript("PF('manageSubjectDialog').hide()");// Este code permite cerrar el dialog
			// cuyo id es manageUserDialog. Este
			// identificador es el widgetVar
			PrimeFaces.current().ajax().update("formSelectionSubject:dt-subjects");// Este code es para refrescar el componente con id
			// dt-subjects que se encuentra dentro del formulario con
			// id formSelectionSubject
		}
		else {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_subject_name_exist");
		}
	}

	private boolean existSubjectName() {
		boolean existSubjectName = false;
		for (int i = 0; i < subjects.size()-1 && !existSubjectName; i++) {
			if (selectedSubject.getName().equals(subjects.get(i).getName()))
				existSubjectName = true;
		}
		return existSubjectName;
	}

	// Permite eliminar una asignatura
	public void deleteSubject() {
		try {
			deleteSubjectInBDAndView(selectedSubject);
			this.selectedSubject = new SubjectDTO();
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_subject_deleted");
			PrimeFaces.current().ajax().update("formSelectionSubject:dt-subjects");// Este code es para refrescar el componente con id
																	// dt-subjects que se encuentra dentro del formulario
																	// con id formSelectionSubject
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}

	}

	private void deleteSubjectInBDAndView(SubjectDTO subject) {
		subjectService.deleteSubject(subject.getId());
		boolean found = false;
		for (int i = 0; i < subjects.size() && !found; i++) {
			if (subjects.get(i).getId().equals(subject.getId())) {
				subjects.remove(i);
				found = true;
			}
		}
	}

	public String getDeleteButtonMessage() {
		if (hasSelectedSubjects()) {
			int size = this.selectedSubjects.size();
			return size > 1 ? size + " " + JsfUtils.getStringValueFromBundle("btn_subject_delete_many")
					: JsfUtils.getStringValueFromBundle("btn_subject_delete_one");
		}
		return JsfUtils.getStringValueFromBundle("btn_subject_delete");
	}

	public boolean hasSelectedSubjects() {
		return this.selectedSubjects != null && !this.selectedSubjects.isEmpty();
	}

	public void deleteSelectedSubjects() {
		try {
			// delete subjects
			for (SubjectDTO s: selectedSubjects) {
				deleteSubjectInBDAndView(s);
			}
			this.selectedSubjects = new ArrayList<SubjectDTO>();
			// load datatable again with new values
			subjects = subjectService.getSubjects();
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_subject_deleted");
			PrimeFaces.current().ajax().update("formSelectionSubject:dt-subjects");// Este code es para refrescar el componente con id
			PrimeFaces.current().executeScript("PF('dtSubjects').clearFilters()");
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
	}

	public void subjectAssign(){
		List<SubjectDTO> subjectsToAssign = new ArrayList<SubjectDTO>();
		for (SubjectDTO s:selectedSubjects) {
			boolean found = false;
			for (int i=0; i < subjects.size() && !found; i++) {
				if(subjects.get(i).getId() == s.getId()){
					subjectsToAssign.add(s);
					subjects.remove(i);
					i--;
				}
			}
		}

		setSelectedSubjects(new ArrayList<SubjectDTO>());
		manageSubjectInYearBean.subjectAssign(subjectsToAssign);
	}

	public void subjectRemove(List<SubjectDTO> subjectsToRemove) {
		subjectsToRemove.forEach(element-> {subjects.add(element);});
		PrimeFaces.current().ajax().update("formSelectionSubject");
	}

	public ManageSubjectInYearBean getManageSubjectInYearBean() {
		return manageSubjectInYearBean;
	}

	public void setManageSubjectInYearBean(ManageSubjectInYearBean manageSubjectInYearBean) {
		this.manageSubjectInYearBean = manageSubjectInYearBean;
	}

	public List<SubjectDTO> getSelectedSubjects() { return selectedSubjects; }

	public void setSelectedSubjects(List<SubjectDTO> selectedSubjects) { this.selectedSubjects = selectedSubjects; }

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
		subjects = subjects == null ? subjectService.getSubjects() : subjects;
		return subjects;
	}

	public void setSubjects(List<SubjectDTO> subjects) {
		this.subjects = subjects;
	}

	public SubjectService getSubjectService() {
		return subjectService;
	}
}
