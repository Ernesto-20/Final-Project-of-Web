package cu.edu.cujae.backend.api.controller;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.StudentDropOutNamedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cu.edu.cujae.backend.core.dto.StudentDropOutDTO;
import cu.edu.cujae.backend.core.service.StudentDropOutService;

@RestController
@RequestMapping("/api/v1/studentdropouts")
public class StudentDropOutController {

    @Autowired
    private StudentDropOutService studentDropOutService;

    @GetMapping("")
    public ResponseEntity<List<StudentDropOutDTO>> getStudentDropOuts() throws SQLException {
        List<StudentDropOutDTO> studentDropOutList = studentDropOutService.findAll();
        return ResponseEntity.ok(studentDropOutList);
    }

    @GetMapping("/named")
    public ResponseEntity<List<StudentDropOutNamedDTO>> getNamedStudentDropOuts() throws SQLException {
        List<StudentDropOutNamedDTO> studentDropOutList = studentDropOutService.findAllNamed();
        return ResponseEntity.ok(studentDropOutList);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentDropOutDTO>> getStudentDropOutByStudentId(@PathVariable String studentId)
            throws SQLException {
        List<StudentDropOutDTO> studentDropOutList = studentDropOutService.findByStudentId(Integer.parseInt(studentId));
        return ResponseEntity.ok(studentDropOutList);
    }

    @GetMapping("/named/student/{studentId}")
    public ResponseEntity<List<StudentDropOutNamedDTO>> getNamedStudentDropOutByStudentId(@PathVariable String studentId)
            throws SQLException {
        List<StudentDropOutNamedDTO> studentDropOutList = studentDropOutService.findNamedByStudentId(Integer.parseInt(studentId));
        return ResponseEntity.ok(studentDropOutList);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<StudentDropOutDTO>> getStudentDropOutByCourseId(@PathVariable String courseId)
            throws SQLException {
        List<StudentDropOutDTO> studentDropOutList = studentDropOutService.findByCourseId(Integer.parseInt(courseId));
        return ResponseEntity.ok(studentDropOutList);
    }

    @GetMapping("/named/course/{courseId}")
    public ResponseEntity<List<StudentDropOutNamedDTO>> getNamedStudentDropOutByCourseId(@PathVariable String courseId)
            throws SQLException {
        List<StudentDropOutNamedDTO> studentDropOutList = studentDropOutService.findNamedByCourseId(Integer.parseInt(courseId));
        return ResponseEntity.ok(studentDropOutList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<StudentDropOutDTO>> getStudentDropOutById(@PathVariable String id)
            throws SQLException {
        List<StudentDropOutDTO> studentDropOutList = studentDropOutService.findByDropOutId(Integer.parseInt(id));
        return ResponseEntity.ok(studentDropOutList);
    }

    @GetMapping("/named/{id}")
    public ResponseEntity<List<StudentDropOutNamedDTO>> getNamedStudentDropOutById(@PathVariable String id)
            throws SQLException {
        List<StudentDropOutNamedDTO> studentDropOutList = studentDropOutService.findNamedByDropOutId(Integer.parseInt(id));
        return ResponseEntity.ok(studentDropOutList);
    }

    @PostMapping("")
    public ResponseEntity<String> createStudentDropOut(@RequestBody StudentDropOutDTO studentDropOut) throws SQLException {
        studentDropOutService.insert(studentDropOut);
        return ResponseEntity.ok("StudentDropOut Created");
    }

    @PutMapping("")
    public ResponseEntity<String> updateStudentDropOut(@RequestBody StudentDropOutDTO studentDropOut) throws SQLException {
        studentDropOutService.updateDropOut(studentDropOut);
        return ResponseEntity.ok("StudentDropOut Updated");
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteStudentDropOut(@RequestParam Integer dropoutId, @RequestParam Integer courseId, @RequestParam Integer studentId) throws SQLException {
    	StudentDropOutDTO studentDropOut = new StudentDropOutDTO(dropoutId, courseId, studentId);
    	
    	studentDropOutService.delete(studentDropOut);
        return ResponseEntity.ok("StudentDropOut deleted");
    }
}
