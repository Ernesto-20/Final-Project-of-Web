package cu.edu.cujae.backend.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.UserDTO;

public interface UserService {
	
	void createUser(UserDTO user) throws SQLException;
	
	void updateUser(UserDTO user) throws SQLException;
	
	List<UserDTO> listUsers() throws SQLException;
	
	UserDTO getUserById(String userId) throws SQLException;
	
	UserDTO getUserByUsername(String username) throws SQLException;
	
	void deleteUser(String id) throws SQLException;
}
