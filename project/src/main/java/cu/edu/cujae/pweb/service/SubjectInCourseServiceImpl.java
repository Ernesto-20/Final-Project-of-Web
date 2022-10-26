package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.SubjectInCourseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* Esta anotiacioon le indica a spring que esta clase es un servicio y por tanto luego podr� inyectarse en otro lugar usando
 * @Autowired. En estas implementaciones luego se pondraan las llamadas al proyecto backend
 */
@Service
public class SubjectInCourseServiceImpl implements SubjectInCourseService{

    @Override
    public List<SubjectInCourseDTO> getSubjectsInCourse() {
        List<SubjectInCourseDTO> subjectsInCourse = new ArrayList<>();
        subjectsInCourse.add(new SubjectInCourseDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), 24, false));
        subjectsInCourse.add(new SubjectInCourseDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), 48, false));
        subjectsInCourse.add(new SubjectInCourseDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), 72, false));
        subjectsInCourse.add(new SubjectInCourseDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), 90, false));
        subjectsInCourse.add(new SubjectInCourseDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), 56, false));
        subjectsInCourse.add(new SubjectInCourseDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), 24, false));
        subjectsInCourse.add(new SubjectInCourseDTO(UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), UUID.randomUUID().toString().replaceAll("-", "").substring(0, 9), 24, false));

        return subjectsInCourse;
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
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteSubjectInCourse(String subjectId, String courseId,String yearId) {
        // TODO Auto-generated method stub

    }

}
