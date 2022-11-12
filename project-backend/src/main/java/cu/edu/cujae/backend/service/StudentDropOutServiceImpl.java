package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.StudentDropOutDTO;
import cu.edu.cujae.backend.core.service.StudentDropOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.LinkedList;

@Service
public class StudentDropOutServiceImpl implements StudentDropOutService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(StudentDropOutDTO studentDropOutDTO) throws SQLException {
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call student_dropout_insert(?, ?, ?)}";
            CallableStatement preparedFunction = null;
            //
            preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, studentDropOutDTO.getDropoutId());
            preparedFunction.setInt(2, studentDropOutDTO.getCourseId());
            preparedFunction.setInt(3, studentDropOutDTO.getStudentId());
            preparedFunction.execute();
            preparedFunction.close();

        }
    }

    @Override
    public LinkedList<StudentDropOutDTO> findAll() throws SQLException {
        LinkedList<StudentDropOutDTO> stdOuts = new LinkedList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call select_all_student_dropout()}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                int dropOutId = resultSet.getInt("dropout_id");
                int courseId = resultSet.getInt("course_id");
                int studentId = resultSet.getInt("student_id");
                stdOuts.add(new StudentDropOutDTO(dropOutId, courseId, studentId));
            }
            resultSet.close();
            preparedFunction.close();
        }

        return stdOuts;
    }

    @Override
    public LinkedList<StudentDropOutDTO> findByStudentId(int id) throws SQLException {
        LinkedList<StudentDropOutDTO> stdDropOut = new LinkedList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call find_student_dropoutbystudent_id(?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(2, id);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                int dropoutId = resultSet.getInt("dropout_id");
                int courseId = resultSet.getInt("course_id");
                stdDropOut.add(new StudentDropOutDTO(dropoutId, courseId, id));
            }
            resultSet.close();
            preparedFunction.close();
        }
        return stdDropOut;
    }

    @Override
    public LinkedList<StudentDropOutDTO> findByCourseId(int id) throws SQLException {
        LinkedList<StudentDropOutDTO> stdDropOut = new LinkedList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call find_student_dropoutbycourse_id(?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(2, id);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                int dropoutId = resultSet.getInt("dropout_id");
                int studentId = resultSet.getInt("student_id");
                stdDropOut.add(new StudentDropOutDTO(dropoutId, id, studentId));
            }
            resultSet.close();
            preparedFunction.close();
        }
        return stdDropOut;
    }

    @Override
    public LinkedList<StudentDropOutDTO> findByDropOutId(int id) throws SQLException {
        LinkedList<StudentDropOutDTO> stdDropOut = new LinkedList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call find_student_dropoutbydropout_id(?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(2, id);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                int courseId = resultSet.getInt("course_id");
                stdDropOut.add(new StudentDropOutDTO(id, courseId, studentId));
            }
            resultSet.close();
            preparedFunction.close();
        }
        return stdDropOut;
    }

    @Override
    public void updateDropOut(StudentDropOutDTO studentDropOut) throws SQLException {
        // Update dropOutId in a row located by courseId and studentId
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call student_dropout_update_dropout(?, ?, ?)}";
            CallableStatement preparedFunction = null;
            preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, studentDropOut.getCourseId());
            preparedFunction.setInt(2, studentDropOut.getStudentId());
            preparedFunction.setInt(3, studentDropOut.getDropoutId());
            preparedFunction.execute();

            preparedFunction.close();
        }
    }

    // ! Este método es innecesario
    @Override
    public void updateCourse(int dropOutId, int studentId, int newCourseId) throws SQLException {
        // Update courseId in a row located by dropOutId and studentId
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call student_dropout_update_course(?, ?, ?)}";
            CallableStatement preparedFunction = null;
            preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, dropOutId);
            preparedFunction.setInt(2, studentId);
            preparedFunction.setInt(3, newCourseId);
            preparedFunction.execute();

            preparedFunction.close();
        }
    }

    @Override
    public void delete(StudentDropOutDTO studentDropOut) throws SQLException {
        /*
         * 1 - dropout_id
         * 2 - course_id
         * 3 - student_id
         */
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{call student_dropout_delete(?, ?, ?)}";
            //
            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, studentDropOut.getDropoutId());
            preparedFunction.setInt(2, studentDropOut.getCourseId());
            preparedFunction.setInt(3, studentDropOut.getStudentId());
            preparedFunction.execute();
            preparedFunction.close();
        }
    }
}
