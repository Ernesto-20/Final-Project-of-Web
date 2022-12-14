package cu.edu.cujae.pweb.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.RoleDTO;
import cu.edu.cujae.pweb.dto.UserDTO;
import cu.edu.cujae.pweb.service.RoleService;
import cu.edu.cujae.pweb.service.UserService;
import cu.edu.cujae.pweb.utils.JsfUtils;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.RoleDTO;
import cu.edu.cujae.pweb.dto.UserDTO;
import cu.edu.cujae.pweb.service.RoleService;
import cu.edu.cujae.pweb.service.UserService;
import cu.edu.cujae.pweb.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ManageUserBean {

	private UserDTO userDto;
	private UserDTO selectedUser;
	private List<UserDTO> users;
	private Long[] selectedRoles;

	private List<RoleDTO> roles;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	public ManageUserBean() {
	}

	public void openNew() {
		this.selectedUser = new UserDTO();
		this.selectedRoles = null;
	}

	public void openForEdit() {
		List<RoleDTO> roles = this.selectedUser.getRoles();
		this.selectedRoles = roles.stream().map(r -> r.getId()).toArray(Long[]::new);
	}

	public void saveUser() {
		if (this.selectedUser.getId() == null) {
			this.selectedUser.setId(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9));
			List<RoleDTO> rolesToAdd = new ArrayList<RoleDTO>();
			for (int i = 0; i < this.selectedRoles.length; i++) {
				rolesToAdd.add(roleService.getRolesById(selectedRoles[i]));
			}
			this.selectedUser.setRoles(rolesToAdd);

			// register user
			userService.createUser(this.selectedUser);

			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_added"); // Este code permite
																									// mostrar un
																									// mensaje exitoso
																									// (FacesMessage.SEVERITY_INFO)
																									// obteniendo el
																									// mensage desde el
																									// fichero de
																									// recursos, con la
																									// llave
																									// message_user_added
		} else {
			// register user
			userService.updateUser(this.selectedUser);
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_edited");
		}

		// load datatable again with new values
		users = userService.getUsers();

		PrimeFaces.current().executeScript("PF('manageUserDialog').hide()");
		PrimeFaces.current().ajax().update("form:dt-users");
	}

	// Permite eliminar un usuario
	public void deleteUser() {
		try {
			// delete user
			userService.deleteUser(this.selectedUser.getId());
			this.selectedUser = null;

			// load datatable again with new values
			users = userService.getUsers();

			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_deleted");
			PrimeFaces.current().ajax().update("form:dt-users");// Este code es para refrescar el componente con id
																// dt-users que se encuentra dentro del formulario con
																// id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}

	}

	public UserDTO getUserDTO() {
		return userDto;
	}

	public void setUserDTO(UserDTO userDto) {
		this.userDto = userDto;
	}

	public UserDTO getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(UserDTO selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<UserDTO> getUsers() {
		users = userService.getUsers();
		return users;
	}

	public void setUsers(List<UserDTO> users) {

		this.users = users;
	}

	public Long[] getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(Long[] selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	public List<RoleDTO> getRoles() {
		roles = roles == null ? roleService.getRoles() : roles;

		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

}
