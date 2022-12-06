package cu.edu.cujae.pweb.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cu.edu.cujae.pweb.dto.YearDTO;

public interface YearService {
	List<YearDTO> getYears();
	void createYear(YearDTO year);
	void updateYear(YearDTO year);
	void deleteYear(Integer yearId);
}
