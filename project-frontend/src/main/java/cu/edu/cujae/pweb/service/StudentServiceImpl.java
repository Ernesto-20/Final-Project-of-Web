package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import cu.edu.cujae.pweb.dto.StudentDTO;
import cu.edu.cujae.pweb.dto.UserDto;

/* Esta anotiacioon le indica a spring que esta clase es un servicio y por tanto luego podr� inyectarse en otro lugar usando

 * @Autowired. En estas implementaciones luego se pondraan las llamadas al proyecto backend
 */
@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private RestService restService;

	//

	@Override
	public List<StudentDTO> getStudents() {

		List<StudentDTO> students = new ArrayList<StudentDTO>();

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<StudentDTO> apiRestMapper = new ApiRestMapper<>();

			String response = (String) restService.GET("/api/v1/students", params, String.class).getBody();
			students = apiRestMapper.mapList(response, StudentDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return students;
	}

	@Override
	public StudentDTO getStudentById(Integer studentId) {
		StudentDTO student = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<StudentDTO> apiRestMapper = new ApiRestMapper<>();

			UriTemplate template = new UriTemplate("/api/v1/students/{studentId}");
			String uri = template.expand(studentId.toString()).toString();
			String response = (String) restService.GET(uri, params, String.class).getBody();
			student = apiRestMapper.mapOne(response, StudentDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return student;
	}

	@Override
	public StudentDTO getStudentByIdNum(String studentIdNum) {
		StudentDTO student = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<StudentDTO> apiRestMapper = new ApiRestMapper<>();

			UriTemplate template = new UriTemplate("/api/v1/students/idnum/{studentIdNum}");
			String uri = template.expand(studentIdNum).toString();
			String response = (String) restService.GET(uri, params, String.class).getBody();
			student = apiRestMapper.mapOne(response, StudentDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return student;
	}

	@Override
	public void createStudent(StudentDTO student) {
		restService.POST("/api/v1/students", student, String.class).getBody();
	}

	@Override
	public void updateStudent(StudentDTO student) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		restService.PUT("/api/v1/students", params, student, String.class).getBody();
	}

	@Override
	public void deleteStudent(Integer studentId) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/api/v1/students/{studentId}");
		String uri = template.expand(studentId.toString()).toString();
		restService.DELETE(uri, params, String.class, null).getBody();
	}

}
