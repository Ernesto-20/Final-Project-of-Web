package cu.edu.cujae.pweb.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cu.edu.cujae.pweb.dto.SubjectDTO;
import cu.edu.cujae.pweb.security.CurrentUserUtils;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

/* Esta anotiacioon le indica a spring que esta clase es un servicio y por tanto luego podr� inyectarse en otro lugar usando
 * @Autowired. En estas implementaciones luego se pondraan las llamadas al proyecto backend
 */
@Service
public class SubjectServiceImpl implements SubjectService{

    @Autowired
    private RestService restService;

    @Override
    public List<SubjectDTO> getSubjects() {
        List<SubjectDTO> subjectList = new ArrayList<SubjectDTO>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<SubjectDTO> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/api/v1/subjects", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            subjectList = apiRestMapper.mapList(response, SubjectDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return subjectList;
    }

    @Override
    public SubjectDTO getSubjectById(Integer subjectId) {
        SubjectDTO subject = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<SubjectDTO> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/subjects/{subjectId}");
            String uri = template.expand(subjectId.toString()).toString();
            String response = (String)restService.GET(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            subject = apiRestMapper.mapOne(response, SubjectDTO.class);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return subject;
    }

    @Override
    public void createSubject(SubjectDTO subjectDTO) {
        restService.POST("/api/v1/subjects", subjectDTO, String.class, CurrentUserUtils.getTokenBearer()).getBody();

    }

    @Override
    public void updateSubject(SubjectDTO subjectDTO) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/subjects", params, subjectDTO, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

    @Override
    public void deleteSubject(Integer subjectId) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/subjects/{subjectId}");
        String uri = template.expand(subjectId.toString()).toString();
        restService.DELETE(uri, params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
    }

}
