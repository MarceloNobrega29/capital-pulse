package capitalpulse.agregadordeinvestimentos.controller;

import capitalpulse.agregadordeinvestimentos.model.User;
import capitalpulse.agregadordeinvestimentos.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void createUser(@RequestBody CreateUserDto createUserDto) {
        userService.createUser(createUserDto);
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
