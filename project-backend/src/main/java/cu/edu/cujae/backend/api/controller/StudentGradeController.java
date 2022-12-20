package cu.edu.cujae.backend.api.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/all")
    public ResponseEntity<List<StudentGradeDTO>> getAllStudentGrade() throws SQLException{
        List<StudentGradeDTO> studentGrades = studentGradeService.getStudentGrades();
        return ResponseEntity.ok(studentGrades);
    }
}
