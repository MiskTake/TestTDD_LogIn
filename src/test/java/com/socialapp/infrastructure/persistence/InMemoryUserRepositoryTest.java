package com.socialapp.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import com.socialapp.domain.entity.User;
import com.socialapp.domain.exception.UserNotFoundException;
import com.socialapp.domain.valueobject.Email;
import com.socialapp.domain.valueobject.Password;

@DisplayName("Repositorio en Memoria (InMemoryUserRepository)")
public class InMemoryUserRepositoryTest {

    private InMemoryUserRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new InMemoryUserRepository();
    }

    @Test
    @DisplayName("No debe existir un usuario antes de guardarlo")
    public void shouldNotExistBeforeSaving() {
        assertFalse(repository.existsByEmail(new Email("usuario@gmail.com")));
    }

    @Test
    @DisplayName("Debe encontrar al usuario guardado por su email")
    public void shouldFindSavedUserByEmail() {
        // Arrange
        Email email = new Email("usuario@gmail.com");
        User user = new User(email, new Password("contraseñaSegura123"));

        // Act
        repository.save(user);

        // Assert
        assertTrue(repository.existsByEmail(email));
        assertEquals(user, repository.findByEmail(email));
    }

    @Test
    @DisplayName("Debe lanzar UserNotFoundException cuando el email no está guardado")
    public void shouldThrowUserNotFoundExceptionWhenEmailIsNotSaved() {
        assertThrows(UserNotFoundException.class, () -> {
            repository.findByEmail(new Email("noexiste@gmail.com"));
        });
    }
}
