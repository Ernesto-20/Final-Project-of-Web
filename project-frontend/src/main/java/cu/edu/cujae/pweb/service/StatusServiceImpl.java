package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import cu.edu.cujae.pweb.dto.StatusDTO;

/* Esta anotación le indica a spring que esta clase es un servicio y por tanto luego podrá inyectarse en otro lugar usando

 * @Autowired. En estas implementaciones luego se pondrán las llamadas al proyecto backend
 */
@Service
public class StatusServiceImpl implements StatusService {
	@Autowired
	private RestService restService;

	//

	@Override
	public List<StatusDTO> getStatus() {

		List<StatusDTO> status = new ArrayList<StatusDTO>();

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<StatusDTO> apiRestMapper = new ApiRestMapper<>();

			String response = (String) restService.GET("/api/v1/status", params, String.class).getBody();
			status = apiRestMapper.mapList(response, StatusDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public StatusDTO getStatusById(Integer statusId) {
		StatusDTO status = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<StatusDTO> apiRestMapper = new ApiRestMapper<>();

			UriTemplate template = new UriTemplate("/api/v1/status/{statusId}");
			String uri = template.expand(statusId.toString()).toString();
			String response = (String) restService.GET(uri, params, String.class).getBody();
			status = apiRestMapper.mapOne(response, StatusDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return status;
	}

//! ESTO PUEDE DAR BATEO
	@Override
	public StatusDTO getStatusByDescription(String description) {
		StatusDTO status = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("description", description);
			ApiRestMapper<StatusDTO> apiRestMapper = new ApiRestMapper<>();

			String response = (String) restService.GET("/api/v1/status", params, String.class).getBody();
			status = apiRestMapper.mapOne(response, StatusDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return status;
	}

	@Override
	public void createStatus(StatusDTO status) {
		restService.POST("/api/v1/status", status, String.class).getBody();
	}

	@Override
	public void updateStatus(StatusDTO status) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		restService.PUT("/api/v1/status", params, status, String.class).getBody();
	}

	@Override
	public void deleteStatus(Integer statusId) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/api/v1/status/{statusId}");
		String uri = template.expand(statusId.toString()).toString();
		restService.DELETE(uri, params, String.class, null).getBody();
	}

}
