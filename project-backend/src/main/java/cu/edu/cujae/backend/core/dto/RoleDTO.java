package cu.edu.cujae.backend.core.dto;

public class RoleDTO {
	private Long id;
	private String roleName;
	private String description;
	
	public RoleDTO(Long id, String roleName, String description) {
		this.id = id;
		this.roleName = roleName;
		this.description = description;
	}
	
	public RoleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
