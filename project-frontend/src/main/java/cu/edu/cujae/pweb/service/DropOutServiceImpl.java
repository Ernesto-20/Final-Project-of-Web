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

import cu.edu.cujae.pweb.dto.DropOutDTO;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;

/* Esta anotación le indica a spring que esta clase es un servicio y por tanto luego podra inyectarse en otro lugar usando
 * @Autowired. En estas implementaciones luego se pondrán las llamadas al proyecto backend
 */
@Service
public class DropOutServiceImpl implements DropOutService {

	@Autowired
	private RestService restService;

	@Override
	public List<DropOutDTO> getDropOuts() {

		List<DropOutDTO> dropouts = new ArrayList<>();

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<DropOutDTO> apiRestMapper = new ApiRestMapper<>();

			String response = (String) restService.GET("/api/v1/dropout/", params, String.class, CurrentUserUtils.getTokenBearer())
					.getBody();
			
			dropouts = apiRestMapper.mapList(response, DropOutDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dropouts;
	}
}
