package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.BrigadeDTO;

import java.sql.SQLException;
import java.util.LinkedList;

public interface BrigadeService {

    void insert(BrigadeDTO dto) throws SQLException;

    LinkedList<BrigadeDTO> findAll() throws SQLException;

    BrigadeDTO findById(int id) throws SQLException;

    LinkedList<BrigadeDTO> findByYearId(int yearId) throws SQLException;

    BrigadeDTO myFindById(int id) throws SQLException;

    void update(BrigadeDTO dto) throws SQLException;

    BrigadeDTO delete(int id) throws SQLException;

    void deleteVoid(int id) throws SQLException;
}
