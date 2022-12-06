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

import cu.edu.cujae.backend.core.dto.YearDTO;
import cu.edu.cujae.backend.core.service.YearService;

@RestController
@RequestMapping("/api/v1/years")
public class YearController {

    @Autowired
    private YearService yearService;

    @GetMapping("")
    public ResponseEntity<List<YearDTO>> getYears() throws SQLException {
        List<YearDTO> yearList = yearService.findAll();
        return ResponseEntity.ok(yearList);
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody YearDTO year) throws SQLException {
        yearService.insert(year);
        return ResponseEntity.ok("Year Created");
    }

    @PutMapping("")
    public ResponseEntity<String> update(@RequestBody YearDTO year) throws SQLException {
        yearService.update(year);
        return ResponseEntity.ok("Year Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws SQLException {
        yearService.delete(Integer.parseInt(id));
        return ResponseEntity.ok("Year deleted");
    }
}
