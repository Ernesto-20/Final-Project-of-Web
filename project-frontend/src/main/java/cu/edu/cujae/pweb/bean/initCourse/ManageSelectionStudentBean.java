package cu.edu.cujae.pweb.bean.initCourse;

import cu.edu.cujae.pweb.bean.CourseBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@Component
@ManagedBean
@ViewScoped
public class ManageSelectionStudentBean {


    public ManageSelectionStudentBean() {
    }

    @PostConstruct
    public void init() {
    }

}
