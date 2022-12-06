package cu.edu.cujae.pweb.bean;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.YearDTO;
import cu.edu.cujae.pweb.service.YearService;
import cu.edu.cujae.pweb.utils.JsfUtils;

@Component // Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped // Este es el alcance utilizado para trabajar con Ajax
public class ManageYearBean {

	private YearDTO yearDTO;
	private YearDTO selectedYear;
	private List<YearDTO> years;
	private Map<String, String> years2;
	private String selectOption;

	/*
	 * @Autowired es la manera para inyectar una dependencia/clase anotada
	 * con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private YearService yearService;

	public ManageYearBean() {

	}

	// Esta anotación permite que se ejecute code luego de haberse ejecuta el
	// constructor de la clase.
	@PostConstruct
	public void init() {
		years = years == null ? yearService.getYears() : years;
		years2 = years2 == null ? yearService.getYears2() : years2;

	}

	// Se ejecuta al dar clic en el button Nuevo
	public void openNew() {
		this.selectedYear = new YearDTO();
	}

	// Se ejecuta al dar clic en el button con el lapicito
	public void openForEdit() {
		// List<RoleDto> roles = this.selectedUser.getRoles();
		// this.selectedRoles = roles.stream().map(r -> r.getId()).toArray(Long[]::new);
	}

	// Se ejecuta al dar clic en el button dentro del dialog para salvar o registrar
	// al usuario
	// public void saveYear() {
	// if (this.selectedYear.getId() == null) {
	// this.selectedYear.setId(UUID.randomUUID().toString().replaceAll("-",
	// "").substring(0, 9));
	// this.selectedYear.setNewRecord(true);
	//
	// this.years.add(this.selectedYear);
	// JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO,
	// "message_user_added"); //Este code permite mostrar un mensaje exitoso
	// (FacesMessage.SEVERITY_INFO) obteniendo el mensage desde el fichero de
	// recursos, con la llave message_user_added
	// }
	// else {
	// JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO,
	// "message_user_edited");
	// }
	//
	// PrimeFaces.current().executeScript("PF('manageYearDialog').hide()");//Este
	// code permite cerrar el dialog cuyo id es manageUserDialog. Este identificador
	// es el widgetVar
	// PrimeFaces.current().ajax().update("form:dt-years");// Este code es para
	// refrescar el componente con id dt-users que se encuentra dentro del
	// formulario con id form
	// }

	// Permite eliminar un usuario
	public void deleteYear() {
		try {
			this.years.remove(this.selectedYear);
			this.selectedYear = null;
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_INFO, "message_user_removed");
			PrimeFaces.current().ajax().update("form:dt-years");// Este code es para refrescar el componente con id
																// dt-users que se encuentra dentro del formulario con
																// id form
		} catch (Exception e) {
			JsfUtils.addMessageFromBundle(null, FacesMessage.SEVERITY_ERROR, "message_error");
		}

	}

	public YearDTO yearDto() {
		return yearDTO;
	}

	public void yearDto(YearDTO yearDTO) {
		this.yearDTO = yearDTO;
	}

	public YearDTO getSelectedYear() {
		return selectedYear;
	}

	public void setSelectedYear(YearDTO selectedYear) {
		this.selectedYear = selectedYear;
	}

	public List<YearDTO> getYears() {
		return years;
	}

	public void setYears(List<YearDTO> years) {
		this.years = years;
	}

	public Map<String, String> getYears2() {
		return years2;
	}

	public void setYears2(Map<String, String> years2) {
		this.years2 = years2;
	}

	public String getSelectOption() {
		return selectOption;
	}

	public void setSelectOption(String selectOption) {
		this.selectOption = selectOption;
	}
}
