package capitalpulse.agregadordeinvestimentos.service;

import capitalpulse.agregadordeinvestimentos.controller.CreateUserDto;
import capitalpulse.agregadordeinvestimentos.model.User;
import capitalpulse.agregadordeinvestimentos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User createUser(CreateUserDto createUserDto) {
        User user = new User();
        user.setUsername(createUserDto.username());
        user.setEmail(createUserDto.email());
        user.setPassword(createUserDto.password());
        return userRepository.save(user);
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(UUID userId) {
        userRepository.deleteById(userId);
    }

}
