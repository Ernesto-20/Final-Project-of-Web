package cu.edu.cujae.pweb.bean.student;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.StudentEscalafonDTO;
import cu.edu.cujae.pweb.service.StudentEscalafonService;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageStudentEscalafonBean {

    private List<StudentEscalafonDTO> studentsEscalafon;


    /* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
     * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
     */
    @Autowired
    private StudentEscalafonService studentEscalafonService;

    public ManageStudentEscalafonBean() {

    }

    //Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase.
    @PostConstruct
    public void init() {
        studentsEscalafon = studentsEscalafon == null ? studentEscalafonService.getStudentsEscalafon() : studentsEscalafon;
    }

    public List<StudentEscalafonDTO> getStudentsEscalafon() {
        return studentsEscalafon;
    }

    public void setStudents(List<StudentEscalafonDTO> studentsEscalafon) {
        this.studentsEscalafon = studentsEscalafon;
    }
}