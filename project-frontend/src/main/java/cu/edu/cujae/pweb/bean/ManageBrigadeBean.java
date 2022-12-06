package cu.edu.cujae.pweb.bean;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.pweb.dto.BrigadeDTO;
import cu.edu.cujae.pweb.service.BrigadeService;
import cu.edu.cujae.pweb.utils.JsfUtils;


@Component //Le indica a spring es un componete registrado
@ManagedBean
@ViewScoped //Este es el alcance utilizado para trabajar con Ajax
public class ManageBrigadeBean {
	
	private BrigadeDTO brigadeDTO;
	private BrigadeDTO selectedBrigade;
	private List<BrigadeDTO> brigades;
	
	/* @Autowired es la manera para inyectar una dependencia/clase anotada con @service en spring
	 * Tener en cuenta que lo que se inyecta siempre es la interfaz y no la clase
	 */
	@Autowired
	private BrigadeService brigadeService;
		
	public ManageBrigadeBean() {
		
	}
	
	public BrigadeDTO getBrigadeDTO() {
		return brigadeDTO;
	}

	public void setBrigadeDTO(BrigadeDTO brigadeDTO) {
		this.brigadeDTO = brigadeDTO;
	}

	public BrigadeDTO getSelectedBrigade() {
		return selectedBrigade;
	}

	public void setSelectedBrigade(BrigadeDTO selectedBrigade) {
		this.selectedBrigade = selectedBrigade;
	}

	public List<BrigadeDTO> getBrigades() {
		return brigades = brigades == null ? brigadeService.findAll() : brigades;
	}

	public void setBrigades(List<BrigadeDTO> brigades) {
		this.brigades = brigades;
	}

}
