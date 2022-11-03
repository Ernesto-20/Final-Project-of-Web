package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.StudentDTO;
import cu.edu.cujae.backend.core.dto.StudentInBrigadeDTO;
import cu.edu.cujae.backend.core.service.StatusService;
import cu.edu.cujae.backend.core.service.StudentInBrigadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.LinkedList;

@Service
public class StudentInBrigadeServiceImpl implements StudentInBrigadeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StatusService statusService;

    @Override
    public void insert(StudentInBrigadeDTO studentInBrigadeDTO) throws SQLException {
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call student_in_brigade_insert(?, ?, ?)}";
            CallableStatement preparedFunction;
            preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, studentInBrigadeDTO.getStudentId());
            preparedFunction.setInt(2, studentInBrigadeDTO.getCourseId());
            preparedFunction.setInt(3, studentInBrigadeDTO.getBrigadeId());
            preparedFunction.execute();
            preparedFunction.close();

        }
    }

    @Override
    public LinkedList<StudentInBrigadeDTO> findAll() throws SQLException {
        LinkedList<StudentInBrigadeDTO> stdsInBrigade = new LinkedList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call select_all_student_in_brigade()}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                int courseId = resultSet.getInt("course_id");
                int brigadeId = resultSet.getInt("brigade_id");
                int numberScale = resultSet.getInt("number_scale");
                stdsInBrigade.add(new StudentInBrigadeDTO(studentId, courseId, brigadeId, numberScale));
            }
            resultSet.close();
            preparedFunction.close();
        }

        return stdsInBrigade;
    }

    @Override
    public LinkedList<StudentInBrigadeDTO> findByStudentId(int id) throws SQLException {
        LinkedList<StudentInBrigadeDTO> stdInBrigade = new LinkedList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call find_student_in_brigadebystudent_id(?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(2, id);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                int brigadeId = resultSet.getInt("brigade_id");
                int courseId = resultSet.getInt("course_id");
                stdInBrigade.add(new StudentInBrigadeDTO(id, courseId, brigadeId));
            }
            resultSet.close();
            preparedFunction.close();
        }

        return stdInBrigade;
    }

    @Override
    public LinkedList<StudentInBrigadeDTO> findByCourseId(int id) throws SQLException {

        LinkedList<StudentInBrigadeDTO> stdInBrigade = new LinkedList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call find_student_in_brigadebycourse_id(?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(2, id);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                int brigadeId = resultSet.getInt("brigade_id");
                int studentId = resultSet.getInt("student_id");
                stdInBrigade.add(new StudentInBrigadeDTO(studentId, id, brigadeId));
            }
            resultSet.close();
            preparedFunction.close();
        }

        return stdInBrigade;
    }

    @Override
    public StudentInBrigadeDTO findBrigadeByStudentAndCourse(int student_id, int course_id) throws SQLException {
        StudentInBrigadeDTO brigade = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call find_brigade_by_student_and_course(?, ?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.setInt(2, student_id);
            preparedFunction.setInt(3, course_id);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            if (resultSet.next()) {
                int brigadeId = resultSet.getInt("id");
                brigade = new StudentInBrigadeDTO(student_id, course_id, brigadeId);
            }
            resultSet.close();
            preparedFunction.close();
        }

        return brigade;
    }

    @Override
    public LinkedList<StudentInBrigadeDTO> findByBrigadeId(int id) throws SQLException {
        LinkedList<StudentInBrigadeDTO> stdInBrigade = new LinkedList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{?= call find_student_in_brigadebybrigade_id(?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(2, id);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                int courseId = resultSet.getInt("course_id");
                stdInBrigade.add(new StudentInBrigadeDTO(studentId, courseId, id));
            }
            resultSet.close();
            preparedFunction.close();
        }

        return stdInBrigade;
    }

    @Override
    public int findNumberScale(StudentInBrigadeDTO studentInBrigadeDTO) throws SQLException {
        int numberScale = 0;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()){
            String function = "{?= call find_student_in_brigade_number_scale(?,?,?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(2, studentInBrigadeDTO.getStudentId());
            preparedFunction.setInt(3, studentInBrigadeDTO.getCourseId());
            preparedFunction.setInt(4, studentInBrigadeDTO.getBrigadeId());
            preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            if (resultSet.next()) {
                numberScale = resultSet.getInt("number_scale");
            }
            resultSet.close();
            preparedFunction.close();
        }
        return numberScale;
    }

    @Override
    public LinkedList<StudentDTO> findStudentByBrigadeId(int brigadeId, int courseId) throws SQLException {

        LinkedList<StudentDTO> listStudents = new LinkedList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{?= call find_student_by_brigade_id(?, ?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.setInt(2, brigadeId);
            preparedFunction.setInt(3, courseId);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                int idSTD = resultSet.getInt("id");
                String idNum = resultSet.getString("id_num");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                String municipality = resultSet.getString("municipality");
                int idStatus = resultSet.getInt("status_id");
                String statusDescription = statusService.findById(idStatus).getDescription();
                listStudents.add(new StudentDTO(idSTD, idNum, first_name, last_name, gender, municipality, idStatus, statusDescription));
            }
            resultSet.close();
            preparedFunction.close();
        }

        return listStudents;
    }

    @Override
    public void updateBrigade(int studentId, int courseId, int newBrigadeId) throws SQLException {
        //Update newBrigadeId in a row located by studentId and courseId
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call student_in_brigade_update_brigade(?, ?, ?)}";
            CallableStatement preparedFunction = null;
            preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, studentId);
            preparedFunction.setInt(2, courseId);
            preparedFunction.setInt(3, newBrigadeId);
            preparedFunction.execute();
            preparedFunction.close();
        }

    }

    @Override
    public void updateStudent(int brigadeId, int courseId, int newStudentId) throws SQLException {
        //Update newBrigadeId in a row located by studentId and courseId
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call student_in_brigade_update_student(?, ?, ?)}";
            CallableStatement preparedFunction = null;
            preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, brigadeId);
            preparedFunction.setInt(2, courseId);
            preparedFunction.setInt(3, newStudentId);
            preparedFunction.execute();
            preparedFunction.close();
        }
    }

    @Override
    public void updateNumberScale(int brigadeId, int courseId, int studentId, int numberScale) throws SQLException {
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call student_in_brigade_update_number_scale(?, ?, ?, ?)}";
            CallableStatement preparedFunction = null;
            preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, brigadeId);
            preparedFunction.setInt(2, courseId);
            preparedFunction.setInt(3, studentId);
            preparedFunction.setInt(4, numberScale);
            preparedFunction.execute();
            preparedFunction.close();
        }
    }

    @Override
    public void delete(int studentId, int courseId, int brigadeId) throws SQLException {
        /*
        1 - student_id
        2 - course_id
        3 - brigade_id
         */
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{call student_in_brigade_delete(?, ?, ?)}";
            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, studentId);
            preparedFunction.setInt(2, courseId);
            preparedFunction.setInt(3, brigadeId);
            preparedFunction.execute();
            preparedFunction.close();
        }
    }
}
