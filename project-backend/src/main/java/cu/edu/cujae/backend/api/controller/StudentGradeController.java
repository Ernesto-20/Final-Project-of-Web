package cu.edu.cujae.backend.api.controller;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.StudentDTO;
import cu.edu.cujae.backend.core.dto.StudentGradeCourseIdDTO;
import cu.edu.cujae.backend.core.dto.StudentGradeOnlyIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cu.edu.cujae.backend.core.dto.StudentGradeDTO;
import cu.edu.cujae.backend.core.service.StudentGradeService;

@RestController
@RequestMapping("/api/v1/studentgrades")
public class StudentGradeController {

    @Autowired
    private StudentGradeService studentGradeService;

    @GetMapping("/")
    public ResponseEntity<List<StudentGradeDTO>> getStudentGradeById(@RequestParam Integer studentId,
            @RequestParam Integer yearId) throws SQLException {
        List<StudentGradeDTO> studentGrades = studentGradeService.getStudentGradesByYearId(studentId, yearId);
        return ResponseEntity.ok(studentGrades);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<StudentGradeOnlyIdDTO>> getStudentGradeByCourseId(@PathVariable Integer courseId)
            throws SQLException {
        List<StudentGradeOnlyIdDTO> studentGrades = studentGradeService.getStudentGradesByCourseId(courseId);
        return ResponseEntity.ok(studentGrades);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentGradeDTO>> getAllStudentGrade() throws SQLException{
        List<StudentGradeDTO> studentGrades = studentGradeService.getStudentGrades();
        return ResponseEntity.ok(studentGrades);
    }

    @GetMapping("/all_course_id")
    public ResponseEntity<List<StudentGradeCourseIdDTO>> getAllStudentGradeCourseId() throws SQLException{
        List<StudentGradeCourseIdDTO> studentGrades = studentGradeService.getStudentGradesCourseId();
        return ResponseEntity.ok(studentGrades);
    }

//    @PutMapping("/")
//    public ResponseEntity<String> update(@RequestBody StudentGradeOnlyIdDTO studentGrade) throws SQLException{
//        studentGradeService.updateGrade(studentGrade);
//        System.out.println(studentGrade.getGradeValue());
//        return ResponseEntity.ok("StudentGrade updated");
//    }

    @PutMapping("")
    public ResponseEntity<String> update(@RequestBody StudentGradeOnlyIdDTO studentGrade) throws SQLException {
        studentGradeService.updateGrade(studentGrade);
        return ResponseEntity.ok("StudentGrade updated");
    }
}
