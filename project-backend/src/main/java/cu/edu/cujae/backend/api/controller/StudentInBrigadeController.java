package cu.edu.cujae.backend.api.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cu.edu.cujae.backend.core.dto.StudentDTO;
import cu.edu.cujae.backend.core.dto.StudentInBrigadeDTO;
import cu.edu.cujae.backend.core.service.StudentInBrigadeService;

@RestController
@RequestMapping("/api/v1/studentinbrigades")
// ?TODO: SE QUEDA PARA MODIFICACIÓN POSTERIOR
public class StudentInBrigadeController {

    @Autowired
    private StudentInBrigadeService studentInBrigadeService;

    @GetMapping("")
    public ResponseEntity<List<StudentInBrigadeDTO>> getStudentInBrigades() throws SQLException {
        List<StudentInBrigadeDTO> studentInBrigadeList = studentInBrigadeService.findAll();
        return ResponseEntity.ok(studentInBrigadeList);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentInBrigadeDTO>> getStudentInBrigadeByStudentId(@PathVariable String studentId)
            throws SQLException {
        List<StudentInBrigadeDTO> studentInBrigadeList = studentInBrigadeService
                .findByStudentId(Integer.parseInt(studentId));
        return ResponseEntity.ok(studentInBrigadeList);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<StudentInBrigadeDTO>> getStudentInBrigadeByCourseId(@PathVariable String courseId)
            throws SQLException {
        List<StudentInBrigadeDTO> studentInBrigadeList = studentInBrigadeService
                .findByCourseId(Integer.parseInt(courseId));
        return ResponseEntity.ok(studentInBrigadeList);
    }

    @GetMapping("/brigade/{brigadeId}")
    public ResponseEntity<List<StudentInBrigadeDTO>> getStudentInBrigadeByYearID(@PathVariable String brigadeId)
            throws SQLException {
        List<StudentInBrigadeDTO> studentInBrigadeList = studentInBrigadeService
                .findByBrigadeId(Integer.parseInt(brigadeId));
        return ResponseEntity.ok(studentInBrigadeList);
    }

    @GetMapping("/course/{courseId}/brigade/{brigadeId}")
    public ResponseEntity<List<StudentDTO>> getStudentByBrigadeId(@PathVariable String courseId,
            @PathVariable String brigadeId)
            throws SQLException {
        List<StudentDTO> studentList = studentInBrigadeService
                .findStudentByBrigadeId(Integer.parseInt(courseId), Integer.parseInt(brigadeId));
        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/course/{courseId}/student/{studentId}")
    public ResponseEntity<StudentInBrigadeDTO> getBrigadeByCourseAndStudent(@PathVariable String courseId,
            @PathVariable String studentId)
            throws SQLException {
        StudentInBrigadeDTO studentInBrigadeList = studentInBrigadeService
                .findBrigadeByCourseAndStudent(Integer.parseInt(courseId), Integer.parseInt(studentId));
        return ResponseEntity.ok(studentInBrigadeList);
    }

    // @GetMapping("/course/{courseId}/student/{studentId}")
    // public ResponseEntity<Integer> getBrigadeByCourseAndStudent(@RequestBody
    // StudentInBrigadeDTO studentInBrigadeDTO)
    // throws SQLException {

    // return
    // ResponseEntity.ok(studentInBrigadeService.findNumberScale(studentInBrigadeDTO));
    // }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody StudentInBrigadeDTO studentInBrigade) throws SQLException {
        studentInBrigadeService.insert(studentInBrigade);
        return ResponseEntity.ok("StudentInBrigade Created");
    }

    @PutMapping("")
    public ResponseEntity<String> updateBrigade(@RequestBody StudentInBrigadeDTO studentInBrigade) throws SQLException {
        studentInBrigadeService.updateBrigade(studentInBrigade);
        return ResponseEntity.ok("StudentInBrigade Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@RequestBody StudentInBrigadeDTO studentInBrigade) throws SQLException {
        studentInBrigadeService.delete(studentInBrigade);
        return ResponseEntity.ok("StudentInBrigade deleted");
    }
}
