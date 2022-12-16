package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.security.CurrentUserUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import cu.edu.cujae.pweb.dto.StudentDropOutDTO;
import cu.edu.cujae.pweb.dto.StudentDropOutNamedDTO;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;

/* Esta anotación le indica a spring que esta clase es un servicio y por tanto luego podra inyectarse en otro lugar usando
 * @Autowired. En estas implementaciones luego se pondrán las llamadas al proyecto backend
 */
@Service
public class StudentDropOutServiceImpl implements StudentDropOutService {

	@Autowired
	private RestService restService;

	@Override
	public List<StudentDropOutDTO> getStudentDropOuts() {

		List<StudentDropOutDTO> studentDropOut = new ArrayList<>();

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<StudentDropOutDTO> apiRestMapper = new ApiRestMapper<>();

			String response = (String) restService.GET("/api/v1/studentdropouts/", params, String.class, CurrentUserUtils.getTokenBearer())
					.getBody();
			
			studentDropOut = apiRestMapper.mapList(response, StudentDropOutDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return studentDropOut;
	}
	
	@Override
	public List<StudentDropOutNamedDTO> getNamedStudentDropOutByCourseId(Integer courseId) {

		List<StudentDropOutNamedDTO> studentDropOut = new ArrayList<>();

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<StudentDropOutNamedDTO> apiRestMapper = new ApiRestMapper<>();
			
			UriTemplate template = new UriTemplate("/api/v1/studentdropouts/named/course/{courseId}");
			String uri = template.expand(courseId.toString()).toString();

			String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer())
					.getBody();
			
			studentDropOut = apiRestMapper.mapList(response, StudentDropOutNamedDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return studentDropOut;
	}

	@Override
	public void createStudentDropOut(StudentDropOutDTO studentDropOut) {
		restService.POST("/api/v1/studentdropouts", studentDropOut, String.class, CurrentUserUtils.getTokenBearer()).getBody();
		
	}
}
