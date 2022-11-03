package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.CourseDTO;
import cu.edu.cujae.backend.core.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.LinkedList;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(CourseDTO courseDTO) throws SQLException {
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call course_insert(?, ?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, courseDTO.getStart());
            preparedFunction.setInt(2, courseDTO.getFinish());
            preparedFunction.execute();
            preparedFunction.close();

        }
    }

    @Override
    public LinkedList<CourseDTO> findAll() throws SQLException {

        LinkedList<CourseDTO> courses = new LinkedList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call select_all_course()}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int start = resultSet.getInt("start");
                int finish = resultSet.getInt("finish");
                courses.add(new CourseDTO(id, start, finish));
            }
            resultSet.close();
            preparedFunction.close();
        }

        return courses;
    }

    @Override
    public CourseDTO findById(int id) throws SQLException {

        CourseDTO course = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call find_coursebyid(?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.setInt(2, id);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            if (resultSet.next()) {
                int start = resultSet.getInt("start");
                int finish = resultSet.getInt("finish");
                course = new CourseDTO(id, start, finish);
            }
            resultSet.close();
            preparedFunction.close();
        }

        return course;
    }

    @Override
    public CourseDTO findByStartAndFinish(int start, int finish) throws SQLException {

        CourseDTO course = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call find_coursebystart_and_finish(?, ?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.setInt(2, start);
            preparedFunction.setInt(3, finish);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                course = new CourseDTO(id, start, finish);
            }
            resultSet.close();
            preparedFunction.close();
        }

        return course;
    }

    @Override
    public CourseDTO findLast() throws SQLException {

        CourseDTO course = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call find_course_last()}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                int start = resultSet.getInt("start");
                int finish = resultSet.getInt("finish");
                course = new CourseDTO(id, start, finish);
            }
            resultSet.close();
            preparedFunction.close();
        }

        return course;
    }

    @Override
    public void update(CourseDTO courseDTO) throws SQLException {

        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call course_update(?, ?, ?)}";
            CallableStatement preparedFunction = null;
            //
            preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, courseDTO.getId());
            preparedFunction.setInt(2, courseDTO.getStart());
            preparedFunction.setInt(3, courseDTO.getFinish());
            preparedFunction.execute();

            preparedFunction.close();

        }
    }

    @Override
    public CourseDTO delete(int id) throws SQLException {

        CourseDTO courseDTO = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            courseDTO = findById(id);
            String function = "{call course_delete(?)}";
            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, id);
            preparedFunction.execute();
            preparedFunction.close();
        }

        return courseDTO;
    }

}
