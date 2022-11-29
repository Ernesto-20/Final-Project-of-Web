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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cu.edu.cujae.backend.core.dto.StatusDTO;
import cu.edu.cujae.backend.core.service.StatusService;

@RestController
@RequestMapping("/api/v1/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping("")
    public ResponseEntity<List<StatusDTO>> getStatus() throws SQLException {
        List<StatusDTO> statusList = statusService.findAll();
        return ResponseEntity.ok(statusList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusDTO> getStatusById(@PathVariable Integer id) throws SQLException {
        StatusDTO Status = statusService.findById(id);
        return ResponseEntity.ok(Status);
    }

    @GetMapping("/")
    public ResponseEntity<StatusDTO> getStatusByDescription(@RequestParam String description) throws SQLException {
        StatusDTO status = statusService.findByDescription(description);
        return ResponseEntity.ok(status);
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody StatusDTO Status) throws SQLException {
        statusService.insert(Status);
        return ResponseEntity.ok("Status Created");
    }

    @PutMapping("")
    public ResponseEntity<String> update(@RequestBody StatusDTO Status) throws SQLException {
        statusService.update(Status);
        return ResponseEntity.ok("Status Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws SQLException {
        statusService.delete(id);
        return ResponseEntity.ok("Status deleted");
    }
}
