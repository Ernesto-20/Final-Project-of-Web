package cu.edu.cujae.pweb.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import cu.edu.cujae.pweb.dto.SubjectInCourseWithNameDTO;
import cu.edu.cujae.pweb.service.SubjectInCourseWithNameService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component //Le indica a spring es un componente registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax

public class ManageSubjectInCourseWithNameBean {

    private SubjectInCourseWithNameDTO subject;
    private SubjectInCourseWithNameDTO selectedSubject;
    private List<SubjectInCourseWithNameDTO> subjects;


    /* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
     * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
     */
    @Autowired
    private SubjectInCourseWithNameService subjectInCourseWithNameService;

    public ManageSubjectInCourseWithNameBean() {

    }

    //Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase.
    @PostConstruct
    public void init() {
        subjects = subjects == null ? subjectInCourseWithNameService.getSubjects() : subjects;
    }

    //Se ejecuta al dar clic en el button con el lapicito
    public void openForEdit() {
//		List<RoleDto> roles = this.selectedUser.getRoles();
//		this.selectedRoles = roles.stream().map(r -> r.getId()).toArray(Long[]::new);
    }

    public void saveSubject() {
        if (this.selectedSubject.getId() == null) {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_edited");
        }

        PrimeFaces.current().executeScript("PF('manageSubjectDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-subjects");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    }

    public void deleteSubject() {
        try {
            this.subjects.remove(this.selectedSubject);
            this.selectedSubject = null;
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_removed");
            PrimeFaces.current().ajax().update("form:dt-subjects");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
        } catch (Exception e) {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
        }

    }

    public List<SubjectInCourseWithNameDTO> getSubjects() {
        return subjects;
    }

    public void setStudents(List<SubjectInCourseWithNameDTO> subjects) {
        this.subjects = subjects;
    }

    public SubjectInCourseWithNameDTO getSubject() {
        return subject;
    }

    public void setSubject(SubjectInCourseWithNameDTO subject) {
        this.subject = subject;
    }

    public SubjectInCourseWithNameDTO getSelectedSubject() {
        return selectedSubject;
    }

    public void setSelectedSubject(SubjectInCourseWithNameDTO selectedSubject) {
        this.selectedSubject = selectedSubject;
    }
}