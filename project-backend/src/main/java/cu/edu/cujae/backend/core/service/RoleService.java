package cu.edu.cujae.backend.core.service;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.RoleDTO;

public interface RoleService {
	
	List<RoleDTO> getRolesByUserId(String userId) throws SQLException;
	
	List<RoleDTO> listRoles() throws SQLException;
	
	RoleDTO getRoleById(Long roleId) throws SQLException;
	
}
