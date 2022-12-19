package cu.edu.cujae.pweb.bean.initCourse;

import cu.edu.cujae.pweb.dto.*;
import cu.edu.cujae.pweb.service.CourseService;
import cu.edu.cujae.pweb.service.InitCourseTransactionService;
import cu.edu.cujae.pweb.service.StudentGradeService;
import cu.edu.cujae.pweb.service.StudentGradeServiceImpl;
import cu.edu.cujae.pweb.utils.JsfUtils;
import cu.edu.cujae.pweb.utils.ValidateInput;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private ManageSubjectInYearBean manageSubjectInYearBean;

    @Autowired
    private InitCourseTransactionService initCourseTransactionService;

    @Autowired
    private StudentGradeService studentGradeService;

    @Autowired
    private CourseService courseService;

    public boolean isCorrectStudentPromotion(){
        boolean isCorrectStudentPromotion = false;
        List<CourseDTO> courses = courseService.getCourses();
        Integer courseId = courses.get(courses.size()-1).getId();
        List<StudentGradeOnlyIdDTO> studentsLastCourse = studentGradeService.getStudentGradesByCourseId(courseId);
        for (int i = 0; i < studentsLastCourse.size() && !isCorrectStudentPromotion; i++) {
            if (studentsLastCourse.get(i).getGradeValue() != 2 &&
                    studentsLastCourse.get(i).getGradeValue() != 3 &&
                    studentsLastCourse.get(i).getGradeValue() != 4 &&
                    studentsLastCourse.get(i).getGradeValue() != 5) {
                isCorrectStudentPromotion = true;
            }
        }
        return isCorrectStudentPromotion;
    }

    public void startCourse()throws IOException{

//            Validar Primera y Segunda Vista (llamar a metodo validate() de su bean).
        if (!manageSelectionStudentBean.isCorrect()) {
            //            Mostrar mensaje de error
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error_brigade_without_students");
        } else{
            //            Obtener los datos.
            List<SubjectInCourseCompleteDTO> listSubjects = new ArrayList<>();
            manageSubjectInYearBean.getSubjectsInCourseList().forEach(subjectsInCourseByYear -> {
                for(SubjectInCourseCompleteDTO temp: subjectsInCourseByYear)
                    listSubjects.add(temp);
            });
            List<List<StudentDTO>> listStudents =  manageSelectionStudentBean.getStudentsList();

            //            Llamar a servicion de unica transacción.
            System.out.println("INICIO DE NUEVO CURSO");
            InitCourseTransactionDTO initCourseTransactionDTO = new InitCourseTransactionDTO(listSubjects, listStudents);
            initCourseTransactionService.initCourse(initCourseTransactionDTO);
        }
    }

    public String getViewSelected(){
        if (isCorrectStudentPromotion()) {
            manageSelectionStudentBean.refresh();
            manageSubjectInYearBean.refresh();

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            String url = request.getRequestURL().toString().substring(39);

            if (actionLabel.equals("Atras") && url.equals("init-course/selection-subject")) {
                actionLabel = "Siguiente";
                disableStart = "true";
                colorStart = "rgb(161,156,156)";
            }
            return "/pages/init-course/selection-subject.xhtml";
        }
        else {
            //PrimeFaces.current().dialog().showMessageDynamic(
                  //  new FacesMessage(FacesMessage.SEVERITY_WARN,"No se puede crear curso", "Faltan estudiantes por notas"));
            return "/pages/welcome/welcome.xhtml";
        }
    }

    public void moveAction() throws IOException {

        if(actionLabel.equals("Siguiente")) {
            if(manageSubjectInYearBean.isCorrect()) {
                String url = "http://localhost:8085/project-frontend/init-course/selection-student"; //url donde se redirige la pantalla
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.getExternalContext().redirect(url);

                this.actionLabel = "Atras";
                this.disableStart = "false";
                this.colorStart = "rgb(13,213,120)";
            }else{
                System.out.println("ERROR: AÑOS ASIGNATURAS");
            }

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
