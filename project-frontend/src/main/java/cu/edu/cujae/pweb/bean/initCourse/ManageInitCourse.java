package cu.edu.cujae.pweb.bean.initCourse;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.IOException;

@Component
@ManagedBean
@ViewScoped
public class ManageInitCourse {
    private String actionLabel = "Siguiente";
    private String directionURL;

    public ManageInitCourse(){

    }

    public void moveAction() throws IOException {

        if(actionLabel.equals("Siguiente")) {
            String url = "http://localhost:8085/project-frontend/init-course/selection-student"; //url donde se redirige la pantalla
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().redirect(url);

            this.actionLabel = "Atras";
        }else {
            String url = "http://localhost:8085/project-frontend/init-course/selection-subject";
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().redirect(url);

            this.actionLabel = "Siguiente";
        }
    }

    public String getDirectionURL() {
        if(actionLabel.equals("selection-subject.xhtml")){
            actionLabel = "selection-student.xhtml";
        }else{
            actionLabel = "selection-subject.xhtml";
        }

        return actionLabel;
    }

    public void setDirectionURL(String directionURL) {
        this.directionURL = directionURL;
    }

    public String getActionLabel() {
        return actionLabel;
    }

    public void setActionLabel(String actionLabel) {
        this.actionLabel = actionLabel;
    }
}
