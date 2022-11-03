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
import cu.edu.cujae.backend.core.service.StudentService;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public ResponseEntity<List<StudentDTO>> getStudents() throws SQLException {
        List<StudentDTO> studentList = studentService.getStudents();
        return ResponseEntity.ok(studentList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable String id) throws SQLException {
        StudentDTO student = studentService.getStudentById(Integer.parseInt(id));
        return ResponseEntity.ok(student);
    }

    @GetMapping("/idnum/{idNum}")
    public ResponseEntity<StudentDTO> getStudentByIdNum(@PathVariable String idNum) throws SQLException {
        StudentDTO student = studentService.getStudentByIdNum(idNum);
        return ResponseEntity.ok(student);
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody StudentDTO student) throws SQLException {
        studentService.createStudent(student);
        return ResponseEntity.ok("Student Created");
    }

    @PutMapping("")
    public ResponseEntity<String> update(@RequestBody StudentDTO student) throws SQLException {
        studentService.updateStudent(student);
        return ResponseEntity.ok("Student Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws SQLException {
        studentService.deleteStudent(Integer.parseInt(id));
        return ResponseEntity.ok("Student deleted");
    }
}
