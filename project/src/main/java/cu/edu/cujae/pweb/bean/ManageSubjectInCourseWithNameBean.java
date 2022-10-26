package cu.edu.cujae.pweb.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import cu.edu.cujae.pweb.dto.SubjectInCourseWithNameDTO;
import cu.edu.cujae.pweb.service.SubjectInCourseWithNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax

public class ManageSubjectInCourseWithNameBean {
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

    public List<SubjectInCourseWithNameDTO> getSubjects() {
        return subjects;
    }

    public void setStudents(List<SubjectInCourseWithNameDTO> subjects) {
        this.subjects = subjects;
    }

}