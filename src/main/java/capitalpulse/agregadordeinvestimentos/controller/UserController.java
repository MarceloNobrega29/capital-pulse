package capitalpulse.agregadordeinvestimentos.controller;

import capitalpulse.agregadordeinvestimentos.dto.AccountResponseDto;
import capitalpulse.agregadordeinvestimentos.dto.CreateAccountDto;
import capitalpulse.agregadordeinvestimentos.dto.CreateUserDto;
import capitalpulse.agregadordeinvestimentos.dto.UpdateUserDto;
import capitalpulse.agregadordeinvestimentos.entity.Account;
import capitalpulse.agregadordeinvestimentos.entity.User;
import capitalpulse.agregadordeinvestimentos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
        User newUser = userService.createUser(createUserDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable ("userId") String userId) {
        return userService.getUserById(userId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<User>> listUsers() {
        var users = userService.listUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable ("userId") String userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateById(@PathVariable ("userId") String userId, @RequestBody UpdateUserDto updateUserDto) {
        userService.updateById(userId, updateUserDto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<Account> createAccount(@PathVariable("userId") String userId, @RequestBody CreateAccountDto createAccountDto) {
        Account newAccount = userService.createAccount(userId, createAccountDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccount);
    }

    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountResponseDto>> listAccounts(@PathVariable("userId") String userId) {
        List<AccountResponseDto> accounts = userService.listAccounts(userId);
        return ResponseEntity.ok(accounts);
    }

}
