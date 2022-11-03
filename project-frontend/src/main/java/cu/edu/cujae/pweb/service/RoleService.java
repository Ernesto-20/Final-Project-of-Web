package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.RoleDto;

import java.util.List;

public interface RoleService {
	List<RoleDto> getRoles();
	List<RoleDto> getRolesByUser(String userId);
	RoleDto getRolesById(Long roleId);
}
