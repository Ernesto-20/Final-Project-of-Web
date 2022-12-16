package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.*;
import cu.edu.cujae.backend.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class InitCourseTransactionServiceImpl implements InitCourseTransactionService {

    @Autowired
    private CourseService courseService;

    @Autowired
    private SubjectInCourseService subjectInCourseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentInBrigadeService studentInBrigadeService;

    @Autowired
    private BrigadeService brigadeService;


    @Override
    public void initCourse(InitCourseTransactionDTO initCourseTransactionDTO) throws SQLException {

        CourseDTO courseDTO = insertCourse(initCourseTransactionDTO);

        insertSubjectInCourse(initCourseTransactionDTO);

        insertNewBrigade(initCourseTransactionDTO);

        insertStudents(initCourseTransactionDTO, courseDTO);

    }

    private void insertStudents(InitCourseTransactionDTO initCourseTransactionDTO, CourseDTO courseDTO) throws SQLException {
        //        Insertando estudiantes y estudiantes en brigada
        List<StudentDTO> currentStudents = studentService.getStudents();
        int studentIdSerial = currentStudents.get(currentStudents.size()-1).getId();
        int brigadesCount = 0;
        for (List<StudentDTO> groupStudent: initCourseTransactionDTO.getNewStudents()){
            for(StudentDTO newStudent: groupStudent){
                studentService.createStudent(newStudent);
                studentInBrigadeService.insert(new StudentInBrigadeDTO(++studentIdSerial, courseDTO.getId(), brigadesCount));
            }

        }
    }

    private void insertNewBrigade(InitCourseTransactionDTO initCourseTransactionDTO) throws SQLException {
        Integer brigadesAmount = brigadeService.findAll().size();

//        Insertando solo nuevas brigadas
        for(int i = brigadesAmount; i< initCourseTransactionDTO.getNewStudents().size(); i++){
            brigadeService.insert(new BrigadeDTO(0, i+1, 1));
        }
    }

    private void insertSubjectInCourse(InitCourseTransactionDTO initCourseTransactionDTO) throws SQLException {
        for(SubjectInCourseCompleteDTO subjects: initCourseTransactionDTO.getSubjectInCourseCompleteDTO()){
            subjectInCourseService.insert(new SubjectInCourseDTO(subjects.getSubjectDTO().getId(),
                    subjects.getCourseDTO().getId(), Integer.parseInt(subjects.getYearID()), subjects.getAmountHours()));

        }
    }

    private CourseDTO insertCourse(InitCourseTransactionDTO initCourseTransactionDTO) throws SQLException {
        CourseDTO courseDTO = initCourseTransactionDTO.getSubjectInCourseCompleteDTO().get(0).getCourseDTO();
        courseService.insert(courseDTO);
        return courseDTO;
    }

}
