package cu.edu.cujae.pweb.service;

import java.util.List;
import java.util.Map;

import cu.edu.cujae.pweb.dto.YearDTO;

public interface YearService {
	List<YearDTO> getYears();
	Map<String,String> getYears2();
	YearDTO getYearById(String yearId);
	void createYear(YearDTO year);
	void updateYear(YearDTO year);
	void deleteYear(String id);
}
