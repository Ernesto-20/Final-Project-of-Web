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
import java.util.UUID;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageSubjectBean {

    private SubjectDTO subjectDTO;
    private SubjectDTO selectedSubject;
    private List<SubjectDTO> subjects;


    /* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
     * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
     */
    @Autowired
    private SubjectService subjectService;

    public ManageSubjectBean() {

    }

    //Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase.
    @PostConstruct
    public void init() {
        subjects = subjects == null ? subjectService.getSubjects() : subjects;
    }

    //Se ejecuta al dar clic en el button Nuevo
    public void openNew() {
        this.selectedSubject = new SubjectDTO();
    }

    //Se ejecuta al dar clic en el button con el lapicito
    public void openForEdit() {

    }

    //Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar a la asignatura
    public void saveSubject() {
        if (this.selectedSubject.getId() == null) {
            this.selectedSubject.setId(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9)));
            this.selectedSubject.setNewRecord(true);

            this.subjects.add(this.selectedSubject);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_subject_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_subject_added
        }
        else {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_subject_edited");
        }

        PrimeFaces.current().executeScript("PF('manageSubjectDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageSubjectDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-subjects");// Este code es para refrescar el componente con id dt-subjects que se encuentra dentro del formulario con id form
    }

    //Permite eliminar una asignatura
    public void deleteSubject() {
        try {
            this.subjects.remove(this.selectedSubject);
            this.selectedSubject = null;
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_subject_removed");
            PrimeFaces.current().ajax().update("form:dt-subjects");// Este code es para refrescar el componente con id dt-subjects que se encuentra dentro del formulario con id form
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
