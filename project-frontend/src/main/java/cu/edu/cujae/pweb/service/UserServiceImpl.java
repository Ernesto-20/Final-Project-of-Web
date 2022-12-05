package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.UserDTO;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private RestService restService;

	@Override
	public List<UserDTO> getUsers() {
		List<UserDTO> userList = new ArrayList<UserDTO>();
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<UserDTO> apiRestMapper = new ApiRestMapper<>();
			String response = (String) restService
					.GET("/api/v1/users", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
			userList = apiRestMapper.mapList(response, UserDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public UserDTO getUserById(String userId) {
		UserDTO user = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<UserDTO> apiRestMapper = new ApiRestMapper<>();

			UriTemplate template = new UriTemplate("/api/v1/users/{userId}");
			String uri = template.expand(userId).toString();
			String response = (String) restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer())
					.getBody();
			user = apiRestMapper.mapOne(response, UserDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
	}

	@Override
	public void createUser(UserDTO user) {
		restService.POST("/api/v1/users", user, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

	@Override
	public void updateUser(UserDTO user) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		restService.PUT("/api/v1/users", params, user, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

	@Override
	public void deleteUser(String userId) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		UriTemplate template = new UriTemplate("/api/v1/users/{userId}");
		String uri = template.expand(userId).toString();
		restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

}
