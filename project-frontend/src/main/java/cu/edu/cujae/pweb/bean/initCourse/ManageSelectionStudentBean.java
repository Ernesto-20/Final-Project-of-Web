package cu.edu.cujae.pweb.bean.initCourse;

import cu.edu.cujae.pweb.dto.StudentDTO;
import cu.edu.cujae.pweb.service.StudentService;
import org.primefaces.PrimeFaces;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;

@Component
@ManagedBean
@ViewScoped
public class ManageSelectionStudentBean {

    private StudentDTO studentDTO;
    private StudentDTO selectedStudent;
    private List<StudentDTO> students;

    private List<List<StudentDTO>> studentsList;
    private int currentIndex;


    @Autowired
    private StudentService studentService;

    public ManageSelectionStudentBean() {
        init();
    }

    private void init() {
        selectedStudent = new StudentDTO();
        students = new ArrayList<>();
        studentsList = new ArrayList<>();
        studentsList.add(new ArrayList<>());
        studentsList.add(new ArrayList<>());
        studentsList.add(new ArrayList<>());
        studentsList.add(new ArrayList<>());
        currentIndex = 0;
    }

    public void restore(){
        init();
        PrimeFaces.current().ajax().update("form:panelView");

    }

    public boolean iCorrect(){
        for(List<StudentDTO> studentDTOS: studentsList)
            if(studentDTOS.size() <= 0)
                return false;

        return true;
    }

    public void refresh(){
        this.students.clear();
        this.studentsList.get(0).forEach(element -> students.add(element));
//        this.students = this.studentsList.get(index);
        this.currentIndex = 0;
        PrimeFaces.current().ajax().update("form:panelView");
    }

    public String index(List<StudentDTO> brigade){
        for(int i=0; i<studentsList.size(); i++){
            if(studentsList.get(i) == brigade){
                return i+"";
            }
        }

        return -1+"";
    }

    public void openNew() {
        this.selectedStudent = new StudentDTO();
    }

    public void openForEdit() {
    }

    public void changeGroup(TabChangeEvent event){
        this.selectedStudent = new StudentDTO();
        int index = Integer.parseInt(event.getTab().getTitle().substring(event.getTab().getTitle().length()-1)) -1;
//        List<StudentDTO> listClone = new ArrayList<>();
//        students.forEach((element) -> {listClone.add(element);} );

//        this.studentsList.set(currentIndex, listClone);

        this.students.clear();
        this.studentsList.get(index).forEach(element -> students.add(element));
//        this.students = this.studentsList.get(index);
        this.currentIndex = index;
        PrimeFaces.current().ajax().update("form:panelView");
    }

    public void saveStudent() {
        if (this.selectedStudent.getId() == null) {
            int idTemp = students.size() == 0 ? 0: students.get(students.size()-1).getId()+1;
            this.selectedStudent.setId(idTemp);
            StudentDTO clone = getClone();

            studentsList.get(currentIndex).add(clone);
            this.students.clear();
            this.studentsList.get(currentIndex).forEach(element -> students.add(element));


//            studentService.createStudent(this.selectedStudent);
//            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_student_added");
        } else {
//            for(int i=0; i<students.size(); i++){
//                if(students.get(i).getId() == this.selectedStudent.getId()){
//                    System.out.println(students.get(i) == this.selectedStudent);
//                    students.set(i, getClone());
//
//                    studentsList.get(currentIndex).set(i,getClone());
//                    this.students.clear();
//                    this.studentsList.get(currentIndex).forEach(element -> students.add(element));
//
//                    break;
//                }
//            }
//            studentService.updateStudent(this.selectedStudent);
//
//            this.selectedStudent = new StudentDTO();
//            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_student_edited");
        }
//        students = studentService.getStudentsByBrigadeCourseYearIds(this.brigade, this.course, this.year);
//
        selectedStudent = new StudentDTO();
        PrimeFaces.current().executeScript("PF('manageStudentDialog').hide()");
        PrimeFaces.current().ajax().update("form:panelView");
    }

    public StudentDTO getClone(){
         return new StudentDTO(selectedStudent.getId(), selectedStudent.getIdNum(), selectedStudent.getFirstName(), selectedStudent.getLastName(),
                selectedStudent.getGender(), selectedStudent.getMunicipality(), selectedStudent.getStatusID());
    }

    public void cancel() {
        this.selectedStudent = new StudentDTO();
    }

    public void deleteStudent() {
        int removeIndex = -1;
        for(int i=0; i<studentsList.get(currentIndex).size(); i++)
            if (studentsList.get(currentIndex).get(i).getId().equals(selectedStudent.getId())) {
                removeIndex = i;
            }
        studentsList.get(currentIndex).remove(removeIndex);
        students.clear();
        this.studentsList.get(currentIndex).forEach(element -> students.add(element));

        PrimeFaces.current().ajax().update("form:panelView");
    }

    public List<List<StudentDTO>> getStudentsList() {
        return studentsList;
    }

    public void setStudentsList(List<List<StudentDTO>> studentsList) {
        this.studentsList = studentsList;
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }

    public StudentDTO getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(StudentDTO selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}
