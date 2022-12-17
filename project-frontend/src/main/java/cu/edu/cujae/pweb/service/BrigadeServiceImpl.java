package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import cu.edu.cujae.pweb.dto.BrigadeDTO;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;

/* Esta anotiacioon le indica a spring que esta clase es un servicio y por tanto luego podr� inyectarse en otro lugar usando
 * @Autowired. En estas implementaciones luego se pondraan las llamadas al proyecto backend
 */
@Service
public class BrigadeServiceImpl implements BrigadeService {

	@Autowired
	private RestService restService;

	@Override
	public List<BrigadeDTO> findAll() {

		List<BrigadeDTO> brigades = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<BrigadeDTO> apiRestMapper = new ApiRestMapper<>();

			String response = (String) restService
					.GET("/api/v1/brigades", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
			brigades = apiRestMapper.mapList(response, BrigadeDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return brigades;
	}

	@Override
	public BrigadeDTO findById(Integer brigadeId) {
		BrigadeDTO brigade = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<BrigadeDTO> apiRestMapper = new ApiRestMapper<>();

			UriTemplate template = new UriTemplate("/api/v1/brigades/{brigadeId}");
			String uri = template.expand(brigadeId.toString()).toString();
			String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer())
					.getBody();
			brigade = apiRestMapper.mapOne(response, BrigadeDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return brigade;
	}

	@Override
	public void insert(BrigadeDTO brigade) {
		restService.POST("/api/v1/brigades", brigade, String.class).getBody();
	}

	@Override
	public void update(BrigadeDTO brigade) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		restService.PUT("/api/v1/brigades", params, brigade, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

	@Override
	public void delete(Integer brigadeId) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/api/v1/brigades/{brigadeId}");
		String uri = template.expand(brigadeId.toString()).toString();
		restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

	@Override
	public List<BrigadeDTO> findByYearId(Integer yearId) {
		List<BrigadeDTO> brigades = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<BrigadeDTO> apiRestMapper = new ApiRestMapper<>();

			UriTemplate template = new UriTemplate("/api/v1/brigades/year/{yearId}");
			String uri = template.expand(yearId).toString();
			String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
			brigades = apiRestMapper.mapList(response, BrigadeDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return brigades;
	}
}
