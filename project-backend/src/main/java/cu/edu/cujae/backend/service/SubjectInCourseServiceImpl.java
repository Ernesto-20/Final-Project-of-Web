package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.*;
import cu.edu.cujae.backend.core.service.CourseService;
import cu.edu.cujae.backend.core.service.SubjectInCourseService;
import cu.edu.cujae.backend.core.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectInCourseServiceImpl implements SubjectInCourseService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private CourseService courseService;

    @Override
    public void insert(SubjectInCourseDTO subjectInCourseDTO) throws SQLException {
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call subject_in_course_insert(?, ?, ?, ?)}";
            CallableStatement preparedFunction = null;
            preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, subjectInCourseDTO.getSubjectId());
            preparedFunction.setInt(2, subjectInCourseDTO.getCourseId());
            preparedFunction.setInt(3, subjectInCourseDTO.getYearId());
            preparedFunction.setInt(4, subjectInCourseDTO.getHoursLong());
            preparedFunction.execute();
            preparedFunction.close();
        }
    }

    @Override
    public LinkedList<SubjectInCourseDTO> findAll() throws SQLException {
        LinkedList<SubjectInCourseDTO> subsInCourse = new LinkedList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call select_all_subject_in_course()}";
            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, java.sql.Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                int subjectId = resultSet.getInt("subject_id");
                int courseId = resultSet.getInt("course_id");
                int yearId = resultSet.getInt("year_id");
                int hours_long = resultSet.getInt("hours_long");
                subsInCourse.add(new SubjectInCourseDTO(subjectId, courseId, yearId, hours_long));
            }
            resultSet.close();
            preparedFunction.close();
        }

        return subsInCourse;
    }

    @Override
    public List<SubjectInCourseNamedDTO> findAllNamed() throws SQLException {
        LinkedList<SubjectInCourseDTO> subsInCourse = findAll();
        LinkedList<SubjectInCourseNamedDTO> namedSubsInCourse = new LinkedList<>();
        for(SubjectInCourseDTO dto: subsInCourse){
            SubjectInCourseNamedDTO subInCourse = new SubjectInCourseNamedDTO(dto);

            CourseDTO courseDTO = courseService.findById(subInCourse.getCourseId());
            if(courseDTO != null) subInCourse.setCourseName(courseDTO.getIdentifier());

            SubjectDTO subjectDTO = subjectService.getSubjectById(subInCourse.getSubjectId());
            if(subjectDTO != null) subInCourse.setSubjectName(subjectDTO.getName());

            namedSubsInCourse.add(subInCourse);
        }

        return namedSubsInCourse;
    }

    @Override
    public LinkedList<SubjectInCourseDTO> findBySubjectId(int id) throws SQLException {
        LinkedList<SubjectInCourseDTO> subsInCourse = new LinkedList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call find_subject_in_coursebysubject_id(?)}";
            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(2, id);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                int courseId = resultSet.getInt("course_id");
                int yearId = resultSet.getInt("year_id");
                int hours_long = resultSet.getInt("hours_long");
                subsInCourse.add(new SubjectInCourseDTO(id, courseId, yearId, hours_long));
            }
            resultSet.close();
            preparedFunction.close();
        }

        return subsInCourse;
    }

    @Override
    public List<SubjectInCourseNamedDTO> findNamedBySubjectId(int id) throws SQLException {
        return findAllNamed().stream().filter(dto -> dto.getSubjectId() == id).collect(Collectors.toList());
    }

    @Override
    public LinkedList<SubjectInCourseDTO> findByCourseId(int id) throws SQLException {
        LinkedList<SubjectInCourseDTO> subsInCourse = new LinkedList<>();

        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call find_subject_in_coursebycourse_id(?)}";
            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(2, id);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                int subjectId = resultSet.getInt("subject_id");
                int yearId = resultSet.getInt("year_id");
                int hours_long = resultSet.getInt("hours_long");
                subsInCourse.add(new SubjectInCourseDTO(subjectId, id, yearId, hours_long));
            }
            resultSet.close();
            preparedFunction.close();
        }

        return subsInCourse;
    }

    @Override
    public List<SubjectInCourseNamedDTO> findNamedByCourseId(int id) throws SQLException {
        return findAllNamed().stream().filter(dto -> dto.getCourseId() == id).collect(Collectors.toList());

    }

    @Override
    public LinkedList<SubjectInCourseDTO> findByYearId(int id) throws SQLException {

        LinkedList<SubjectInCourseDTO> subsInCourse = new LinkedList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call find_subject_in_coursebyyear_id(?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(2, id);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                int subjectId = resultSet.getInt("subject_id");
                int courseId = resultSet.getInt("course_id");
                int hours_long = resultSet.getInt("hours_long");
                subsInCourse.add(new SubjectInCourseDTO(subjectId, courseId, id, hours_long));
            }
            resultSet.close();
            preparedFunction.close();
        }

        return subsInCourse;
    }

    @Override
    public List<SubjectInCourseNamedDTO> findNamedByYearId(int id) throws SQLException {
        return findAllNamed().stream().filter(dto -> dto.getYearId() == id).collect(Collectors.toList());

    }

    @Override
    public void updateHoursLong(SubjectInCourseDTO subjectInCourse) throws SQLException {
        // Update hours_long in a row located by subjectId, courseId and yearId
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call subject_in_course_update(?, ?, ?, ?)}";
            CallableStatement preparedFunction = null;
            //
            preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, subjectInCourse.getSubjectId());
            preparedFunction.setInt(2, subjectInCourse.getCourseId());
            preparedFunction.setInt(3, subjectInCourse.getYearId());
            preparedFunction.setInt(4, subjectInCourse.getHoursLong());
            preparedFunction.execute();

            preparedFunction.close();
        }

    }

    @Override
    public void delete(String subjectId, String courseId, String yearId) throws SQLException {
        /*
         * 1 - subject_id
         * 2 - course_id
         * 3 - year_id
         */
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{call subject_in_course_delete(?, ?, ?)}";
            //
            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, Integer.parseInt(subjectId));
            preparedFunction.setInt(2, Integer.parseInt(courseId));
            preparedFunction.setInt(3, Integer.parseInt(yearId));
            preparedFunction.execute();
            preparedFunction.close();

        }
    }

    // @Override // No recuerdo la utilidad de esto
    // public int getYearsByCourseId(int courseId) throws SQLException{
    //
    // LinkedList<IDTO> subsInCourse = findByCourseId(courseId);
    // TreeSet<Integer> years = new TreeSet<>();
    //
    // for(IDTO dto : subsInCourse){
    // SubjectInCourseDTO s = (SubjectInCourseDTO) dto;
    // years.add(s.getYearId());
    // }
    //
    // return years.size();
    // }

    @Override
    public LinkedList<SubjectInCourseDTO> findByCourseAndYearId(int course_id, int year_id) throws SQLException {

        LinkedList<SubjectInCourseDTO> subsInCourse = new LinkedList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{?= call find_subject_in_coursebyyear_id_and_course_id(?, ?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.setInt(2, course_id);
            preparedFunction.setInt(3, year_id);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                int subjectId = resultSet.getInt("subject_id");
                int courseId = resultSet.getInt("course_id");
                int hours_long = resultSet.getInt("hours_long");
                subsInCourse.add(new SubjectInCourseDTO(subjectId, courseId, year_id, hours_long));
            }
            resultSet.close();
            preparedFunction.close();
        }

        return subsInCourse;
    }
}
