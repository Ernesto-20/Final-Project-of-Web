package cu.edu.cujae.backend.api.controller;

import cu.edu.cujae.backend.core.dto.SubjectDTO;
import cu.edu.cujae.backend.core.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class SubjectController {
	
	@Autowired
	private SubjectService subjectService;
	
	@GetMapping("")
    public ResponseEntity<List<SubjectDTO>> getSubjects() throws SQLException {
		List<SubjectDTO> subjectList = subjectService.getSubjects();
        return ResponseEntity.ok(subjectList);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable String id) throws SQLException {
        SubjectDTO subject = subjectService.getSubjectById(id);
        return ResponseEntity.ok(subject);
    }
	
	@PostMapping("")
    public ResponseEntity<String> create(@RequestBody SubjectDTO subject) throws SQLException {
        subjectService.createSubject(subject);
        return ResponseEntity.ok("Subject Created");
    }
	
	@PutMapping("")
    public ResponseEntity<String> update(@RequestBody SubjectDTO subject) throws SQLException {
        subjectService.updateSubject(subject);
        return ResponseEntity.ok("Subject Updated");
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws SQLException {
        subjectService.deleteSubject(id);
        return ResponseEntity.ok("Subject Deleted");
    }
}
