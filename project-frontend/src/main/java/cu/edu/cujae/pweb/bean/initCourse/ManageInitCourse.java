package cu.edu.cujae.pweb.bean.initCourse;

import cu.edu.cujae.pweb.service.InitCourseTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
    private String disableStart = "true";
    private String colorStart = "rgb(161,156,156)";

    public ManageInitCourse(){

    }

    @Autowired
    private ManageSelectionStudentBean manageSelectionStudentBean;

//    @Autowired
//    private ManageSelectionSubjectBean manageSelectionSubjectBean;

    @Autowired
    private InitCourseTransactionService initCourseTransactionService;

    public void starCourse()throws IOException{
        System.out.println("STAR COURSE");
//            Validar Primera y Segunda Vista (llamar a metodo validate() de su bean).
//        if(manageSelectionStudentBean.isCorrect()) {
            if (!manageSelectionStudentBean.iCorrect()) {
                System.out.println("FALTAN DATOS");
                //            Mostrar mensaje de error
                //            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_error_brigade_without_students");
            } else {
                System.out.println("OK");
                //            Obtener los datos.
                //            Llamar a servicion de unica transacción.
            }
//        }else{
//            System.out.println("Faltan años por asignar asignaturas");
//        }
    }

    public String getViewSelected(){
        manageSelectionStudentBean.refresh();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String url = request.getRequestURL().toString().substring(39);

        if(actionLabel.equals("Atras") && url.equals("init-course/selection-subject")){
          actionLabel = "Siguiente";
            disableStart = "true";
            colorStart = "rgb(161,156,156)";
        }
        return "selection-subject.xhtml";
    }

    public void moveAction() throws IOException {

        if(actionLabel.equals("Siguiente")) {
            String url = "http://localhost:8085/project-frontend/init-course/selection-student"; //url donde se redirige la pantalla
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().redirect(url);

            this.actionLabel = "Atras";
            this.disableStart = "false";
            this.colorStart = "rgb(13,213,120)";
        }else {
            String url = "http://localhost:8085/project-frontend/init-course/selection-subject";
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().redirect(url);

            this.actionLabel = "Siguiente";
            this.disableStart = "true";
            this.colorStart = "rgb(161,156,156)";
        }
    }

    public String getColorStart() {
        return colorStart;
    }

    public void setColorStart(String colorStart) {
        this.colorStart = colorStart;
    }

    public String getDisableStart() {
        return disableStart;
    }

    public void setDisableStart(String disableStart) {
        this.disableStart = disableStart;
    }

    public String getActionLabel() {
        return actionLabel;
    }

    public void setActionLabel(String actionLabel) {
        this.actionLabel = actionLabel;
    }
}
