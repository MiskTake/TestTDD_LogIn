package com.socialapp.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import com.socialapp.domain.exception.InvalidEmailException;

@DisplayName("Validador de Correo (EmailValidator)")
public class EmailValidatorTest {

    private EmailValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new EmailValidator();
    }

    @Test
    @DisplayName("Debe lanzar InvalidEmailException cuando el correo no contiene '@'")
    public void shouldThrowInvalidEmailExceptionWhenEmailHasNoAtSymbol() {
        // Arrange
        String invalidEmail = "usuariogmail.com"; // sin '@'

        // Act & Assert
        assertThrows(InvalidEmailException.class, () -> {
            validator.validate(invalidEmail);
        });
    }

    @Test
    @DisplayName("Debe lanzar InvalidEmailException cuando el correo es null")
    public void shouldThrowInvalidEmailExceptionWhenEmailIsNull() {
        // Act & Assert
        assertThrows(InvalidEmailException.class, () -> {
            validator.validate(null);
        });
    }

    @Test
    @DisplayName("No debe lanzar ninguna excepción cuando el correo es válido")
    public void shouldNotThrowWhenEmailIsValid() {
        // Arrange
        String validEmail = "usuario@gmail.com";

        // Act & Assert
        assertDoesNotThrow(() -> {
            validator.validate(validEmail);
        });
    }

}