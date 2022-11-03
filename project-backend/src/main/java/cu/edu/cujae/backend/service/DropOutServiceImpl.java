package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.DropOutDTO;
import cu.edu.cujae.backend.core.service.DropOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.LinkedList;

@Service
public class DropOutServiceImpl implements DropOutService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(DropOutDTO dropOutDTO) throws SQLException {
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call dropout_insert(?)}";
            CallableStatement preparedFunction = null;
            //
            preparedFunction = conn.prepareCall(function);
            preparedFunction.setString(1, dropOutDTO.getCause());
            preparedFunction.execute();

            preparedFunction.close();

        }
    }

    @Override
    public LinkedList<DropOutDTO> findAll() throws SQLException {
        LinkedList<DropOutDTO> dropOuts = new LinkedList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{?= call select_all_dropout()}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cause = resultSet.getString("cause");
                dropOuts.add(new DropOutDTO(id, cause));
            }
            resultSet.close();
            preparedFunction.close();
        }

        return dropOuts;
    }

    @Override
    public DropOutDTO findById(int id) throws SQLException {
        DropOutDTO dropOutDTO = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{?= call find_dropoutbyid(?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(2, id);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            if (resultSet.next()) {
                String cause = resultSet.getString("cause");
                dropOutDTO = new DropOutDTO(id, cause);
            }
            resultSet.close();
            preparedFunction.close();
        }
        return dropOutDTO;
    }

    @Override
    public DropOutDTO findByCause(String cause) throws SQLException {
        DropOutDTO dropOutDTO = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{?= call find_dropoutbycause(?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setString(2, cause);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                dropOutDTO = new DropOutDTO(id, cause);
            }
            resultSet.close();
            preparedFunction.close();
        }
        return dropOutDTO;
    }

    @Override
    public void update(DropOutDTO dropOutDTO) throws SQLException {
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call dropout_update(?, ?)}";
            CallableStatement preparedFunction = null;

            preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, dropOutDTO.getId());
            preparedFunction.setString(2, dropOutDTO.getCause());
            preparedFunction.execute();

            preparedFunction.close();

        }
    }

    @Override
    public DropOutDTO delete(int id) throws SQLException {
        DropOutDTO dropOutDTO = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            dropOutDTO = findById(id);
            String function = "{call dropout_delete(?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, id);
            preparedFunction.execute();
            preparedFunction.close();
        }

        return dropOutDTO;
    }
}

