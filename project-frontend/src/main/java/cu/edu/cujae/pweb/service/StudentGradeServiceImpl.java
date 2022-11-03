package cu.edu.cujae.pweb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.StudentGradeDTO;

/* Esta anotiacioon le indica a spring que esta clase es un servicio y por tanto luego podr� inyectarse en otro lugar usando
 * @Autowired. En estas implementaciones luego se pondraan las llamadas al proyecto backend
 */
@Service
public class StudentGradeServiceImpl implements StudentGradeService{
	
	@Override
	public List<StudentGradeDTO> getStudentGrades() {
		
		List<StudentGradeDTO> studentGrades = new ArrayList<>();
		studentGrades.add(new StudentGradeDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Carlos", "2020-2021", 3, "IA", 4, false));
		studentGrades.add(new StudentGradeDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Juan", "2020-2021" , 2, "DPOO", 3, false));
		studentGrades.add(new StudentGradeDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Luis", "2020-2021" , 1, "IP", 5, false));
		studentGrades.add(new StudentGradeDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "Pedro", "2020-2021", 3, "SI", 4, false));
		
		return studentGrades;
	}

	@Override
	public StudentGradeDTO getStudentById(String userId) {
		return getStudentGrades().stream().filter(r -> r.getId().equals(userId)).findFirst().get();
	}

//	@Override
//	public void createStudent(StudentGradeDTO student) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void updateStudent(StudentGradeDTO student) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deleteStudent(String id) {
//		// TODO Auto-generated method stub
//		
//	}
	
}
