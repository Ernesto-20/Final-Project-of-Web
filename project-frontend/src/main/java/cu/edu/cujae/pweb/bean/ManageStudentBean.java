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

import cu.edu.cujae.pweb.dto.StudentDTO;
import cu.edu.cujae.pweb.service.StudentService;
import cu.edu.cujae.pweb.utils.JsfUtils;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageStudentBean {
	
	private StudentDTO studentDTO;
	private StudentDTO selectedStudent;
	private List<StudentDTO> students;

	
	/* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private StudentService studentService;
		
	
	public ManageStudentBean() {
		
	}
	

	//Esta anotacioon permite que se ejecute code luego de haberse ejecuta el constructor de la clase. 
	@PostConstruct
    public void init() {
	    students = students == null ? studentService.getStudents() : students;
		
    }
	
	//Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
        this.selectedStudent = new StudentDTO();
    }
	
	//Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
//		List<RoleDto> roles = this.selectedUser.getRoles();
//		this.selectedRoles = roles.stream().map(r -> r.getId()).toArray(Long[]::new);
	}
	
	//Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar al usuario
	public void saveStudent() {
        if (this.selectedStudent.getId() == null) {
            this.selectedStudent.setId(Integer.valueOf(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9)));
            
            this.students.add(this.selectedStudent);
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_added"); //Este code permite mostrar un mensaje exitoso (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de recursos, con la llave message_user_added
        }
        else {
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_edited");
        }

        PrimeFaces.current().executeScript("PF('manageStudentDialog').hide()");//Este code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador es el widgetVar
        PrimeFaces.current().ajax().update("form:dt-students");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
    }

	//Permite eliminar un usuario
    public void deleteStudent() {
    	try {
    		this.students.remove(this.selectedStudent);
            this.selectedStudent = null;
            JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_removed");
            PrimeFaces.current().ajax().update("form:dt-students");// Este code es para refrescar el componente con id dt-users que se encuentra dentro del formulario con id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}
        
    }

	public StudentDTO getStudentDto() {
		return studentDTO;
	}

	public void setStudentDto(StudentDTO studentDTO) {
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


}
