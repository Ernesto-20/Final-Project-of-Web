package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.SubjectDTO;
import cu.edu.cujae.backend.core.service.SubjectService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* Esta anotiacioon le indica a spring que esta clase es un servicio y por tanto luego podr� inyectarse en otro lugar usando
 * @Autowired. En estas implementaciones luego se pondraan las llamadas al proyecto backend
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    @Override
    public List<SubjectDTO> getSubjects() throws SQLException {
        List<SubjectDTO> subjects = new ArrayList<>();
        subjects.add(new SubjectDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "C", false));
        subjects.add(new SubjectDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "PW", false));
        subjects.add(new SubjectDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "MH", false));
        subjects.add(new SubjectDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "MD", false));
        subjects.add(new SubjectDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "IA", false));
        subjects.add(new SubjectDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "SI", false));
        subjects.add(new SubjectDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), "DS", false));

        return subjects;
    }

    @Override
    public SubjectDTO getSubjectById(String userId) throws SQLException {
        return getSubjects().stream().filter(r -> r.getId().equals(userId)).findFirst().get();
    }

    @Override
    public void createSubject(SubjectDTO subjectDTO) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateSubject(SubjectDTO subjectDTO) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteSubject(String id) throws SQLException {
        // TODO Auto-generated method stub

    }

}
