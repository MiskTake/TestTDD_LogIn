package com.socialapp.domain.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import com.socialapp.domain.valueobject.Email;
import com.socialapp.domain.valueobject.Password;

@DisplayName("Entidad de Usuario (User)")
public class UserTest {

    @Test
    @DisplayName("Debe autenticar cuando la contraseña candidata coincide con la almacenada")
    public void shouldAuthenticateWhenCandidatePasswordMatches() {
        // Arrange
        User user = new User("user-1", new Email("usuario@gmail.com"), new Password("contraseñaSegura123"));

        // Act & Assert
        assertTrue(user.authenticatesWith(new Password("contraseñaSegura123")));
    }

    @Test
    @DisplayName("No debe autenticar cuando la contraseña candidata no coincide")
    public void shouldNotAuthenticateWhenCandidatePasswordDoesNotMatch() {
        // Arrange
        User user = new User("user-1", new Email("usuario@gmail.com"), new Password("contraseñaSegura123"));

        // Act & Assert
        assertFalse(user.authenticatesWith(new Password("otraContraseña123")));
    }

    @Test
    @DisplayName("Dos usuarios con el mismo id deben ser iguales aunque sus atributos difieran (identidad de entidad)")
    public void shouldBeEqualWhenIdIsTheSameEvenIfAttributesDiffer() {
        // Arrange
        User first = new User("user-1", new Email("uno@gmail.com"), new Password("contraseñaSegura123"));
        User second = new User("user-1", new Email("dos@gmail.com"), new Password("otraContraseña123"));

        // Act & Assert
        assertEquals(first, second);
    }

    @Test
    @DisplayName("Dos usuarios con distinto id no deben ser iguales aunque compartan el mismo email")
    public void shouldNotBeEqualWhenIdDiffersEvenIfEmailMatches() {
        // Arrange
        User first = new User("user-1", new Email("usuario@gmail.com"), new Password("contraseñaSegura123"));
        User second = new User("user-2", new Email("usuario@gmail.com"), new Password("contraseñaSegura123"));

        // Act & Assert
        assertNotEquals(first, second);
    }
}
