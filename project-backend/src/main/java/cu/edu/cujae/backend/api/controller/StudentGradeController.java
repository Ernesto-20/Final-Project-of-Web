package cu.edu.cujae.backend.api.controller;

import java.sql.SQLException;
import java.util.List;

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
}
