package cu.edu.cujae.pweb.bean;

import cu.edu.cujae.pweb.dto.*;
import cu.edu.cujae.pweb.service.*;
import cu.edu.cujae.pweb.utils.JsfUtils;
import cu.edu.cujae.pweb.utils.RankingComparator;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
@ManagedBean
@ViewScoped
public class ManageRankingBean {

    private List<StudentGradeCourseIdDTO> studentGrades;
    private List<StudentRankingDTO> studentsRanking;
    private List<CourseDTO> courses;
    private int courseSelectOption = 1;
    private int yearSelectOption = 1;

    @Autowired
    private StudentGradeService studentGradeService;

    @Autowired
    private StudentInBrigadeService studentInBrigadeService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    public ManageRankingBean() {

    }

    public void onCourseOrYearChange(){
        List<Integer> studentsIds = studentInBrigadeService.getStudentsIdsByCourseAndYearId(courseSelectOption, yearSelectOption);
        System.out.println("Showing student ids");
        for (Integer id : studentsIds)
            System.out.println(id);
        System.out.println("Finished showing");
        List<StudentGradeCourseIdDTO> studentsGrade = studentGradeService.getStudentGradesAllCourseId().stream().filter(sg -> isAllowedCourse(sg.getCourseId()) && isSearchedStudent(sg.getStudentId(), studentsIds)).collect(Collectors.toList());
        studentsRanking = new ArrayList<>();

        HashMap<Integer, List<Integer>> gradesByStudent = new HashMap<>();

        for(StudentGradeCourseIdDTO studentGrade : studentsGrade){
            if(gradesByStudent.containsKey(studentGrade.getStudentId())){
                gradesByStudent.get(studentGrade.getStudentId()).add(studentGrade.getGradeValue());
            }
            else{
                gradesByStudent.put(studentGrade.getStudentId(), new ArrayList<Integer>());
                gradesByStudent.get(studentGrade.getStudentId()).add(studentGrade.getGradeValue());
            }
        }

        for(Integer studentId : gradesByStudent.keySet()){
            System.out.println("Para estudiante: " + studentId);
            List<Integer> grades = gradesByStudent.get(studentId);

            double average = 0;

            for(Integer grade : grades) {
                System.out.println("Nota: " + grade);
                average += grade;
            }

            average = average / grades.size();
            System.out.println("Grade Size: " + grades.size());

            studentsRanking.add(new StudentRankingDTO(studentId, studentService.getStudentById(studentId).getFullName(), average));
        }

        studentsRanking.sort(new RankingComparator());
        PrimeFaces.current().ajax().update(":dt-students");
    }

    public List<StudentGradeCourseIdDTO> getStudentGrades() {
//        if(studentGrades == null)
//            studentGrades = studentGradeService.getStudentGradesAllCourseId().stream().filter(studentGrade -> studentGrade.);

        return studentGrades;
    }

    public void setStudentGrades(List<StudentGradeCourseIdDTO> studentGrades) {
        this.studentGrades = studentGrades;
    }

    public List<StudentRankingDTO> getStudentsRanking() {
        if(studentsRanking == null) {
            onCourseOrYearChange();
        }

        return studentsRanking;
    }

    public void setStudentsRanking(List<StudentRankingDTO> studentsRanking) {
        this.studentsRanking = studentsRanking;
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

    public List<CourseDTO> getCourses() {
        if(courses == null)
            courses = courseService.getCourses();

        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }

    public boolean isAllowedCourse(int courseId){
        return courseId <= courseSelectOption;
    }

    public boolean isSearchedStudent(int studentId, List<Integer> studentIds){

        for(Integer id : studentIds)
            if(id == studentId)
                return true;

        return false;
    }
}