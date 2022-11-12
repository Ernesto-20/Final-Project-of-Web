package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.SubjectInCourseDTO;

import java.sql.SQLException;
import java.util.LinkedList;

public interface SubjectInCourseService {
    void insert(SubjectInCourseDTO subjectInCourseDTO) throws SQLException;

    LinkedList<SubjectInCourseDTO> findAll() throws SQLException;

    LinkedList<SubjectInCourseDTO> findBySubjectId(int id) throws SQLException;

    LinkedList<SubjectInCourseDTO> findByCourseId(int id) throws SQLException;

    LinkedList<SubjectInCourseDTO> findByYearId(int id) throws SQLException;

    void updateHoursLong(SubjectInCourseDTO subjectInCourse) throws SQLException;

    void delete(SubjectInCourseDTO subjectInCourse) throws SQLException;

    // ?TODO: NO SE HA IMPLEMENTADO ESTE MÉTODO
    LinkedList<SubjectInCourseDTO> findByCourseAndYearId(int course_id, int year_id) throws SQLException;
}
