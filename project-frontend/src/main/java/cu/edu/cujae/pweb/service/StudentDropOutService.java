package cu.edu.cujae.pweb.service;

import java.util.List;

import cu.edu.cujae.pweb.dto.StudentDropOutDTO;
import cu.edu.cujae.pweb.dto.StudentDropOutNamedDTO;

public interface StudentDropOutService {
    void createStudentDropOut(StudentDropOutDTO studentDropOut);

    List<StudentDropOutDTO> getStudentDropOuts();

	List<StudentDropOutNamedDTO> getNamedStudentDropOutByCourseId(Integer courseId);

//    List<StudentDropOutNamedDTO> findAllNamed();
//
//    List<StudentDropOutDTO> findByStudentId(int id);
//
//    List<StudentDropOutNamedDTO> findNamedByStudentId(int id);
//
//    List<StudentDropOutDTO> findByCourseId(int id);
//
//    List<StudentDropOutNamedDTO> findNamedByCourseId(int id);
//
//    List<StudentDropOutDTO> findByDropOutId(int id) throws SQLException;
//
//    List<StudentDropOutNamedDTO> findNamedByDropOutId(int id) throws SQLException;
//
//    void updateDropOut(StudentDropOutDTO studentDropOut) throws SQLException;
//
//    // ! Este método es innecesario
//    void updateCourse(int dropOutId, int studentId, int newCourseId) throws SQLException;
//
//    void delete(StudentDropOutDTO studentDropOut) throws SQLException;
}
