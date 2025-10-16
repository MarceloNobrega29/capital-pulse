package capitalpulse.agregadordeinvestimentos.controller;

import capitalpulse.agregadordeinvestimentos.model.User;
import capitalpulse.agregadordeinvestimentos.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
        User novoUser = userService.createUser(createUserDto);
        URI location = URI.create("/users/" + novoUser.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable ("userId") String userId) {
        return userService.getUserById(userId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


}
