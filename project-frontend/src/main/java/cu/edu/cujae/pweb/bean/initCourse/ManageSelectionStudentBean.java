package cu.edu.cujae.pweb.bean.initCourse;

import cu.edu.cujae.pweb.dto.StudentDTO;
import cu.edu.cujae.pweb.service.StudentService;
import cu.edu.cujae.pweb.utils.ValidateInput;
import org.primefaces.PrimeFaces;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
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
        initialize();
    }

    @PostConstruct
    public void init() {
        this.selectedStudent = new StudentDTO();
    }

    private void initialize() {
        selectedStudent = new StudentDTO();
        students = new ArrayList<>();
        studentsList = new ArrayList<>();
        studentsList.add(new ArrayList<>());
        studentsList.add(new ArrayList<>());
//        studentsList.add(new ArrayList<>());
//        studentsList.add(new ArrayList<>());
        currentIndex = 0;

//        HARDCODE
//        studentsList.get(0).add(new StudentDTO(0, "012", "Ernesto", "Fariña", "M", "habana", 0));
//        studentsList.get(0).add(new StudentDTO(1, "013", "Ernesto", "Fariña", "M", "habana", 0));
//        studentsList.get(0).add(new StudentDTO(2, "013", "Ernesto", "Fariña", "M", "habana", 0));

        studentsList.get(0).add(new StudentDTO(1, "014", "Ernesto", "Fariña", "M", "habana", 0));
        studentsList.get(0).add(new StudentDTO(1, "015", "Ernesto", "Fariña", "M", "habana", 0));
        studentsList.get(0).add(new StudentDTO(1, "016", "Ernesto", "Fariña", "M", "habana", 0));

        studentsList.get(1).add(new StudentDTO(2, "017", "Ernesto", "Fariña", "M", "habana", 0));
        studentsList.get(1).add(new StudentDTO(2, "018", "Ernesto", "Fariña", "M", "habana", 0));
        studentsList.get(1).add(new StudentDTO(2, "019", "Ernesto", "Fariña", "M", "habana", 0));
//
//        studentsList.get(3).add(new StudentDTO(9, "020", "Ernesto", "Fariña", "M", "habana", 0));
//        studentsList.get(3).add(new StudentDTO(10, "021", "Ernesto", "Fariña", "M", "habana", 0));
//        studentsList.get(3).add(new StudentDTO(11, "022", "Ernesto", "Fariña", "M", "habana", 0));
    }

    public void restore(){
        init();
        PrimeFaces.current().ajax().update("form:panelView");

    }

    public boolean isCorrect(){
        for(List<StudentDTO> studentDTOS: studentsList)
            if(studentDTOS.size() <= 0)
                return false;

        return true;
    }

    public void refresh(){
        this.selectedStudent = new StudentDTO();
        this.students.clear();
        this.studentsList.get(0).forEach(element -> students.add(element));
//        this.students = this.studentsList.get(index);
        this.currentIndex = 0;
//        PrimeFaces.current().ajax().update("form:panelView");
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

        PrimeFaces.current().ajax().update("dialog");
//        PrimeFaces.current().executeScript("PF('manageStudentDialog').show()");
    }

    public void openForEdit() {
        PrimeFaces.current().ajax().update("dialog");
//        PrimeFaces.current().executeScript("PF('manageStudentDialog').show()");
    }


    public void changeGroup(TabChangeEvent event){
        this.selectedStudent = new StudentDTO();
        int index = Integer.parseInt(event.getTab().getTitle().substring(event.getTab().getTitle().length()-1)) -1;

        this.students.clear();
        this.studentsList.get(index).forEach(element -> students.add(element));
        this.currentIndex = index;
        PrimeFaces.current().ajax().update("form:panelView");
    }

    public void saveStudent() {
        if(!ValidateInput.isName(this.selectedStudent.getFirstName())) {
            System.out.println("HERE");
            System.out.println(selectedStudent.getFirstName());

            FacesContext.getCurrentInstance().addMessage("firstName", new FacesMessage("hola cara de bola", "hola cabron"));
            System.out.println("HERE 2");
        }
//        if(isCorrectIdNum()){
//            if (this.selectedStudent.getId() == null) {
//                int idTemp = students.size() == 0 ? 0 : students.get(students.size() - 1).getId() + 1;
//                this.selectedStudent.setId(idTemp);
//                StudentDTO clone = getClone();
//
//                studentsList.get(currentIndex).add(clone);
//                this.students.clear();
//                this.studentsList.get(currentIndex).forEach(element -> students.add(element));
//
//
//            }
//            selectedStudent = new StudentDTO();
//
//            PrimeFaces.current().executeScript("PF('manageStudentDialog').hide()");
//            PrimeFaces.current().ajax().update("form:panelView");
//        }else{
//            System.out.println("mostrar mensaje de error");
//        }
    }

    private boolean isCorrectIdNum() {
        for(List<StudentDTO> brigade: studentsList)
            for(StudentDTO studentDTO: brigade)
                if( studentDTO.getIdNum().equals(this.selectedStudent.getIdNum()) && !studentDTO.getId().equals(this.selectedStudent.getId())) {
                    System.out.println("Este carne ya corresponde a un estudiante");
                    return false;
                }

        return true;
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
