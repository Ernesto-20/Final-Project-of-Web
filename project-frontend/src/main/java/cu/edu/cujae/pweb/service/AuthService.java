package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.UserAuthenticatedDTO;

public interface AuthService {
	
	UserAuthenticatedDTO login(String username, String password);
	
}
