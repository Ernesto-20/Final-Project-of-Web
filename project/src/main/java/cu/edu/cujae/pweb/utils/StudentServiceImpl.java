package cu.edu.cujae.pweb.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import cu.edu.cujae.backend.core.dto.StudentDTO;

/* Esta anotiacioon le indica a spring que esta clase es un servicio y por tanto luego podr� inyectarse en otro lugar usando

 * @Autowired. En estas implementaciones luego se pondraan las llamadas al proyecto backend
 */
@Service
public class StudentServiceImpl implements StudentService{
	
//	@Override
//	public List<StudentDTO> getStudents() {
//		
//		
//		List<StudentDTO> students = new ArrayList<>();
//		students.add(new StudentDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "123455", "Arnaldo", "Rojas Fuentes", "M", "Regla","Promovido", false));
//		students.add(new StudentDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "123455", "Perseo" , "Suarez Tamyo", "M", "Regla", "Promovido", false));
//		students.add(new StudentDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "123455", "Sandor" , "Camejo Rayas", "M", "Guanabacoa", "Promovido", false));
//		students.add(new StudentDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "123455", "Ronaldo", "Coas Saldivar", "F", "Playa", "Promovido", false));
//		students.add(new StudentDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "123455", "Hector", "Ribas Traki", "F", "Alamar", "Promovido", false));
//		students.add(new StudentDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "123455", "Camilo", "Estrada Lopez", "F", "Parra", "Promovido", false));
//		students.add(new StudentDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "123455", "Facundo", "Romero Ramen", "F", "Regla", "Promovido", false));
//		
//		return students;
//	}
	
	@Override
	public List<StudentDTO> getStudents() {
		
		List<StudentDTO> students = null;
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		ApiRestMapper<StudentDTO> apiRestMapper = new ApiRestMapper<>();
		
//		String response = (String)restService.GET("/api/v1/students", params, String.class).getBody();
//		students = apiRestMapper.mapList(response, StudentDTO.class);
		
		return students;
	}

	@Override
	public StudentDTO getStudentById(String studentId) {
		StudentDTO student = null;
		
		try {
//			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//			ApiRestMapper<StudentDTO> apiRestMapper = new ApiRestMapper<>();
			
//			UriTemplate template = new UriTemplate("/api/v1/students/{studentId}");
//			String uri = template.expand("studentId", studentId).toString();
//			String response = (String)restService.GET(uri, params, String.class).getBody();
//			student = apiRestMapper.mapOne(response, StudentDTO.class);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return student;
	}

//	@Override
//	public StudentDTO getStudentById(String userId) {
//		return getStudents().stream().filter(r -> r.getId().equals(userId)).findFirst().get();
//	}
	@Override
	public void createStudent(StudentDTO student) {
		// TODO Auto-generated method stub
//		restService.POST("/api/v1/students", student, String.class, null).getBody();
	}

	@Override
	public void updateStudent(StudentDTO student) {
//		restService.PUT("/api/v1/students", student, String.class, null).getBody();
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteStudent(String id) {
		// TODO Auto-generated method stub
		
	}
	
}
