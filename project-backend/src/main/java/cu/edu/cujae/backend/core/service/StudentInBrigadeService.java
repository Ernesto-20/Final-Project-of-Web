package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.StudentDTO;
import cu.edu.cujae.backend.core.dto.StudentInBrigadeDTO;
import cu.edu.cujae.backend.core.dto.StudentInBrigadeNamedDTO;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public interface StudentInBrigadeService {
    void insert(StudentInBrigadeDTO studentInBrigadeDTO) throws SQLException;

    LinkedList<StudentInBrigadeDTO> findAll() throws SQLException;

    List<StudentInBrigadeNamedDTO> findAllNamed() throws SQLException;

    LinkedList<StudentInBrigadeDTO> findByStudentId(int id) throws SQLException;

    List<StudentInBrigadeNamedDTO> findNamedByStudentId(int id) throws SQLException;

    LinkedList<StudentInBrigadeDTO> findByCourseId(int id) throws SQLException;

    List<StudentInBrigadeNamedDTO> findNamedByCourseId(int id) throws SQLException;

    LinkedList<StudentInBrigadeDTO> findByBrigadeId(int id) throws SQLException;

    List<StudentInBrigadeNamedDTO> findNamedByBrigadeId(int id) throws SQLException;

    LinkedList<StudentDTO> findStudentByBrigadeId(int courseId, int brigadeId) throws SQLException;

    StudentInBrigadeDTO findBrigadeByCourseAndStudent(int courseId, int studentId) throws SQLException;

    // ! ESTE MÉTODO NO SÉ COMO CREARLE SU CONTROLLER
    int findNumberScale(StudentInBrigadeDTO studentInBrigadeDTO) throws SQLException;

    void updateBrigade(StudentInBrigadeDTO studentInBrigade) throws SQLException;

    // ! Este método es innecesario
    void updateStudent(int brigadeId, int courseId, int newStudentId) throws SQLException;

    // ?TODO: NO SE HA IMPLEMENTADO ESTE MÉTODO
    void updateNumberScale(StudentInBrigadeDTO studentInBrigade) throws SQLException;

    void delete(StudentInBrigadeDTO studentInBrigade) throws SQLException;
}
