package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.SubjectInCourseDTO;
import cu.edu.cujae.backend.core.dto.SubjectInCourseNamedDTO;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public interface SubjectInCourseService {
    void insert(SubjectInCourseDTO subjectInCourseDTO) throws SQLException;

    LinkedList<SubjectInCourseDTO> findAll() throws SQLException;

    List<SubjectInCourseNamedDTO> findAllNamed() throws SQLException;

    LinkedList<SubjectInCourseDTO> findBySubjectId(int id) throws SQLException;

    List<SubjectInCourseNamedDTO> findNamedBySubjectId(int id) throws SQLException;

    LinkedList<SubjectInCourseDTO> findByCourseId(int id) throws SQLException;

    List<SubjectInCourseNamedDTO> findNamedByCourseId(int id) throws SQLException;

    LinkedList<SubjectInCourseDTO> findByYearId(int id) throws SQLException;

    List<SubjectInCourseNamedDTO> findNamedByYearId(int id) throws SQLException;

    void updateHoursLong(SubjectInCourseDTO subjectInCourse) throws SQLException;

    void delete(String subjectId, String courseId, String yearId) throws SQLException;

    // ?TODO: NO SE HA IMPLEMENTADO ESTE MÉTODO
    LinkedList<SubjectInCourseDTO> findByCourseAndYearId(int course_id, int year_id) throws SQLException;
}
