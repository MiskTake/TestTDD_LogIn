package com.socialapp.domain.entity;

import com.socialapp.domain.valueobject.Email;
import com.socialapp.domain.valueobject.Password;

import java.util.Objects;

public class User {

    private final String id;
    private final Email email;
    private final Password password;

    public User(String id, Email email, Password password) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El ID del usuario no puede ser nulo.");
        }
        if (email == null) {
            throw new IllegalArgumentException("El email del usuario no puede ser nulo.");
        }
        if (password == null) {
            throw new IllegalArgumentException("La contraseña del usuario no puede ser nula.");
        }
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String id() {
        return id;
    }

    public Email email() {
        return email;
    }

    public boolean authenticatesWith(Password candidate) {
        return password.matches(candidate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
