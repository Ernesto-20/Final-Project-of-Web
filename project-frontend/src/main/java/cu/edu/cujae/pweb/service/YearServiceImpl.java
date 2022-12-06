package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cu.edu.cujae.pweb.dto.CourseDTO;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import cu.edu.cujae.pweb.dto.StudentDTO;
import cu.edu.cujae.pweb.dto.YearDTO;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;

/* Esta anotiacioon le indica a spring que esta clase es un servicio y por tanto luego podr� inyectarse en otro lugar usando

 * @Autowired. En estas implementaciones luego se pondraan las llamadas al proyecto backend
 */
@Service
public class YearServiceImpl implements YearService {

	@Autowired
	private RestService restService;

	@Override
	public List<YearDTO> getYears() {

		List<YearDTO> years = new ArrayList<YearDTO>();

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<YearDTO> apiRestMapper = new ApiRestMapper<>();

			String response = (String) restService.GET("/api/v1/years", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
			years = apiRestMapper.mapList(response, YearDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return years;
	}

	@Override
	public void createYear(YearDTO year) {
		restService.POST("/api/v1/years", year, String.class).getBody();
	}

	@Override
	public void updateYear(YearDTO year) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		restService.PUT("/api/v1/years", params, year, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

	@Override
	public void deleteYear(Integer yearId) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/api/v1/years/{yearId}");
		String uri = template.expand(yearId.toString()).toString();
		restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

}
