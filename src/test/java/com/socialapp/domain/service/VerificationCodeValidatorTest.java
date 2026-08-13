package com.socialapp.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import com.socialapp.domain.exception.InvalidVerificationCodeException;

@DisplayName("Validador de Código de Verificación (VerificationCodeValidator)")
public class VerificationCodeValidatorTest {

    private VerificationCodeValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new VerificationCodeValidator();
    }

    @Test
    @DisplayName("Debe lanzar InvalidVerificationCodeException cuando el código no coincide")
    public void shouldThrowInvalidVerificationCodeExceptionWhenCodesDoNotMatch() {
        // Act & Assert
        assertThrows(InvalidVerificationCodeException.class, () -> {
            validator.validate("111111", "123456");
        });
    }

    @Test
    @DisplayName("Debe lanzar InvalidVerificationCodeException cuando el código ingresado es null")
    public void shouldThrowInvalidVerificationCodeExceptionWhenSubmittedCodeIsNull() {
        // Act & Assert
        assertThrows(InvalidVerificationCodeException.class, () -> {
            validator.validate(null, "123456");
        });
    }

    @Test
    @DisplayName("No debe lanzar ninguna excepción cuando el código coincide")
    public void shouldNotThrowWhenCodesMatch() {
        // Act & Assert
        assertDoesNotThrow(() -> {
            validator.validate("123456", "123456");
        });
    }
}
