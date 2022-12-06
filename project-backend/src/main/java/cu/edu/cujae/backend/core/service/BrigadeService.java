package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.BrigadeDTO;

import java.sql.SQLException;
import java.util.List;

public interface BrigadeService {

    void insert(BrigadeDTO dto) throws SQLException;

    List<BrigadeDTO> findAll() throws SQLException;

    BrigadeDTO findById(Integer id) throws SQLException;

    List<BrigadeDTO> findByYearId(Integer yearId) throws SQLException;

//    BrigadeDTO myFindById(int id) throws SQLException;

    void update(BrigadeDTO dto) throws SQLException;


    void delete(Integer id) throws SQLException;
}
