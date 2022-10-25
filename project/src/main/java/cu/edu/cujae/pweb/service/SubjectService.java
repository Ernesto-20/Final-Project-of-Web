package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.SubjectDTO;

import java.util.List;

public interface SubjectService {
    List<SubjectDTO> getSubjects();
    SubjectDTO getSubjectById(String userId);
    void createSubject(SubjectDTO subject);
    void updateSubject(SubjectDTO subject);
    void deleteSubject(String id);
}