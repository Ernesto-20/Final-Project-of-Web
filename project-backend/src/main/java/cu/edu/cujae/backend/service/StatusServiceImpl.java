package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.StatusDTO;
import cu.edu.cujae.backend.core.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.LinkedList;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(StatusDTO statusDTO) throws SQLException {
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call status_insert(?)}";
            CallableStatement preparedFunction = null;
            preparedFunction = conn.prepareCall(function);
            preparedFunction.setString(1, statusDTO.getDescription());
            preparedFunction.execute();

            preparedFunction.close();
        }
    }

    @Override
    public LinkedList<StatusDTO> findAll() throws SQLException {
        LinkedList<StatusDTO> statuses = new LinkedList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call select_all_status()}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                statuses.add(new StatusDTO(id, description));
            }
            resultSet.close();
            preparedFunction.close();
        }
        return statuses;
    }

    @Override
    public StatusDTO findById(int id) throws SQLException {
        StatusDTO statusDTO = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call find_statusbyid(?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
            preparedFunction.setInt(2, id);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            if (resultSet.next()) {
                String description = resultSet.getString("description");
                statusDTO = new StatusDTO(id, description);
            }
            resultSet.close();
            preparedFunction.close();
        }
        return statusDTO;
    }

    @Override
    public StatusDTO findByDescription(String description) throws SQLException {
        StatusDTO statusDTO = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call find_statusbydescription(?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.setString(2, description);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                statusDTO = new StatusDTO(id, description);
            }
            resultSet.close();
            preparedFunction.close();
        }
        return statusDTO;
    }

    @Override
    public void update(StatusDTO statusDTO) throws SQLException {
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call status_update(?, ?)}";
            CallableStatement preparedFunction = null;
            //
            preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, statusDTO.getId());
            preparedFunction.setString(2, statusDTO.getDescription());
            preparedFunction.execute();

            preparedFunction.close();

        }
    }

    @Override
    public StatusDTO delete(int id) throws SQLException {
        StatusDTO statusDTO = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            statusDTO = findById(id);
            String function = "{call status_delete(?)}";
            //
            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, id);
            preparedFunction.execute();
            preparedFunction.close();
        }
        return statusDTO;
    }
}
