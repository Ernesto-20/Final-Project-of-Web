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

import cu.edu.cujae.backend.core.dto.CourseDTO;
import cu.edu.cujae.backend.core.service.CourseService;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("")
    public ResponseEntity<List<CourseDTO>> getCourses() throws SQLException {
        List<CourseDTO> courseList = courseService.findAll();
        return ResponseEntity.ok(courseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable String id) throws SQLException {
        CourseDTO Course = courseService.findById(Integer.parseInt(id));
        return ResponseEntity.ok(Course);
    }

    @GetMapping("/last")
    public ResponseEntity<CourseDTO> getLastCourse() throws SQLException {
        CourseDTO courseList = courseService.findLast();
        return ResponseEntity.ok(courseList);
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody CourseDTO Course) throws SQLException {
        courseService.insert(Course);
        return ResponseEntity.ok("Course Created");
    }

    @PutMapping("")
    public ResponseEntity<String> update(@RequestBody CourseDTO Course) throws SQLException {
        courseService.update(Course);
        return ResponseEntity.ok("Course Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws SQLException {
        courseService.delete(Integer.parseInt(id));
        return ResponseEntity.ok("Course deleted");
    }
}
