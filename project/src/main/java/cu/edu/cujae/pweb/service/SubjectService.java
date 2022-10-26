package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.SubjectDTO;

public interface SubjectService {
    List<SubjectDTO> getSubjects();
    SubjectDTO getSubjectById(String subjectId);
    void createSubject(SubjectDTO subject);
    void updateSubject(SubjectDTO subject);
    void deleteSubject(String id);
}