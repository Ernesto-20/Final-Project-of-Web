package cu.edu.cujae.pweb.bean.initCourse;

import cu.edu.cujae.pweb.bean.CourseBean;
import cu.edu.cujae.pweb.dto.StudentDTO;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@Component
@ManagedBean
@ViewScoped
public class ManageSelectionStudentBean {
    private List<List<StudentDTO>> newStudents;

    public ManageSelectionStudentBean() {
        newStudents = new ArrayList();
        newStudents.add(new ArrayList()); // first year
        newStudents.add(new ArrayList()); // second year
        newStudents.add(new ArrayList()); // third year
        newStudents.add(new ArrayList()); // four year
    }



    public List<List<StudentDTO>> getNewStudents() {
        return newStudents;
    }

    public void setNewStudents(List<List<StudentDTO>> newStudents) {
        this.newStudents = newStudents;
    }
}
