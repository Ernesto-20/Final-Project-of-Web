package cu.edu.cujae.pweb.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@Component
@ManagedBean
@ViewScoped
public class ManageSelectionStudentBean {
    private CourseBean newCourse;

    public ManageSelectionStudentBean() {
    }

    @PostConstruct
    public void init() {
        this.newCourse = new CourseBean();
        this.newCourse.setCourse("2022-2023");
    }

    public CourseBean getNewCourse() {
        return newCourse;
    }

    public void setNewCourse(CourseBean newCourse) {
        this.newCourse = newCourse;
    }
}
