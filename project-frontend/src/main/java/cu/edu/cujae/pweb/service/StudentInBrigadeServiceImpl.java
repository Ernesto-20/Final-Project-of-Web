package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.BrigadeDTO;
import cu.edu.cujae.pweb.dto.StudentDTO;
import cu.edu.cujae.pweb.dto.StudentGradeCourseIdDTO;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cu.edu.cujae.pweb.dto.StudentInBrigadeDTO;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/* Esta anotación le indica a spring que esta clase es un servicio y por tanto luego podrá inyectarse en otro lugar usando

 * @Autowired. En estas implementaciones luego se pondrán las llamadas al proyecto backend
 */
@Service
public class StudentInBrigadeServiceImpl implements StudentInBrigadeService {
	@Autowired
	private RestService restService;

	@Autowired
	private BrigadeService brigadeService;


	@Override
	public void createStudentInBrigade(StudentInBrigadeDTO studentInBrigade) {
		restService.POST("/api/v1/studentinbrigades", studentInBrigade, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

	@Override
	public List<Integer> getStudentsIdsByCourseAndYearId(int courseId, int yearId) {
		List<BrigadeDTO> brigades = brigadeService.findByYearId(yearId);
		System.out.println("Brigadas: " + brigades.size());
		List<Integer> studentsId = null;
		List<StudentInBrigadeDTO> studentsInBrigade = null;

		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			ApiRestMapper<StudentInBrigadeDTO> apiRestMapper = new ApiRestMapper<>();

			String response = (String) restService.GET("/api/v1/studentinbrigades", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
			studentsInBrigade = apiRestMapper.mapList(response, StudentInBrigadeDTO.class);

		} catch (IOException e) {
			e.printStackTrace();
		}

		if(studentsInBrigade != null) {

			studentsId = studentsInBrigade.stream().filter(sInB -> isBrigadeInList(sInB.getBrigadeId(), brigades) && sInB.getCourseId() == courseId).map(sInB -> sInB.getStudentId()).collect(Collectors.toList());
//			System.out.println("Showing filtered student");
//			for (Integer b : studentsId)
//				System.out.println(b);
//			System.out.println("Finish showing filtered student");
		}
		return studentsId;
	}

	public boolean isBrigadeInList(int brigadeId, List<BrigadeDTO> brigades){

		for(BrigadeDTO brigade : brigades)
			if(brigade.getId() == brigadeId)
				return true;

		return false;
	}
}
