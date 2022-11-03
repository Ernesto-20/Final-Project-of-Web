package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.GradeDTO;
import cu.edu.cujae.backend.core.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.LinkedList;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(GradeDTO gradeDTO) throws SQLException {

        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call grade_insert(?, ?)}";
            CallableStatement preparedFunction = null;
            preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, gradeDTO.getValue());
            preparedFunction.setString(2, gradeDTO.getScale());
            preparedFunction.execute();

            preparedFunction.close();

        }
    }

    @Override
    public LinkedList<GradeDTO> findAll() throws SQLException {

        LinkedList<GradeDTO> grades = new LinkedList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call select_all_grade()}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                int value = resultSet.getInt("value");
                String scale = resultSet.getString("scale");
                grades.add(new GradeDTO(value, scale));
            }
            resultSet.close();
            preparedFunction.close();
        }

        return grades;

    }

    @Override
    public GradeDTO findById(int value) throws SQLException {   //return the scale related with the value
        GradeDTO gradeDTO = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call find_gradebyvalue(?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.setInt(2, value);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            if (resultSet.next()) {
                String scale = resultSet.getString("scale");
                gradeDTO = new GradeDTO(value, scale);
            }
            resultSet.close();
            preparedFunction.close();
        }

        return gradeDTO;

    }

    @Override
    public GradeDTO findByScale(String scale) throws SQLException {
        GradeDTO gradeDTO = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call find_gradebyscale(?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.setString(2, scale);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            if (resultSet.next()) {
                int value = resultSet.getInt("value");
                gradeDTO = new GradeDTO(value, scale);
            }
            resultSet.close();
            preparedFunction.close();
        }

        return gradeDTO;
    }

    @Override
    public void update(GradeDTO gradeDTO) throws SQLException {

        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call grade_update(?, ?)}";
            CallableStatement preparedFunction = null;

            preparedFunction.setInt(1, gradeDTO.getValue());
            preparedFunction.setString(2, gradeDTO.getScale());
            preparedFunction.execute();

            preparedFunction.close();

        }
    }

    @Override
    public GradeDTO delete(int value) throws SQLException {
        GradeDTO gradeDTO = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            gradeDTO = findById(value);
            String function = "{call grade_delete(?)}";
            //
            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, value);
            preparedFunction.execute();
            preparedFunction.close();
        }

        return gradeDTO;

    }
}
