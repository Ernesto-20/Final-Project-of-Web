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

	public YearDTO getYearDto() {
		return yearDTO;
	}

	public void getYearDto(YearDTO yearDTO) {
		this.yearDTO = yearDTO;
	}

	public YearDTO getSelectedYear() {
		return selectedYear;
	}

	public void setSelectedYear(YearDTO selectedYear) {
		this.selectedYear = selectedYear;
	}

	public List<YearDTO> getYears() {
		return years = years == null ? yearService.getYears() : years;
	}

	public void setYears(List<YearDTO> years) {
		this.years = years;
	}

	public String getSelectOption() {
		return selectOption;
	}

	public void setSelectOption(String selectOption) {
		this.selectOption = selectOption;
	}
}
