package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.*;
import cu.edu.cujae.backend.core.service.CourseService;
import cu.edu.cujae.backend.core.service.DropOutService;
import cu.edu.cujae.backend.core.service.StudentDropOutService;
import cu.edu.cujae.backend.core.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentDropOutServiceImpl implements StudentDropOutService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentService studentService;

    @Autowired
    private DropOutService dropOutService;

    @Autowired
    private CourseService courseService;

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
    public List<StudentDropOutNamedDTO> findAllNamed() throws SQLException {
        LinkedList<StudentDropOutDTO> students = new LinkedList<>();
        List<StudentDropOutNamedDTO> namedStudents = new LinkedList<>();


        for(StudentDropOutDTO nameLess: students){
            StudentDropOutNamedDTO student = new StudentDropOutNamedDTO(nameLess);

            StudentDTO studentDTO = studentService.getStudentById(student.getStudentId());
            if(studentDTO != null) student.setStudentName(studentDTO.getFullName());

            DropOutDTO dropOutDTO = dropOutService.findById(student.getDropoutId());
            if(dropOutDTO != null) student.setDropoutName(dropOutDTO.getCause());

            CourseDTO courseDTO = courseService.findById(student.getCourseId());
            if(courseDTO != null) student.setCourseName(courseDTO.getIdentifier());
        }

        return namedStudents;
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
    public List<StudentDropOutNamedDTO> findNamedByStudentId(int id) throws SQLException {
        return findAllNamed().stream().filter(dto -> dto.getStudentId() == id).collect(Collectors.toList());
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
    public List<StudentDropOutNamedDTO> findNamedByCourseId(int id) throws SQLException {
        return findAllNamed().stream().filter(dto -> dto.getCourseId() == id).collect(Collectors.toList());
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
    public List<StudentDropOutNamedDTO> findNamedByDropOutId(int id) throws SQLException {
        return findAllNamed().stream().filter(dto -> dto.getCourseId() == id).collect(Collectors.toList());
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
