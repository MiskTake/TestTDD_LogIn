package com.socialapp.application.usecase;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import com.socialapp.domain.entity.User;
import com.socialapp.domain.exception.InvalidCredentialsException;
import com.socialapp.domain.exception.UserNotFoundException;
import com.socialapp.domain.repository.UserRepository;
import com.socialapp.domain.valueobject.Email;
import com.socialapp.domain.valueobject.Password;

@DisplayName("Caso de Uso: Inicio de Sesión (LoginUserUseCase)")
public class LoginUserUseCaseTest {

    private UserRepository repositoryMock;
    private LoginUserUseCase useCase;

    @BeforeEach
    public void setUp() {
        repositoryMock = Mockito.mock(UserRepository.class);
        useCase = new LoginUserUseCase(repositoryMock);
    }

    @Test
    @DisplayName("Debe lanzar UserNotFoundException cuando el email no está registrado")
    public void shouldThrowUserNotFoundExceptionWhenEmailIsNotRegistered() {
        // Arrange
        Mockito.when(repositoryMock.existsByEmail(new Email("noexiste@gmail.com"))).thenReturn(false);

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            useCase.execute("noexiste@gmail.com", "contraseñaSegura123");
        });
    }

    @Test
    @DisplayName("Debe lanzar InvalidCredentialsException cuando la contraseña no coincide")
    public void shouldThrowInvalidCredentialsExceptionWhenPasswordDoesNotMatch() {
        // Arrange
        Mockito.when(repositoryMock.existsByEmail(new Email("usuario@gmail.com"))).thenReturn(true);
        Mockito.when(repositoryMock.findByEmail(new Email("usuario@gmail.com")))
                .thenReturn(new User(new Email("usuario@gmail.com"), new Password("contraseñaCorrecta123")));

        // Act & Assert
        assertThrows(InvalidCredentialsException.class, () -> {
            useCase.execute("usuario@gmail.com", "contraseñaIncorrecta123");
        });
    }

    @Test
    @DisplayName("No debe lanzar ninguna excepción cuando el email y la contraseña son correctos")
    public void shouldNotThrowWhenEmailAndPasswordAreCorrect() {
        // Arrange
        Mockito.when(repositoryMock.existsByEmail(new Email("usuario@gmail.com"))).thenReturn(true);
        Mockito.when(repositoryMock.findByEmail(new Email("usuario@gmail.com")))
                .thenReturn(new User(new Email("usuario@gmail.com"), new Password("contraseñaSegura123")));

        // Act & Assert
        assertDoesNotThrow(() -> {
            useCase.execute("usuario@gmail.com", "contraseñaSegura123");
        });
    }
}
