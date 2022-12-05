package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.RoleDTO;
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
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RestService restService;

	@Override
	public List<RoleDTO> getRoles() {
		List<RoleDTO> roleList = new ArrayList<RoleDTO>();
	    try {
	    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<RoleDTO> apiRestMapper = new ApiRestMapper<>();
		    String response = (String)restService.GET("/api/v1/roles", params, String.class).getBody();
		    roleList = apiRestMapper.mapList(response, RoleDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return roleList;
	}

	@Override
	public List<RoleDTO> getRolesByUserId(String userId) {
		List<RoleDTO> roleList = new ArrayList<RoleDTO>();
	    try {
	    	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<RoleDTO> apiRestMapper = new ApiRestMapper<>();
		    
		    UriTemplate template = new UriTemplate("/api/v1/roles/users/{userId}");
		    String uri = template.expand(userId).toString();
		    
		    String response = (String)restService.GET(uri, params, String.class).getBody();
		    roleList = apiRestMapper.mapList(response, RoleDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return roleList;
	}

	@Override
	public RoleDTO getRolesById(Long roleId) {
		RoleDTO role = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		    ApiRestMapper<RoleDTO> apiRestMapper = new ApiRestMapper<>();
		    
		    UriTemplate template = new UriTemplate("/api/v1/roles/{id}");
		    String uri = template.expand(roleId).toString();
		    String response = (String)restService.GET(uri, params, String.class).getBody();
		    role = apiRestMapper.mapOne(response, RoleDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return role;
	}

}
