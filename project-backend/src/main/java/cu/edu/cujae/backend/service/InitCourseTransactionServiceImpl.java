package cu.edu.cujae.backend.service;

import cu.edu.cujae.backend.core.dto.*;
import cu.edu.cujae.backend.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
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
    private StudentGradeService studentGradeService;

    @Autowired
    private BrigadeService brigadeService;




    @Override
    public void initCourse(InitCourseTransactionDTO initCourseTransactionDTO) throws SQLException {

        CourseDTO courseDTO = insertCourse(initCourseTransactionDTO);

        insertSubjectInCourse(initCourseTransactionDTO, courseDTO);

        insertNewBrigade(initCourseTransactionDTO);

        List<StudentDTO> newStudent = insertStudents(initCourseTransactionDTO, courseDTO);

        insertStudentGrade(initCourseTransactionDTO.getSubjectInCourseCompleteDTO(), newStudent, courseDTO);

    }

    private void insertStudentGrade(List<SubjectInCourseCompleteDTO> subjectInCourseCompleteDTOs, List<StudentDTO> newStudents, CourseDTO courseDTO) throws SQLException {

        for(SubjectInCourseCompleteDTO subjectInCourseCompleteDTO: subjectInCourseCompleteDTOs){
//            Insert only students in first school year
            if(subjectInCourseCompleteDTO.getYearID() == 1) {
                for (StudentDTO studentDTO : newStudents) {
                    studentGradeService.insert(new StudentGradeOnlyIdDTO(
                            subjectInCourseCompleteDTO.getYearID(),
                            studentDTO.getId(),
                            subjectInCourseCompleteDTO.getSubjectDTO().getId(),
                            courseDTO.getId(),
                            0
                    ));
                }
            }
        }

    }

    private List<StudentDTO> insertStudents(InitCourseTransactionDTO initCourseTransactionDTO, CourseDTO courseDTO) throws SQLException {
        //        Insertando estudiantes y estudiantes en brigada
        List<StudentDTO> currentStudents = studentService.getStudents();
        int brigadesCount = 1;
        List<StudentDTO> newStudents = new ArrayList<>();
        for (List<StudentDTO> groupStudent: initCourseTransactionDTO.getNewStudents()){
            for(StudentDTO newStudent: groupStudent){
                studentService.createStudent(newStudent);
                newStudents.add(studentService.getStudentByIdNum(newStudent.getIdNum()));

                studentInBrigadeService.insert(new StudentInBrigadeDTO(newStudents.get(newStudents.size()-1).getId(), courseDTO.getId(), brigadesCount));
            }
            brigadesCount++;
        }

        return newStudents;
    }

    private void insertNewBrigade(InitCourseTransactionDTO initCourseTransactionDTO) throws SQLException {
        Integer brigadesAmount = brigadeService.findAll().size();

//        Insertando solo nuevas brigadas
        for(int i = brigadesAmount; i< initCourseTransactionDTO.getNewStudents().size(); i++){
            brigadeService.insert(new BrigadeDTO(0, i+1, 1));
        }
    }

    private void insertSubjectInCourse(InitCourseTransactionDTO initCourseTransactionDTO, CourseDTO courseDTO) throws SQLException {
        for(SubjectInCourseCompleteDTO subjects: initCourseTransactionDTO.getSubjectInCourseCompleteDTO()){
            subjectInCourseService.insert(new SubjectInCourseDTO(subjects.getSubjectDTO().getId(),
                    courseDTO.getId(), subjects.getYearID(), subjects.getAmountHours()));
        }
    }

    private CourseDTO insertCourse(InitCourseTransactionDTO initCourseTransactionDTO) throws SQLException {
        CourseDTO courseDTO = initCourseTransactionDTO.getSubjectInCourseCompleteDTO().get(0).getCourseDTO();

        courseService.insert(courseDTO);
        List<CourseDTO> list = courseService.findAll();

        return list.get(list.size()-1);
    }

}
