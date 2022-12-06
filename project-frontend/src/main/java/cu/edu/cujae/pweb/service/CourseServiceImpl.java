package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import cu.edu.cujae.pweb.dto.StudentDTO;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.CourseDTO;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/* Esta anotiacioon le indica a spring que esta clase es un servicio y por tanto luego podr� inyectarse en otro lugar usando
 * @Autowired. En estas implementaciones luego se pondraan las llamadas al proyecto backend
 */
@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private RestService restService;
	
//	@Override
//	public List<CourseDTO> getCourses() {
//
//		List<CourseDTO> courses = new ArrayList<>();
//		courses.add(new CourseDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), 2013, 2014, false));
//		courses.add(new CourseDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), 2014, 2015, false));
//		courses.add(new CourseDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), 2015, 2016, false));
//
//		return courses;
//	}

	@Override
	public List<CourseDTO> getCourses() {

		List<CourseDTO> courses = new ArrayList<>();

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<CourseDTO> apiRestMapper = new ApiRestMapper<>();

			String response = (String) restService.GET("/api/v1/courses", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
			courses = apiRestMapper.mapList(response, CourseDTO.class);
		} catch (
				IOException e) {
			e.printStackTrace();
		}
		return courses;
	}
	
	@Override
	public Map<String,String> getCourses2() {
		Map<String,String> courses2 = new HashMap<>();
		courses2.put("2019-2020", "2019-2020");
		courses2.put("2020-2021", "2020-2021");
		courses2.put("2021-2022", "2021-2022");
		
		return courses2;
	}
	@Override
	public CourseDTO getCourseById(String userId) {
		return getCourses().stream().filter(r -> r.getId().equals(userId)).findFirst().get();
	}

	@Override
	public void createCourse(CourseDTO course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCourse(CourseDTO course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCourse(String id) {
		// TODO Auto-generated method stub
		
	}
	
}
