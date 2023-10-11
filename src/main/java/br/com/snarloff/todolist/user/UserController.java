package br.com.snarloff.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/users")
public class UserController {

    /*
        * @Autowired // Annotation to inject an instance of IUserRepository
        * private IUserRepository userRepository;
    */

    private final IUserRepository userRepository;
    public UserController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/")
    public ResponseEntity<Object> create(@RequestBody UserModel user) {
        var foundedUser = this.userRepository.findByUsername(user.getUsername());

        if (foundedUser != null) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "Username already exists");
            response.put("statusCode", 400);
            return ResponseEntity.badRequest().body(response); // 400
        }

        var password = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(password);

        var userCreated = this.userRepository.save(user);
        return ResponseEntity.ok().body(userCreated); // 200
    }
}
