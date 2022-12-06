package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import cu.edu.cujae.pweb.dto.CourseDTO;
import cu.edu.cujae.pweb.dto.StudentDTO;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;

/* Esta anotiacioon le indica a spring que esta clase es un servicio y por tanto luego podr� inyectarse en otro lugar usando
 * @Autowired. En estas implementaciones luego se pondraan las llamadas al proyecto backend
 */
@Service
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private RestService restService;
	
	@Override
	public List<CourseDTO> getCourses() {
		
		List<CourseDTO> courses = new ArrayList<CourseDTO>();
		
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<CourseDTO> apiRestMapper = new ApiRestMapper<>();

			String response = (String) restService.GET("/api/v1/courses", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
			courses = apiRestMapper.mapList(response, CourseDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return courses;
	}

	@Override
	public CourseDTO getCourseById(Integer courseId) {
		CourseDTO course = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<CourseDTO> apiRestMapper = new ApiRestMapper<>();

			UriTemplate template = new UriTemplate("/api/v1/courses/{courseId}");
			String uri = template.expand(courseId.toString()).toString();
			String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
			course = apiRestMapper.mapOne(response, CourseDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return course;
	}

	@Override
	public void createCourse(CourseDTO course) {
		restService.POST("/api/v1/courses", course, String.class).getBody();
	}

	@Override
	public void updateCourse(CourseDTO course) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		restService.PUT("/api/v1/courses", params, course, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

	@Override
	public void deleteCourse(Integer courseId) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/api/v1/courses/{courseId}");
		String uri = template.expand(courseId.toString()).toString();
		restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}
}
