package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.RoleDTO;

import java.util.List;

public interface RoleService {
	List<RoleDTO> getRoles();
	List<RoleDTO> getRolesByUserId(String userId);
	RoleDTO getRolesById(Long roleId);
}
