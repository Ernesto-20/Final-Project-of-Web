package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.BrigadeDTO;
import cu.edu.cujae.backend.core.service.BrigadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrigadeServiceImpl implements BrigadeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(BrigadeDTO brigadeDTO) throws SQLException {
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call brigade_insert(?, ?)}";
            CallableStatement preparedFunction = null;
            //
            preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, brigadeDTO.getNumber());
            preparedFunction.setInt(2, brigadeDTO.getYearId());
            preparedFunction.execute();
            preparedFunction.close();

        }
    }

    @Override
    public List<BrigadeDTO> findAll() throws SQLException {

        List<BrigadeDTO> brigades = new ArrayList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call select_all_brigade()}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                int number = resultSet.getInt("number");
                Integer yearId = resultSet.getInt("year_id");
                brigades.add(new BrigadeDTO(id, number, yearId));
            }
            resultSet.close();
            preparedFunction.close();
        }

        return brigades;
    }

    @Override
    public BrigadeDTO findById(Integer id) throws SQLException {

        BrigadeDTO brigade = null;

        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call find_brigadebyid(?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.setInt(2, id);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            if (resultSet.next()) {
                int number = resultSet.getInt(1);
                int yearId = resultSet.getInt(2);
                brigade = new BrigadeDTO(id, number, yearId);
            }
            resultSet.close();
            preparedFunction.close();
        }

        return brigade;
    }

    @Override
    public List<BrigadeDTO> findByYearId(Integer yearId) throws SQLException {

        List<BrigadeDTO> brigade = new ArrayList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call find_brigade_by_year_id(?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.setInt(2, yearId);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                int num = resultSet.getInt(2);
                brigade.add(new BrigadeDTO(id, num, yearId));
            }
            resultSet.close();
            preparedFunction.close();
        }

        return brigade;
    }

//    @Override
//    public BrigadeDTO myFindById(int id) throws SQLException {
//        BrigadeDTO brigade = null;
//        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
//            conn.setAutoCommit(false);
//            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            String query_to_execute = "SELECT * FROM brigade WHERE brigade.id =" + id;
//            ResultSet result = statement.executeQuery(query_to_execute);
//            result.first(); /* esto es necesario para que la clase ResultSet cargue el valor de resultado de la consulta*/
//            /*esto lo que hace es devolver el primer campo del ResultSet que es de tipo String. Si hubiera más campos se pone el número correspondiente y se utiliza el get del tipo de dato de ese campo. Por ejemplo si tuviera dos campo y el segundo es entero se pusiera resultado.getInt(2)*/
//            int number = result.getInt(2);
//            int yearId = result.getInt(3);
//
//            brigade = new BrigadeDTO(id, number, yearId);
//
//            result.close();
//            statement.close();
//        }
//
//        return brigade;
//    }

    @Override
    public void update(BrigadeDTO brigadeDTO) throws SQLException {

        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call brigade_update(?, ?, ?)}";
            CallableStatement preparedFunction = null;
            //
            preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, brigadeDTO.getId());
            preparedFunction.setInt(2, brigadeDTO.getNumber());
            preparedFunction.setInt(3, brigadeDTO.getYearId());
            preparedFunction.execute();

            preparedFunction.close();

        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{call brigade_delete(?)}";
            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, id);
            preparedFunction.execute();
            preparedFunction.close();
        }

    }
}
