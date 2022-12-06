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

import cu.edu.cujae.pweb.dto.StudentGradeDTO;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;

/* Esta anotación le indica a spring que esta clase es un servicio y por tanto luego podra inyectarse en otro lugar usando
 * @Autowired. En estas implementaciones luego se pondrán las llamadas al proyecto backend
 */
@Service
public class StudentGradeServiceImpl implements StudentGradeService {

	@Autowired
	private RestService restService;

	@Override
	public List<StudentGradeDTO> getStudentGradesByYearId(Integer studentId, Integer yearId) {

		List<StudentGradeDTO> studentGrades = new ArrayList<>();

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("studentId", studentId.toString());
			params.add("yearId", yearId.toString());
			ApiRestMapper<StudentGradeDTO> apiRestMapper = new ApiRestMapper<>();

			UriTemplate template = new UriTemplate("/api/v1/studentgrades/");
			String uri = template.expand(studentId.toString()).toString();
			String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer())
					.getBody();
			studentGrades = apiRestMapper.mapList(response, StudentGradeDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return studentGrades;
	}
}
