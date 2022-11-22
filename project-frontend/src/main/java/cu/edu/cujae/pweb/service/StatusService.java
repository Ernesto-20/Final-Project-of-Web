package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.StatusDTO;

public interface StatusService {
	List<StatusDTO> getStatus();
	StatusDTO getStatusById(Integer statusId);
	StatusDTO getStatusByDescription(String description);
	void createStatus(StatusDTO status);
	void updateStatus(StatusDTO status);
	void deleteStatus(Integer statusId);
}
