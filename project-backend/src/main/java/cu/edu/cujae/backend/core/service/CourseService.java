package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.CourseDTO;

import java.sql.SQLException;
import java.util.LinkedList;

public interface CourseService {
    void insert(CourseDTO courseDTO) throws SQLException;

    LinkedList<CourseDTO> findAll() throws SQLException;

    CourseDTO findById(int id) throws SQLException;

    // NO TIENE UN MÉTODO EN EL API CONTROLLER
    CourseDTO findByStartAndFinish(int start, int finish) throws SQLException;

    CourseDTO findLast() throws SQLException;

    void update(CourseDTO courseDTO) throws SQLException;

    CourseDTO delete(int id) throws SQLException;
}
