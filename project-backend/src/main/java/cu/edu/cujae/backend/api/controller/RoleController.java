package cu.edu.cujae.backend.api.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cu.edu.cujae.backend.core.dto.RoleDTO;
import cu.edu.cujae.backend.core.service.RoleService;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("")
    public ResponseEntity<List<RoleDTO>> getRoles() throws SQLException {
		List<RoleDTO> roleList = roleService.listRoles();
        return ResponseEntity.ok(roleList);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<RoleDTO> geRoleById(@PathVariable Long id) throws SQLException {
		RoleDTO role = roleService.getRoleById(id);
        return ResponseEntity.ok(role);
    }
	
	@GetMapping("/users/{userId}")
    public ResponseEntity<List<RoleDTO>> geRoleByUserId(@PathVariable String userId) throws SQLException {
		List<RoleDTO> roleList = roleService.getRolesByUserId(userId);
        return ResponseEntity.ok(roleList);
    }
	
}
