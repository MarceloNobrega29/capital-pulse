package capitalpulse.agregadordeinvestimentos.service;

import capitalpulse.agregadordeinvestimentos.controller.CreateUserDto;
import capitalpulse.agregadordeinvestimentos.controller.UpdateUserDto;
import capitalpulse.agregadordeinvestimentos.entity.User;
import capitalpulse.agregadordeinvestimentos.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Nested
    class createUser {

        @Test
        @DisplayName("Should create a user with success")
        void shouldCreateAUserWithSuccess(){

            var user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@email.com",
                    "password",
                    Instant.now(),
                    null
            );

            doReturn(user).when(userRepository).save(userArgumentCaptor.capture());
            var input = new CreateUserDto(
                    "username",
                    "email@email.com",
                    "321"
            );

            var output = userService.createUser(input);

            assertNotNull(output);

            var userCaptured = userArgumentCaptor.getValue();

            assertEquals(input.username(), userCaptured.getUsername());
            assertEquals(input.email(), userCaptured.getEmail());
            assertEquals(input.password(), userCaptured.getPassword());
        }

        @Test
        @DisplayName("Should throw exeption when error occurs")
        void shouldThrowExeptionWhenErrorOccurs(){

            doThrow(new RuntimeException()).when(userRepository).save(any());
            var input = new CreateUserDto(
                    "username",
                    "email@email.com",
                    "321"
            );

            assertThrows(RuntimeException.class, () -> userService.createUser(input));

        }
    }

    @Nested
    class getUserById {

        @Test
        @DisplayName("Should get user by id with success when optional is empty")
        void shouldGetUserByIdWithSuccessWhenOptionalIsEmpty() {

            var userId = UUID.randomUUID();
            doReturn(Optional.empty())
                    .when(userRepository).
                    findById(uuidArgumentCaptor.capture());

            var output = userService.getUserById(userId.toString());

            assertTrue(output.isEmpty());
            assertEquals(userId, uuidArgumentCaptor.getValue());


        }
    }

    @Nested
    class listUsers {

        @Test
        @DisplayName("Should return all users with success")
        void shouldReturnAllUsersWithSuccess() {

            var user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@email.com",
                    "password",
                    Instant.now(),
                    null
            );
            var userList = List.of(user);
            doReturn(List.of(user))
                    .when(userRepository)
                    .findAll();

            var output = userService.listUsers();

            assertNotNull(output);
            assertEquals(userList.size(),output.size());

        }
    }

    @Nested
    class deleteUserById {

        @Test
        @DisplayName("Should delete user with success")
        void shouldDeleteUserWithSuccess() {

            var userId = UUID.randomUUID();
            var user = new User();
            user.setUserId(userId);

            when(userRepository.findById(uuidArgumentCaptor.capture()))
                    .thenReturn(Optional.of(user));


            doNothing()
                    .when(userRepository)
                    .delete(any(User.class));

            userService.deleteUserById(userId.toString());

            assertEquals(userId, uuidArgumentCaptor.getValue());

            verify(userRepository).delete(user);
        }
    }

    @Nested
    class updateById {

        @Test
        @DisplayName("Should update user by id when user exists and username and password is filled")
        void shouldUpdateUserByIdWhenUserExistsAndUsernameAndPasswordIsFilled() {

            var updateUserDto = new UpdateUserDto(
                    "newusername",
                    "newpassword"
            );

            var user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@email.com",
                    "password",
                    Instant.now(),
                    null
            );

            when(userRepository.findById(any(UUID.class)))
                    .thenReturn(Optional.of(user));

            when(userRepository.save(any(User.class)))
                    .thenReturn(user);

            userService.updateById(user.getUserId().toString(), updateUserDto);

            verify(userRepository).findById(uuidArgumentCaptor.capture());
            assertEquals(user.getUserId(), uuidArgumentCaptor.getValue());

            verify(userRepository).save(userArgumentCaptor.capture());

            var userCaptured = userArgumentCaptor.getValue();
            assertEquals(updateUserDto.username(), userCaptured.getUsername());
            assertEquals(updateUserDto.password(), userCaptured.getPassword());
        }
    }
}