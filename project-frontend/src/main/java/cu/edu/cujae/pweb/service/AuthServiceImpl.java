package cu.edu.cujae.pweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.LoginRequestDTO;
import cu.edu.cujae.pweb.dto.UserAuthenticatedDTO;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private RestService restService;

	@Override
	public UserAuthenticatedDTO login(String username, String password) {
		UserAuthenticatedDTO authenticatedDto = null;
		try {
			ApiRestMapper<UserAuthenticatedDTO> apiRestMapper = new ApiRestMapper<>();
			String response = (String) restService
					.POST("/api/v1/auth/login", new LoginRequestDTO(username, password), String.class).getBody();
			authenticatedDto = apiRestMapper.mapOne(response, UserAuthenticatedDTO.class);
		} catch (Exception e) {
			authenticatedDto = null;
		}
		return authenticatedDto;
	}

}
