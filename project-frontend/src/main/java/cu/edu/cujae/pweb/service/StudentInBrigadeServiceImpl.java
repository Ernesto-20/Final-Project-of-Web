package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cu.edu.cujae.pweb.dto.StudentInBrigadeDTO;
import cu.edu.cujae.pweb.security.CurrentUserUtils;

/* Esta anotación le indica a spring que esta clase es un servicio y por tanto luego podrá inyectarse en otro lugar usando

 * @Autowired. En estas implementaciones luego se pondrán las llamadas al proyecto backend
 */
@Service
public class StudentInBrigadeServiceImpl implements StudentInBrigadeService {
	@Autowired
	private RestService restService;


	@Override
	public void createStudentInBrigade(StudentInBrigadeDTO studentInBrigade) {
		restService.POST("/api/v1/studentinbrigades", studentInBrigade, String.class, CurrentUserUtils.getTokenBearer()).getBody();
	}

}
