package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.SubjectInCourseDTO;

import java.util.List;

public interface SubjectInCourseService {
    List<SubjectInCourseDTO> getSubjectsInCourse();
    SubjectInCourseDTO getSubjectInCourseById(String subjectId, String courseId, String yearId);
    void createSubjectInCourse(SubjectInCourseDTO subjectInCourse);
    void updateSubjectInCourse(SubjectInCourseDTO subjectInCourse);
    void deleteSubjectInCourse(String subjectId, String courseId, String yearId);
}