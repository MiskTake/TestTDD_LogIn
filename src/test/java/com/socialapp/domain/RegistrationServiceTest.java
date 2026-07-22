package com.socialapp.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import com.socialapp.domain.exception.EmailAlreadyRegisteredException;
import com.socialapp.domain.exception.InvalidEmailException;
import com.socialapp.domain.exception.WeakPasswordException;

@DisplayName("Servicio de Registro (RegistrationService)")
public class RegistrationServiceTest {

    private UserRepository repositoryMock;
    private RegistrationService service;

    @BeforeEach
    public void setUp() {
        repositoryMock = Mockito.mock(UserRepository.class);
        service = new RegistrationService(new EmailValidator(), new PasswordValidator(), repositoryMock);
    }

    @Test
    @DisplayName("Debe guardar al usuario exactamente una vez cuando los datos son válidos y el email no está registrado")
    public void shouldSaveUserExactlyOnceWhenDataIsValidAndEmailIsNotRegistered() {
        // Arrange
        Mockito.when(repositoryMock.existsByEmail("nuevo@gmail.com")).thenReturn(false);

        // Act
        service.register("nuevo@gmail.com", "contraseñaSegura123");

        // Assert
        Mockito.verify(repositoryMock, Mockito.times(1)).save(new User("nuevo@gmail.com", "contraseñaSegura123"));
    }

    @Test
    @DisplayName("Debe lanzar EmailAlreadyRegisteredException cuando el email ya está registrado")
    public void shouldThrowEmailAlreadyRegisteredExceptionWhenEmailIsAlreadyRegistered() {
        // Arrange
        Mockito.when(repositoryMock.existsByEmail("existente@gmail.com")).thenReturn(true);

        // Act & Assert
        assertThrows(EmailAlreadyRegisteredException.class, () -> {
            service.register("existente@gmail.com", "contraseñaSegura123");
        });

        Mockito.verify(repositoryMock, Mockito.never()).save(Mockito.any());
    }

    @Test
    @DisplayName("Debe lanzar InvalidEmailException y no guardar nada cuando el email es inválido")
    public void shouldThrowInvalidEmailExceptionWhenEmailIsInvalid() {
        // Arrange: mock y servicio ya configurados en @BeforeEach

        // Act & Assert
        assertThrows(InvalidEmailException.class, () -> {
            service.register("correoinvalido", "contraseñaSegura123");
        });

        Mockito.verify(repositoryMock, Mockito.never()).save(Mockito.any());
    }

    @Test
    @DisplayName("Debe lanzar WeakPasswordException y no guardar nada cuando la contraseña es inválida")
    public void shouldThrowWeakPasswordExceptionWhenPasswordIsInvalid() {
        // Arrange: mock y servicio ya configurados en @BeforeEach

        // Act & Assert
        assertThrows(WeakPasswordException.class, () -> {
            service.register("nuevo@gmail.com", "123");
        });

        Mockito.verify(repositoryMock, Mockito.never()).save(Mockito.any());
    }
}
