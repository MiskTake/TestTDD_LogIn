package com.socialapp.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import com.socialapp.domain.exception.WeakPasswordException;

@DisplayName("Validador de Contraseñas (PasswordValidator)")
public class PasswordValidatorTest {

    private PasswordValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new PasswordValidator();
    }

    @Test
    @DisplayName("Debe lanzar WeakPasswordException cuando la contraseña tiene menos de 8 caracteres")
    public void shouldThrowWeakPasswordExceptionWhenPasswordIsTooShort() {
        // Arrange
        String shortPassword = "abc123"; // 6 caracteres, menos de 8

        // Act & Assert
        assertThrows(WeakPasswordException.class, () -> {
            validator.validate(shortPassword);
        });
    }

    @Test
    @DisplayName("Debe lanzar WeakPasswordException cuando la contraseña es null")
    public void shouldThrowWeakPasswordExceptionWhenPasswordIsNull() {
        // Act & Assert
        assertThrows(WeakPasswordException.class, () -> {
            validator.validate(null);
        });
    }

    @Test
    @DisplayName("No debe lanzar ninguna excepción cuando la contraseña es válida (8+ caracteres)")
    public void shouldNotThrowWhenPasswordIsValid() {
        // Arrange
        String validPassword = "contraseñaSegura123";

        // Act & Assert
        assertDoesNotThrow(() -> {
            validator.validate(validPassword);
        });
    }

}