package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.YearDTO;
import cu.edu.cujae.backend.core.service.YearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.LinkedList;

@Service
public class YearServiceImpl implements YearService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(YearDTO yearDTO) throws SQLException {
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call year_insert(?)}";
            CallableStatement preparedFunction = null;
            preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, yearDTO.getId());
            preparedFunction.execute();
            preparedFunction.close();
        }
    }
        @Override
    public LinkedList<YearDTO> findAll() throws SQLException {

        LinkedList<YearDTO> years = new LinkedList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{?= call select_all_year()}";
            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                years.add(new YearDTO(id));
            }
            resultSet.close();
            preparedFunction.close();
        }

        return years;
    }

    @Override
    public YearDTO findById(int id) throws SQLException {

        YearDTO year = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{?= call find_yearbyid(?)}";
            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.setInt(2, id);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            if (resultSet.next()) {
                year = new YearDTO(id);
            }
            resultSet.close();
            preparedFunction.close();
        }

        return year;

    }

    @Override
    public void update(YearDTO yearDTO) throws SQLException {

        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call year_update(?, ?, ?)}";
            CallableStatement preparedFunction = null;

            preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, yearDTO.getId());
            preparedFunction.execute();

            preparedFunction.close();
        }

    }

    @Override
    public YearDTO delete(int id) throws SQLException {

        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{call year_delete(?)}";
            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, id);
            preparedFunction.execute();
            preparedFunction.close();
        }

        return new YearDTO(id);
    }
}
