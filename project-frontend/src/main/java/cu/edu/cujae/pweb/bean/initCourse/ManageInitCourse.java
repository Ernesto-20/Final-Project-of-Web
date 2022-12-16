package cu.edu.cujae.pweb.bean.initCourse;

import cu.edu.cujae.pweb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@ManagedBean
@ViewScoped
public class ManageInitCourse {
    private String actionLabel = "Siguiente";

    public ManageInitCourse(){

    }

    @Autowired
    private ManageSelectionStudentBean manageSelectionStudentBean;


    public String getViewSelected(){
        manageSelectionStudentBean.refresh();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String url = request.getRequestURL().toString().substring(39);

        if(actionLabel.equals("Atras") && url.equals("init-course/selection-subject")){
          actionLabel = "Siguiente";
        }
        return "selection-subject.xhtml";
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

    public void startCourse()throws IOException{
//            Validar Primera y Segunda Vista (llamar a metodo validate() de su bean).
//            Obtener los datos.
//            Llamar a servicion de unica transacción.
    }


    public String getActionLabel() {
        return actionLabel;
    }

    public void setActionLabel(String actionLabel) {
        this.actionLabel = actionLabel;
    }
}
