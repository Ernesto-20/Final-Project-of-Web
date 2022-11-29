//package cu.edu.cujae.backend.api.controller;
//
//import java.sql.SQLException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import cu.edu.cujae.backend.core.dto.StudentGradeDTO;
//import cu.edu.cujae.backend.core.service.StudentGradeService;
//
//@RestController
//@RequestMapping("/api/v1/studentgrades")
//public class StudentGradeController {
//
//    @Autowired
//    private StudentGradeService studentGradeService;
//
//    @GetMapping("/{studentId}")
//    public ResponseEntity<StudentGradeDTO> getStudentGradeById(@PathVariable String studentId) throws SQLException {
//        StudentGradeDTO studentGrades = studentGradeService.getStudentGradesById(Integer.parseInt(studentId));
//        return ResponseEntity.ok(studentGrades);
//    }
//}
