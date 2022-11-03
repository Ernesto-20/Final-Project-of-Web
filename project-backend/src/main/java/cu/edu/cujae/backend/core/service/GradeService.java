package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.GradeDTO;

import java.sql.SQLException;
import java.util.LinkedList;

public interface GradeService {
    void insert(GradeDTO gradeDTO) throws SQLException;

    LinkedList<GradeDTO> findAll() throws SQLException;

    GradeDTO findById(int value) throws SQLException;

    GradeDTO findByScale(String scale) throws SQLException;

    void update(GradeDTO gradeDTO) throws SQLException;

    GradeDTO delete(int value) throws SQLException;
}
