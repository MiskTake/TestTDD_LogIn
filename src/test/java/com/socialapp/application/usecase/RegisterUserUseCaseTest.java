package com.socialapp.application.usecase;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import com.socialapp.domain.entity.User;
import com.socialapp.domain.exception.EmailAlreadyRegisteredException;
import com.socialapp.domain.exception.InvalidEmailException;
import com.socialapp.domain.exception.WeakPasswordException;
import com.socialapp.domain.repository.UserRepository;
import com.socialapp.domain.valueobject.Email;
import com.socialapp.domain.valueobject.Password;

@DisplayName("Caso de Uso: Registro de Usuario (RegisterUserUseCase)")
public class RegisterUserUseCaseTest {

    private UserRepository repositoryMock;
    private RegisterUserUseCase useCase;

    @BeforeEach
    public void setUp() {
        repositoryMock = Mockito.mock(UserRepository.class);
        useCase = new RegisterUserUseCase(repositoryMock);
    }

    @Test
    @DisplayName("Debe guardar al usuario exactamente una vez cuando los datos son válidos y el email no está registrado")
    public void shouldSaveUserExactlyOnceWhenDataIsValidAndEmailIsNotRegistered() {
        // Arrange
        Mockito.when(repositoryMock.existsByEmail(new Email("nuevo@gmail.com"))).thenReturn(false);

        // Act
        useCase.execute("nuevo@gmail.com", "contraseñaSegura123");

        // Assert: el id lo genera el caso de uso, así que se captura al usuario guardado
        // y se verifica su comportamiento en vez de comparar contra un id predefinido.
        ArgumentCaptor<User> savedUser = ArgumentCaptor.forClass(User.class);
        Mockito.verify(repositoryMock, Mockito.times(1)).save(savedUser.capture());
        assertEquals(new Email("nuevo@gmail.com"), savedUser.getValue().email());
        assertTrue(savedUser.getValue().authenticatesWith(new Password("contraseñaSegura123")));
    }

    @Test
    @DisplayName("Debe lanzar EmailAlreadyRegisteredException cuando el email ya está registrado")
    public void shouldThrowEmailAlreadyRegisteredExceptionWhenEmailIsAlreadyRegistered() {
        // Arrange
        Mockito.when(repositoryMock.existsByEmail(new Email("existente@gmail.com"))).thenReturn(true);

        // Act & Assert
        assertThrows(EmailAlreadyRegisteredException.class, () -> {
            useCase.execute("existente@gmail.com", "contraseñaSegura123");
        });

        Mockito.verify(repositoryMock, Mockito.never()).save(Mockito.any());
    }

    @Test
    @DisplayName("Debe lanzar InvalidEmailException y no guardar nada cuando el email es inválido")
    public void shouldThrowInvalidEmailExceptionWhenEmailIsInvalid() {
        // Act & Assert
        assertThrows(InvalidEmailException.class, () -> {
            useCase.execute("correoinvalido", "contraseñaSegura123");
        });

        Mockito.verify(repositoryMock, Mockito.never()).save(Mockito.any());
    }

    @Test
    @DisplayName("Debe lanzar WeakPasswordException y no guardar nada cuando la contraseña es inválida")
    public void shouldThrowWeakPasswordExceptionWhenPasswordIsInvalid() {
        // Act & Assert
        assertThrows(WeakPasswordException.class, () -> {
            useCase.execute("nuevo@gmail.com", "123");
        });

        Mockito.verify(repositoryMock, Mockito.never()).save(Mockito.any());
    }
}
