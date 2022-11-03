package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.DropOutDTO;

import java.sql.SQLException;
import java.util.LinkedList;

public interface DropOutService {
    void insert(DropOutDTO dropOutDTO) throws SQLException;

    LinkedList<DropOutDTO> findAll() throws SQLException;

    DropOutDTO findById(int id) throws SQLException;

    DropOutDTO findByCause(String cause) throws SQLException;

    void update(DropOutDTO dropOutDTO) throws SQLException;

    DropOutDTO delete(int id) throws SQLException;
}
