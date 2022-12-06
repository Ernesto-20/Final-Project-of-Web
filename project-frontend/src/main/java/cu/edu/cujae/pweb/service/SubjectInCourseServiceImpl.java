package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.StudentDTO;
import cu.edu.cujae.pweb.dto.SubjectDTO;
import cu.edu.cujae.pweb.dto.SubjectInCourseDTO;
import cu.edu.cujae.pweb.dto.SubjectInCourseNamedDTO;
import cu.edu.cujae.pweb.security.CurrentUserUtils;
import cu.edu.cujae.pweb.utils.ApiRestMapper;
import cu.edu.cujae.pweb.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* Esta anotiacioon le indica a spring que esta clase es un servicio y por tanto luego podr� inyectarse en otro lugar usando
 * @Autowired. En estas implementaciones luego se pondraan las llamadas al proyecto backend
 */
@Service
public class SubjectInCourseServiceImpl implements SubjectInCourseService{

    @Autowired
    private RestService restService;

    @Override
    public List<SubjectInCourseDTO> getSubjectsInCourse() {
        List<SubjectInCourseDTO> subjectsInCourse = new ArrayList<>();

//        subjectsInCourse.add(new SubjectInCourseDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), 24, false));
//        subjectsInCourse.add(new SubjectInCourseDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), 48, false));
//        subjectsInCourse.add(new SubjectInCourseDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), 72, false));
//        subjectsInCourse.add(new SubjectInCourseDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), 90, false));
//        subjectsInCourse.add(new SubjectInCourseDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), 56, false));
//        subjectsInCourse.add(new SubjectInCourseDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), 24, false));
//        subjectsInCourse.add(new SubjectInCourseDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), 24, false));

        return subjectsInCourse;
    }

    @Override
    public List<SubjectInCourseNamedDTO> getSubjectsInCourseNamed() {

        List<SubjectInCourseNamedDTO> students = new ArrayList<>();

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<SubjectInCourseNamedDTO> apiRestMapper = new ApiRestMapper<>();

            String response = (String) restService.GET("/api/v1/subjectincourses/named", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();
            students = apiRestMapper.mapList(response, SubjectInCourseNamedDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public SubjectInCourseDTO getSubjectInCourseById(String subjectId, String courseId, String yearId) {
        return getSubjectsInCourse().stream().filter(r -> r.getSubjectId().equals(subjectId)
        && r.getCourseId().equals(courseId) && r.getYearId().equals(yearId)).findFirst().get();
    }

    @Override
    public void createSubjectInCourse(SubjectInCourseDTO subjectInCourseDTO) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateSubjectInCourse(SubjectInCourseDTO subjectInCourseDTO) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/subjectincourses", params, subjectInCourseDTO, String.class, CurrentUserUtils.getTokenBearer()).getBody();

    }

    @Override
    public void deleteSubjectInCourse(String subjectId, String courseId, String yearId) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("subjectId", subjectId);
        params.add("courseId", courseId);
        params.add("yearId", yearId);
        restService.DELETE("/api/v1/subjectincourses/", params, String.class, CurrentUserUtils.getTokenBearer()).getBody();

    }

}
