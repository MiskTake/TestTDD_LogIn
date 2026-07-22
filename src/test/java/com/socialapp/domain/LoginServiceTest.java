package com.socialapp.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import com.socialapp.domain.exception.InvalidCredentialsException;
import com.socialapp.domain.exception.UserNotFoundException;

@DisplayName("Servicio de Login (LoginService)")
public class LoginServiceTest {

    private UserRepository repositoryMock;
    private LoginService loginService;

    @BeforeEach
    public void setUp() {
        repositoryMock = Mockito.mock(UserRepository.class);
        loginService = new LoginService(repositoryMock);
    }

    @Test
    @DisplayName("Debe lanzar UserNotFoundException cuando el email no está registrado")
    public void shouldThrowUserNotFoundExceptionWhenEmailIsNotRegistered() {
        // Arrange
        Mockito.when(repositoryMock.existsByEmail("noexiste@gmail.com")).thenReturn(false);

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            loginService.login("noexiste@gmail.com", "contraseñaSegura123");
        });
    }

    @Test
    @DisplayName("Debe lanzar InvalidCredentialsException cuando la contraseña no coincide")
    public void shouldThrowInvalidCredentialsExceptionWhenPasswordDoesNotMatch() {
        // Arrange
        Mockito.when(repositoryMock.existsByEmail("usuario@gmail.com")).thenReturn(true);
        Mockito.when(repositoryMock.findByEmail("usuario@gmail.com")).thenReturn(new User("usuario@gmail.com", "contraseñaCorrecta123"));

        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () -> {
            loginService.login("usuario@gmail.com", "contraseñaIncorrecta123");
        });
    }

    @Test
    @DisplayName("No debe lanzar ninguna excepción cuando el email y la contraseña son correctos")
    public void shouldNotThrowWhenEmailAndPasswordAreCorrect() {
        // Arrange
        Mockito.when(repositoryMock.existsByEmail("usuario@gmail.com")).thenReturn(true);
        Mockito.when(repositoryMock.findByEmail("usuario@gmail.com")).thenReturn(new User("usuario@gmail.com", "contraseñaSegura123"));

        // Act & Assert
        assertDoesNotThrow(() -> {
            loginService.login("usuario@gmail.com", "contraseñaSegura123");
        });
    }
}
