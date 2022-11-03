package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.StudentDTO;
import cu.edu.cujae.backend.core.dto.StudentInBrigadeDTO;

import java.sql.SQLException;
import java.util.LinkedList;

public interface StudentInBrigadeService {
    void insert(StudentInBrigadeDTO studentInBrigadeDTO) throws SQLException;

    LinkedList<StudentInBrigadeDTO> findAll() throws SQLException;

    LinkedList<StudentInBrigadeDTO> findByStudentId(int id) throws SQLException;

    LinkedList<StudentInBrigadeDTO> findByCourseId(int id) throws SQLException;

    StudentInBrigadeDTO findBrigadeByStudentAndCourse(int student_id, int course_id) throws SQLException;

    LinkedList<StudentInBrigadeDTO> findByBrigadeId(int id) throws SQLException;

    int findNumberScale(StudentInBrigadeDTO studentInBrigadeDTO) throws SQLException;

    LinkedList<StudentDTO> findStudentByBrigadeId(int brigadeId, int courseId) throws SQLException;

    void updateBrigade(int studentId, int courseId, int newBrigadeId) throws SQLException;

    void updateStudent(int brigadeId, int courseId, int newStudentId) throws SQLException;

    void updateNumberScale(int brigadeId, int courseId, int studentId, int numberScale) throws SQLException;

    void delete(int studentId, int courseId, int brigadeId) throws SQLException;
}
