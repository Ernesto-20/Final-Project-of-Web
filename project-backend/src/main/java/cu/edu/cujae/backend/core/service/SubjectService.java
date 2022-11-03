package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.SubjectDTO;

import java.sql.SQLException;
import java.util.List;

public interface SubjectService {
    List<SubjectDTO> getSubjects() throws SQLException;
    SubjectDTO getSubjectById(Integer subjectId) throws SQLException;
    void createSubject(SubjectDTO subject) throws SQLException;
    void updateSubject(SubjectDTO subject) throws SQLException;
    void deleteSubject(Integer id) throws SQLException;
}