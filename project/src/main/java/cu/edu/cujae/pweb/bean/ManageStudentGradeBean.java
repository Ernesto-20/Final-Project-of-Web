package cu.edu.cujae.pweb.bean;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.StudentGradeDTO;
import cu.edu.cujae.pweb.service.StudentService;
import cu.edu.cujae.pweb.utils.JsfUtils;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageStudentGradeBean {
	
	private StudentGradeDTO studentGradeDto;
	private StudentGradeDTO selectedStudentGrade;
	private List<StudentGradeDTO> studentGrades;

	
	/* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private StudentService studentService;
		
	
	public ManageStudentGradeBean() {
		
	}
	

	//Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase. 
	@PostConstruct
    public void init() {
	    studentGrades = studentGrades == null ? studentService.getStudents() : studentGrades;
		
    }
	
	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
        this.selectedStudentGrade = new StudentGradeDTO();
    }
	
	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
//		List<RoleDto> roles = this.selectedUser.getRoles();
//		this.selectedRoles = roles.stream().map(r -> r.getId()).toArray(Long[]::new);
	}
	
	//Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
	public void saveStudent() {
        if (this.selectedStudentGrade.getId() == null) {
            this.selectedStudentGrade.setId(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
            this.selectedStudentGrade.setNewRecord(true);
            
            this.studentGrades.add(this.selectedStudentGrade);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_edited");
        }

        PrimeFaces.current().executeScript("PF('manageStudentDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-studentGrades");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    }

	//Permite eliminar un usuario
    public void deleteStudent() {
    	try {
    		this.studentGrades.remove(this.selectedStudentGrade);
            this.selectedStudentGrade = null;
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_removed");
            PrimeFaces.current().ajax().update("form:dt-studentGrades");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
        
    }

	public StudentGradeDTO getStudentGradeDTO() {
		return studentGradeDto;
	}

	public void setStudentGradeDTO(StudentGradeDTO studentGradeDto) {
		this.studentGradeDto = studentGradeDto;
	}

	public StudentGradeDTO getSelectedStudent() {
		return selectedStudentGrade;
	}

	public void setSelectedStudent(StudentGradeDTO selectedStudentGrade) {
		this.selectedStudentGrade = selectedStudentGrade;
	}

	public List<StudentGradeDTO> getStudents() {
		return studentGrades;
	}

	public void setStudents(List<StudentGradeDTO> studentGrades) {
		this.studentGrades = studentGrades;
	}


}
