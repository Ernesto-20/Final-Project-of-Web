package cu.edu.cujae.backend.core.service;

import cu.edu.cujae.backend.core.dto.YearDTO;

import java.sql.SQLException;
import java.util.LinkedList;

public interface YearService {
    public void insert(YearDTO yearDTO) throws SQLException;
    public LinkedList<YearDTO> findAll() throws SQLException;
    public YearDTO findById(int id) throws SQLException;
    public void update(YearDTO yearDTO) throws SQLException;
    public YearDTO delete(int id) throws SQLException;
}
