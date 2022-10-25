package cu.edu.cujae.pweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.StudentDto;

/* Esta anotiacioon le indica a spring que esta clase es un servicio y por tanto luego podr� inyectarse en otro lugar usando
 * @Autowired. En estas implementaciones luego se pondraan las llamadas al proyecto backend
 */
@Service
public class StudentServiceImpl implements StudentService{
	
	@Override
	public List<StudentDto> getStudents() {
		List<StudentDto> students = new ArrayList<>();
		students.add(new StudentDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "123455", "Arnaldo", "Rojas Fuentes", "M", "Regla","Promovido", false));
		students.add(new StudentDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "123455", "Perseo" , "Suarez Tamyo", "M", "Regla", "Promovido", false));
		students.add(new StudentDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "123455", "Sandor" , "Camejo Rayas", "M", "Guanabacoa", "Promovido", false));
		students.add(new StudentDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "123455", "Ronaldo", "Coas Saldivar", "F", "Playa", "Promovido", false));
		students.add(new StudentDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "123455", "Hector", "Ribas Traki", "F", "Alamar", "Promovido", false));
		students.add(new StudentDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "123455", "Camilo", "Estrada Lopez", "F", "Parra", "Promovido", false));
		students.add(new StudentDto(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "123455", "Facundo", "Romero Ramen", "F", "Regla", "Promovido", false));
		
		return students;
	}

	@Override
	public StudentDto getStudentById(String userId) {
		return getStudents().stream().filter(r -> r.getId().equals(userId)).findFirst().get();
	}

	@Override
	public void createStudent(StudentDto student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateStudent(StudentDto student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteStudent(String id) {
		// TODO Auto-generated method stub
		
	}
	
}
