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

import cu.edu.cujae.backend.core.dto.BrigadeDTO;
import cu.edu.cujae.backend.core.service.BrigadeService;

@RestController
@RequestMapping("/api/v1/brigades")
public class BrigadeController {

    @Autowired
    private BrigadeService brigadeService;

    @GetMapping("")
    public ResponseEntity<List<BrigadeDTO>> getBrigades() throws SQLException {
        List<BrigadeDTO> brigadeList = brigadeService.findAll();
        return ResponseEntity.ok(brigadeList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrigadeDTO> getBrigadeById(@PathVariable String id) throws SQLException {
        BrigadeDTO Brigade = brigadeService.findById(Integer.parseInt(id));
        return ResponseEntity.ok(Brigade);
    }

    @GetMapping("/year/{yearId}")
    public ResponseEntity<List<BrigadeDTO>> getBrigadeByYearID(@PathVariable String yearId) throws SQLException {
        List<BrigadeDTO> brigadeList = brigadeService.findByYearId(Integer.parseInt(yearId));
        return ResponseEntity.ok(brigadeList);
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody BrigadeDTO Brigade) throws SQLException {
        brigadeService.insert(Brigade);
        return ResponseEntity.ok("Brigade Created");
    }

    @PutMapping("")
    public ResponseEntity<String> update(@RequestBody BrigadeDTO Brigade) throws SQLException {
        brigadeService.update(Brigade);
        return ResponseEntity.ok("Brigade Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws SQLException {
        brigadeService.delete(Integer.parseInt(id));
        return ResponseEntity.ok("Brigade deleted");
    }
}
