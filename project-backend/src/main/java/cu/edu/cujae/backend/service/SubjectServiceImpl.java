package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.SubjectDTO;
import cu.edu.cujae.backend.core.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/* Esta anotación le indica a spring que esta clase es un servicio y por tanto luego podrá inyectarse en otro lugar usando
 * @Autowired. En estas implementaciones luego se pondrían las llamadas al proyecto backend
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<SubjectDTO> getSubjects() throws SQLException {
        LinkedList<SubjectDTO> subjects = new LinkedList<>();
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call select_all_subject()}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                subjects.add(new SubjectDTO(id, name));
            }
            resultSet.close();
            preparedFunction.close();
        }

        return subjects;
    }

    @Override
    public SubjectDTO getSubjectById(Integer id) throws SQLException {
        SubjectDTO subjectDTO = null;
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            String function = "{?= call find_subjectbyid(?)}";

            CallableStatement preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(2, id);
            preparedFunction.registerOutParameter(1, Types.OTHER);
            preparedFunction.execute();
            ResultSet resultSet = (ResultSet) preparedFunction.getObject(1);
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                subjectDTO = new SubjectDTO(id, name);
            }
            resultSet.close();
            preparedFunction.close();
        }
        return subjectDTO;
    }

    @Override
    public void createSubject(SubjectDTO subjectDTO) throws SQLException {
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call subject_insert(?)}";
            CallableStatement preparedFunction = null;
            preparedFunction = conn.prepareCall(function);
            preparedFunction.setString(1, subjectDTO.getName());
            preparedFunction.execute();
            preparedFunction.close();
        }
    }

    @Override
    public void updateSubject(SubjectDTO subjectDTO) throws SQLException {
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            String function = "{ call subject_update(?, ?)}";
            CallableStatement preparedFunction = null;

            preparedFunction = conn.prepareCall(function);
            preparedFunction.setInt(1, subjectDTO.getId());
            preparedFunction.setString(2, subjectDTO.getName());
            preparedFunction.execute();

            preparedFunction.close();

        }
    }

    @Override
    public void deleteSubject(Integer id) throws SQLException {
        try (Connection conn = jdbcTemplate.getDataSource().getConnection()) {
            CallableStatement CS = conn.prepareCall("{call subject_delete(?)}");
            CS.setInt(1, id);
            CS.executeUpdate();
        }
    }

}
