package capitalpulse.agregadordeinvestimentos.service;

import capitalpulse.agregadordeinvestimentos.dto.CreateAccountDto;
import capitalpulse.agregadordeinvestimentos.dto.CreateUserDto;
import capitalpulse.agregadordeinvestimentos.dto.UpdateUserDto;
import capitalpulse.agregadordeinvestimentos.entity.Account;
import capitalpulse.agregadordeinvestimentos.entity.AccountStock;
import capitalpulse.agregadordeinvestimentos.entity.BillingAddress;
import capitalpulse.agregadordeinvestimentos.entity.User;
import capitalpulse.agregadordeinvestimentos.repository.AccountRepository;
import capitalpulse.agregadordeinvestimentos.repository.BillingAddressRepository;
import capitalpulse.agregadordeinvestimentos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BillingAddressRepository billingAddressRepository;


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

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void deleteUserById(String userId) {
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        userRepository.delete(user);
    }

    public void updateById(String userId, UpdateUserDto updateUserDto) {
        var id = UUID.fromString(userId);
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        user.setUsername(updateUserDto.username());
        user.setPassword(updateUserDto.password());

        userRepository.save(user);
    }

    public Account createAccount(String userId, CreateAccountDto createAccountDto) {

        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (user.getAccounts() == null) {
            user.setAccounts(new ArrayList<>());
        }

        Account account = new Account();
        account.setUser(user);
        account.setDescription(createAccountDto.description());
        account.setAccountStocks(new ArrayList<>());

        Account accountCreated = accountRepository.save(account);

        BillingAddress address = new BillingAddress();
        address.setAccount(accountCreated);
        address.setStreet(createAccountDto.street());
        address.setNumber(createAccountDto.number());

        billingAddressRepository.save(address);

        return accountCreated;
    }
}
