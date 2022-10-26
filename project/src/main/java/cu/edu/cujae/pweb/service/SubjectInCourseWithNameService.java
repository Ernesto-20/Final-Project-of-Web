package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.StudentDto;
import cu.edu.cujae.pweb.dto.SubjectInCourseWithNameDTO;

import java.util.List;

public interface SubjectInCourseWithNameService {
        List<SubjectInCourseWithNameDTO> getSubjects();
        SubjectInCourseWithNameDTO getSubjectById(String id);
        void createStudent(SubjectInCourseWithNameDTO subject);
        void updateStudent(SubjectInCourseWithNameDTO subject);
        void deleteStudent(String id);
    }
