package cu.edu.cujae.pweb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.YearDTO;

/* Esta anotiacioon le indica a spring que esta clase es un servicio y por tanto luego podr� inyectarse en otro lugar usando

 * @Autowired. En estas implementaciones luego se pondraan las llamadas al proyecto backend
 */
@Service
public class YearServiceImpl implements YearService{
	
	@Override
	public List<YearDTO> getYears() {
		
		List<YearDTO> years = new ArrayList<>();
		years.add(new YearDTO(1, false));
		years.add(new YearDTO(2, false));
		years.add(new YearDTO(3, false));
		years.add(new YearDTO(4, false));
		
		return years;
	}
	
	public Map<String,String> getYears2() {
		
		Map<String,String> years2 = new HashMap<String,String>();
		years2.put("1","1");
		years2.put("2","2");
		years2.put("3","3");
		years2.put("4","4");
		return years2;
	}

	@Override
	public YearDTO getYearById(String userId) {
		return null;
//		return getYears().stream().filter(r -> r.getId().equals(userId)).findFirst().get();
	}

	@Override
	public void createYear(YearDTO year) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateYear(YearDTO year) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteYear(String id) {
		// TODO Auto-generated method stub
		
	}
	
}
