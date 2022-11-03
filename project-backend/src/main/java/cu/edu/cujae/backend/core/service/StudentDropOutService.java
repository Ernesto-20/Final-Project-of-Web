package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.StudentDropOutDTO;

import java.sql.SQLException;
import java.util.LinkedList;

public interface StudentDropOutService {
    void insert(StudentDropOutDTO studentDropOutDTO) throws SQLException;

    LinkedList<StudentDropOutDTO> findAll() throws SQLException;

    LinkedList<StudentDropOutDTO> findByStudentId(int id) throws SQLException;

    LinkedList<StudentDropOutDTO> findByCourseId(int id) throws SQLException;

    LinkedList<StudentDropOutDTO> findByDropOutId(int id) throws SQLException;

    void updateDropOut(int courseId, int studentId, int newDropOutId) throws SQLException;

    void updateCourse(int dropOutId, int studentId, int newCourseId) throws SQLException;

    void delete(int dropOutId, int courseId, int studentId) throws SQLException;
}
