package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.StatusDTO;

import java.sql.SQLException;
import java.util.LinkedList;

public interface StatusService {
    void insert(StatusDTO dto) throws SQLException;

    LinkedList<StatusDTO> findAll() throws SQLException;

    StatusDTO findById(int id) throws SQLException;

    StatusDTO findByDescription(String description) throws SQLException;

    void update(StatusDTO dto) throws SQLException;

    StatusDTO delete(int id) throws SQLException;
}
