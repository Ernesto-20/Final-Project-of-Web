package cu.edu.cujae.backend.api.controller;

import cu.edu.cujae.backend.core.dto.DropOutDTO;
import cu.edu.cujae.backend.core.service.DropOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dropout")
public class DropOutController {

    @Autowired
    private DropOutService dropOutService;

    @GetMapping("")
    public ResponseEntity<List<DropOutDTO>> getDropOuts() throws SQLException {
        List<DropOutDTO> dropOutDTOList = dropOutService.findAll();
        return ResponseEntity.ok(dropOutDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DropOutDTO> getDropOutById(@PathVariable String id) throws SQLException {
        DropOutDTO user = dropOutService.findById(Integer.parseInt(id));
        return ResponseEntity.ok(user);
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody DropOutDTO dropOutDTO) throws SQLException {
        dropOutService.insert(dropOutDTO);
        return ResponseEntity.ok("DropOut Created");
    }

    @PutMapping("")
    public ResponseEntity<String> update(@RequestBody DropOutDTO dropOutDTO) throws SQLException {
        dropOutService.update(dropOutDTO);
        return ResponseEntity.ok("DropOut Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws SQLException {
        dropOutService.delete(Integer.parseInt(id));
        return ResponseEntity.ok("User deleted");
    }
}
