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

import cu.edu.cujae.backend.core.dto.GradeDTO;
import cu.edu.cujae.backend.core.service.GradeService;

@RestController
@RequestMapping("/api/v1/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping("")
    public ResponseEntity<List<GradeDTO>> getGrades() throws SQLException {
        List<GradeDTO> gradeList = gradeService.findAll();
        return ResponseEntity.ok(gradeList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GradeDTO> getGradeById(@PathVariable String id) throws SQLException {
        GradeDTO grade = gradeService.findById(Integer.parseInt(id));
        return ResponseEntity.ok(grade);
    }

    @GetMapping("/scale/{scaleId}")
    public ResponseEntity<GradeDTO> getGradeByScale(@PathVariable String scaleId) throws SQLException {
        GradeDTO gradeList = gradeService.findByScale(scaleId);
        return ResponseEntity.ok(gradeList);
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody GradeDTO grade) throws SQLException {
        gradeService.insert(grade);
        return ResponseEntity.ok("Grade Created");
    }

    @PutMapping("")
    public ResponseEntity<String> update(@RequestBody GradeDTO grade) throws SQLException {
        gradeService.update(grade);
        return ResponseEntity.ok("Grade Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws SQLException {
        gradeService.delete(Integer.parseInt(id));
        return ResponseEntity.ok("Grade deleted");
    }
}
