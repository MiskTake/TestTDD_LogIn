package com.socialapp.domain.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import com.socialapp.domain.exception.InvalidEmailException;

@DisplayName("Value Object de Correo (Email)")
public class EmailTest {

    @Test
    @DisplayName("Debe lanzar InvalidEmailException cuando el correo no contiene '@'")
    public void shouldThrowInvalidEmailExceptionWhenEmailHasNoAtSymbol() {
        // Arrange
        String invalidEmail = "usuariogmail.com"; // sin '@'

        // Act & Assert
        assertThrows(InvalidEmailException.class, () -> {
            new Email(invalidEmail);
        });
    }

    @Test
    @DisplayName("Debe lanzar InvalidEmailException cuando el correo es null")
    public void shouldThrowInvalidEmailExceptionWhenEmailIsNull() {
        // Act & Assert
        assertThrows(InvalidEmailException.class, () -> {
            new Email(null);
        });
    }

    @Test
    @DisplayName("No debe lanzar ninguna excepción cuando el correo es válido")
    public void shouldNotThrowWhenEmailIsValid() {
        // Arrange
        String validEmail = "usuario@gmail.com";

        // Act & Assert
        assertDoesNotThrow(() -> {
            new Email(validEmail);
        });
    }

}
