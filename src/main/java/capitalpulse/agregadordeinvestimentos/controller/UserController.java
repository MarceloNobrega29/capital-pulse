package capitalpulse.agregadordeinvestimentos.controller;

import capitalpulse.agregadordeinvestimentos.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody String body) {

        return null;
    }

    @GetMapping("/{userIdE}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {

        return null;
    }



}
