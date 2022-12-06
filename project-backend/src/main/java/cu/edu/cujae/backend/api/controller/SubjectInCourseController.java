package cu.edu.cujae.backend.api.controller;

import java.sql.SQLException;
import java.util.List;

import cu.edu.cujae.backend.core.dto.SubjectInCourseNamedDTO;
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

import cu.edu.cujae.backend.core.dto.SubjectInCourseDTO;
import cu.edu.cujae.backend.core.service.SubjectInCourseService;

@RestController
@RequestMapping("/api/v1/subjectincourses")
public class SubjectInCourseController {

    @Autowired
    private SubjectInCourseService subjectInCourseService;

    @GetMapping("")
    public ResponseEntity<List<SubjectInCourseDTO>> getSubjectInCourses() throws SQLException {
        List<SubjectInCourseDTO> subjectInCourseList = subjectInCourseService.findAll();
        return ResponseEntity.ok(subjectInCourseList);
    }

    @GetMapping("/named")
    public ResponseEntity<List<SubjectInCourseNamedDTO>> getSubjectsInCourseNamed() throws SQLException {
        List<SubjectInCourseNamedDTO> subjectInCourseList = subjectInCourseService.findAllNamed();
        return ResponseEntity.ok(subjectInCourseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<SubjectInCourseDTO>> getSubjectInCourseById(@PathVariable String id)
            throws SQLException {
        List<SubjectInCourseDTO> subjectInCourseList = subjectInCourseService.findBySubjectId(Integer.parseInt(id));
        return ResponseEntity.ok(subjectInCourseList);
    }

    @GetMapping("/named/{id}")
    public ResponseEntity<List<SubjectInCourseNamedDTO>> getSubjectsInCourseByIdNamed(@PathVariable int id) throws SQLException{
        List<SubjectInCourseNamedDTO> subjectsInCourse = subjectInCourseService.findNamedBySubjectId(id);
        return ResponseEntity.ok(subjectsInCourse);
    }

    @GetMapping("course/{courseId}")
    public ResponseEntity<List<SubjectInCourseDTO>> getSubjectInCourseByCourseId(@PathVariable String courseId)
            throws SQLException {
        List<SubjectInCourseDTO> subjectInCourseList = subjectInCourseService
                .findByCourseId(Integer.parseInt(courseId));
        return ResponseEntity.ok(subjectInCourseList);
    }

    @GetMapping("/course/named/{id}")
    public ResponseEntity<List<SubjectInCourseNamedDTO>> getSubjectsInCourseByCourseIdNamed(@PathVariable int id) throws SQLException{
        List<SubjectInCourseNamedDTO> subjectsInCourse = subjectInCourseService.findNamedByCourseId(id);
        return ResponseEntity.ok(subjectsInCourse);
    }

    @GetMapping("/year/{yearId}")
    public ResponseEntity<List<SubjectInCourseDTO>> getSubjectInCourseByStudentId(@PathVariable String yearId)
            throws SQLException {
        List<SubjectInCourseDTO> subjectInCourseList = subjectInCourseService
                .findByYearId(Integer.parseInt(yearId));
        return ResponseEntity.ok(subjectInCourseList);
    }

    @GetMapping("/year/named/{id}")
    public ResponseEntity<List<SubjectInCourseNamedDTO>> getSubjectsInCourseByYearIdNamed(@PathVariable int id) throws SQLException{
        List<SubjectInCourseNamedDTO> subjectsInCourse = subjectInCourseService.findNamedByYearId(id);
        return ResponseEntity.ok(subjectsInCourse);
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody SubjectInCourseDTO subjectInCourse) throws SQLException {
        subjectInCourseService.insert(subjectInCourse);
        return ResponseEntity.ok("SubjectInCourse Created");
    }

    @PutMapping("")
    public ResponseEntity<String> updateHoursLong(@RequestBody SubjectInCourseDTO subjectInCourse) throws SQLException {
        subjectInCourseService.updateHoursLong(subjectInCourse);
        return ResponseEntity.ok("SubjectInCourse Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@RequestBody SubjectInCourseDTO subjectInCourse) throws SQLException {
        subjectInCourseService.delete(subjectInCourse);
        return ResponseEntity.ok("SubjectInCourse deleted");
    }

    // ?TODO: NO SE HA IMPLEMENTADO ESTE MÉTODO
    // LinkedList<SubjectInCourseDTO> findByCourseAndYearId(int course_id, int
    // year_id) throws SQLException;
}
