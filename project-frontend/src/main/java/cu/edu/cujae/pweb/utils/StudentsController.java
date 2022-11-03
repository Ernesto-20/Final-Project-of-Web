package cu.edu.cujae.pweb.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import cu.edu.cujae.backend.core.dto.StudentDTO;
import cu.edu.cujae.backend.core.service.StudentService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})

@RequestMapping("/api/v1/students")
public class StudentsController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/")
    public ResponseEntity<List<StudentDTO>> getStudents() {
		List<StudentDTO> students = new ArrayList<StudentDTO>();
		students = studentService.getStudents();
		
        return ResponseEntity.ok(students);
    }
//	@GetMapping("/")
//    public ResponseEntity<List<StudentDTO>> getStudents() {
//		List<StudentDTO> students = new ArrayList<StudentDTO>();
//		students.add(new StudentDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "123455", "Arnaldo", "Rojas Fuentes", "M", "Regla","Promovido", false));
//		students.add(new StudentDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "123455", "Perseo" , "Suarez Tamyo", "M", "Regla", "Promovido", false));
//		students.add(new StudentDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "123455", "Sandor" , "Camejo Rayas", "M", "Guanabacoa", "Promovido", false));
//		students.add(new StudentDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "123455", "Ronaldo", "Coas Saldivar", "F", "Playa", "Promovido", false));
//		students.add(new StudentDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "123455", "Hector", "Ribas Traki", "F", "Alamar", "Promovido", false));
//		students.add(new StudentDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "123455", "Camilo", "Estrada Lopez", "F", "Parra", "Promovido", false));
//		students.add(new StudentDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "123455", "Facundo", "Romero Ramen", "F", "Regla", "Promovido", false));
//		
//        return ResponseEntity.ok(students);
//    }
	
	@GetMapping("/{studentId}")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable String studentId) {
		StudentDTO student = studentService.getStudentById(studentId);
		
		if(student == null) {
			throw new RuntimeException("Student id not found -"+studentId);
		}
        return ResponseEntity.ok(student);
    }
	
	@PostMapping("/")
	public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO student) {
		
		student.setId("0");
		studentService.createStudent(student);
		
		return ResponseEntity.ok(student); 
    }
	
	@PutMapping("/")
	public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO student) {
		studentService.updateStudent(student);
		return ResponseEntity.ok(student);
    }
	
	@DeleteMapping("/{studentId}")
	public ResponseEntity<String> deleteStudent(@PathVariable String studentId) {
		StudentDTO student = studentService.getStudentById(studentId);
		
		if(student == null) {
			throw new RuntimeException("Student id not found -"+studentId);
		}
		
		studentService.deleteStudent(studentId);
		
        return ResponseEntity.ok("Deleted student id - " + studentId);
    }
	
}
