package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.BrigadeDTO;
import cu.edu.cujae.pweb.dto.StudentDTO;
import cu.edu.cujae.pweb.dto.StudentGradeDTO;
import cu.edu.cujae.pweb.dto.StudentGradeOnlyIdDTO;
import cu.edu.cujae.pweb.service.BrigadeService;
import cu.edu.cujae.pweb.service.StudentGradeService;
import cu.edu.cujae.pweb.service.StudentService;
import cu.edu.cujae.pweb.utils.JsfUtils;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ManagedBean
@ViewScoped
public class ManageAssignStudentGradeBean {

    private List<StudentGradeDTO> studentGrades;
    private List<BrigadeDTO> brigades;
    private List<StudentDTO> students;
    private StudentGradeDTO selectedStudentGrade;
    private int courseSelectOption = 1;
    private int yearSelectOption = 1;
    private int brigadeSelectOption = 1;
    private int studentSelectOption = 1;

    @Autowired
    private StudentGradeService studentGradeService;

    @Autowired
    private BrigadeService brigadeService;

    @Autowired
    private StudentService studentService;

    public ManageAssignStudentGradeBean() {

    }

    public List<StudentGradeDTO> filterStudentGrades(){
        return studentGradeService.getStudentGradesAll()
                .stream()
                .filter(studentGrade ->
                    studentGrade.getStudentId() == studentSelectOption &&
                    studentGrade.getYearId() == yearSelectOption)
                .collect(Collectors.toList());
    }

    public void onFormChange(){
        studentGrades = filterStudentGrades();

        PrimeFaces.current().ajax().update(":form");
    }

    public void onCourseOrYearChange(){
        brigades = brigadeService.findByYearId(yearSelectOption);
        if(brigades.size() > 0)
            brigadeSelectOption = brigades.get(0).getId();
        else
            brigadeSelectOption = -1;

        students = studentService.getStudentsByBrigadeCourseYearIds(
                brigadeSelectOption,
                courseSelectOption,
                yearSelectOption
        );
        if(students.size() > 0)
            studentSelectOption = students.get(0).getId();
        else
            studentSelectOption = -1;

        studentGrades = filterStudentGrades();
        PrimeFaces.current().ajax().update(":form");
    }

    public void onBrigadeChange(){
        students = studentService.getStudentsByBrigadeCourseYearIds(
                brigadeSelectOption,
                courseSelectOption,
                yearSelectOption
        );
        if(students.size() > 0)
            studentSelectOption = students.get(0).getId();
        else
            studentSelectOption = -1;

        studentGrades = filterStudentGrades();
        PrimeFaces.current().ajax().update(":form");
    }

    public void onStudentChange(){
        studentGrades = filterStudentGrades();
        PrimeFaces.current().ajax().update(":form");
    }

    public List<StudentGradeDTO> getStudentGrades() {
        if(studentGrades == null) {
            brigades = brigadeService.findByYearId(yearSelectOption);
            if(brigades.size() > 0)
                brigadeSelectOption = brigades.get(0).getId();
            else
                brigadeSelectOption = -1;

            students = studentService.getStudentsByBrigadeCourseYearIds(
                    brigadeSelectOption,
                    courseSelectOption,
                    yearSelectOption
            );
            if(students.size() > 0)
                studentSelectOption = students.get(0).getId();
            else
                studentSelectOption = -1;

            studentGrades = filterStudentGrades();
        }

        return studentGrades;
    }

    public void openForEdit(){
        System.out.println("Abrir para editar");
        System.out.println(selectedStudentGrade.getStudentName());
    }

    public void saveStudentGrade() {
        System.out.println("Salvando nueva nota: " + selectedStudentGrade.getGradeValue());
        if (selectedStudentGrade.getGradeValue().equals("2") ||
                selectedStudentGrade.getGradeValue().equals("3") ||
                selectedStudentGrade.getGradeValue().equals("4") ||
                selectedStudentGrade.getGradeValue().equals("5")
        ){
            System.out.println("Esta bueno");
            studentGradeService.updateStudentGrade(new StudentGradeOnlyIdDTO(
                    selectedStudentGrade.getYearId(),
                    selectedStudentGrade.getStudentId(),
                    selectedStudentGrade.getSubjectId(),
                    courseSelectOption,
                    Integer.parseInt(selectedStudentGrade.getGradeValue())
            ));
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "edited_message_assign_student_grades");
            PrimeFaces.current().executeScript("PF('manageStudentGradeDialog').hide()");
            PrimeFaces.current().ajax().update(":form");
        }
        else{
            System.out.println("Esta malo");
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_FATAL, "error_message_assign_student_grades");
        }
    }

    public void setStudentGrades(List<StudentGradeDTO> studentGrades) {
        this.studentGrades = studentGrades;
    }

    public int getCourseSelectOption() {
        return courseSelectOption;
    }

    public void setCourseSelectOption(int courseSelectOption) {
        this.courseSelectOption = courseSelectOption;
    }

    public int getYearSelectOption() {
        return yearSelectOption;
    }

    public void setYearSelectOption(int yearSelectOption) {
        this.yearSelectOption = yearSelectOption;
    }

    public int getBrigadeSelectOption() {
        return brigadeSelectOption;
    }

    public void setBrigadeSelectOption(int brigadeSelectOption) {
        this.brigadeSelectOption = brigadeSelectOption;
    }

    public int getStudentSelectOption() {
        return studentSelectOption;
    }

    public void setStudentSelectOption(int studentSelectOption) {
        this.studentSelectOption = studentSelectOption;
    }

    public List<BrigadeDTO> getBrigades() {
        if(brigades == null)
            brigades = brigadeService.findByYearId(yearSelectOption);

        return brigades;
    }

    public void setBrigades(List<BrigadeDTO> brigades) {
        this.brigades = brigades;
    }

    public List<StudentDTO> getStudents() {
        if(students == null);
            students = studentService.getStudentsByBrigadeCourseYearIds(
                    brigadeSelectOption,
                    courseSelectOption,
                    yearSelectOption
            );

        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }

    public StudentGradeDTO getSelectedStudentGrade() {
        return selectedStudentGrade;
    }

    public void setSelectedStudentGrade(StudentGradeDTO selectedStudentGrade) {
        this.selectedStudentGrade = selectedStudentGrade;
    }
}