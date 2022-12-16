package cu.edu.cujae.backend.api.controller;

import cu.edu.cujae.backend.core.dto.GradeDTO;
import cu.edu.cujae.backend.core.dto.InitCourseTransactionDTO;
import cu.edu.cujae.backend.core.service.GradeService;
import cu.edu.cujae.backend.core.service.InitCourseTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/initcourse")
public class InitCourseTransactionController {
    @Autowired
    private InitCourseTransactionService initCourseTransactionService;

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody InitCourseTransactionDTO initCourseTransactionDTO) throws SQLException {
        initCourseTransactionService.initCourse(initCourseTransactionDTO);
        return ResponseEntity.ok("Started Course");
    }

}
