package cu.edu.cujae.pweb.service;



import cu.edu.cujae.pweb.dto.UserDTO;

import java.util.List;

public interface UserService {
	List<UserDTO> getUsers();
	UserDTO getUserById(String userId);
	void createUser(UserDTO user);
	void updateUser(UserDTO user);
	void deleteUser(String id);
}
