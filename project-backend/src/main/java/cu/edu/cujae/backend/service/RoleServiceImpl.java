package cu.edu.cujae.backend.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cu.edu.cujae.backend.core.dto.RoleDTO;
import cu.edu.cujae.backend.core.dto.UserDTO;
import cu.edu.cujae.backend.core.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<RoleDTO> getRolesByUserId(String userId) throws SQLException {
		List<RoleDTO> roleList = new ArrayList<RoleDTO>();
		
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(
				      "SELECT id, role_name, description FROM xrole inner join user_role on user_role.role_id = xrole.id where user_role.user_id = ?");
	
			pstmt.setString(1, userId);
	
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				roleList.add(new RoleDTO(rs.getLong("id")
						,rs.getString("role_name")
						,rs.getString("description")));
			}
		}
		return roleList;
	}

	@Override
	public List<RoleDTO> listRoles() throws SQLException {
		List<RoleDTO> roleList = new ArrayList<RoleDTO>();
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT * FROM xrole");
			
			while(rs.next()){
				roleList.add(new RoleDTO(rs.getLong("id")
						,rs.getString("role_name")
						,rs.getString("description")));
			}
		} 
		return roleList;
	}

	@Override
	public RoleDTO getRoleById(Long roleId) throws SQLException {
		RoleDTO role = null; 
		try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
		
			PreparedStatement pstmt = conn.prepareStatement(
				      "SELECT * FROM xrole where id = ?");
	
			pstmt.setLong(1, roleId);
	
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				role = new RoleDTO(rs.getLong("id")
						,rs.getString("role_name")
						,rs.getString("description"));
			}
		}
		
		return role;
	}
}
