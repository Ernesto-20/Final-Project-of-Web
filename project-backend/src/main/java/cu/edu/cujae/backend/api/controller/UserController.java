package cu.edu.cujae.backend.api.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

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

import cu.edu.cujae.backend.core.dto.UserDTO;
import cu.edu.cujae.backend.core.email.EmailSenderService;
//import cu.edu.cujae.backend.core.email.EmailSenderService;
import cu.edu.cujae.backend.core.email.Mail;
import cu.edu.cujae.backend.core.service.UserService;
import freemarker.template.TemplateException;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    
     @Autowired
     private EmailSenderService emailService;

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> getUsers() throws SQLException {
        List<UserDTO> userList = userService.listUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String id) throws SQLException {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody UserDTO user) throws SQLException {
    	userService.createUser(user);
        sendMailToUserWithCredentials(user.getFullName(), user.getEmail());

        return ResponseEntity.ok("User Created");
    }

    @PutMapping("")
    public ResponseEntity<String> update(@RequestBody UserDTO user) throws SQLException {
        userService.updateUser(user);
        return ResponseEntity.ok("User Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws SQLException {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }

     private void sendMailToUserWithCredentials(String fullName, String email) {
     Mail mail = new Mail();
     mail.setMailTo(email);
     mail.setSubject("Registro de Usuario");
     mail.setTemplate("user-registration-template.ftl");
    
     Map<String, Object> model = new HashMap<String, Object>();
     model.put("name", fullName);
     mail.setProps(model);
    
     try {
    	 emailService.sendEmail(mail);
     } catch (MessagingException | IOException | TemplateException e) {
     e.printStackTrace();
     }
     }
}
