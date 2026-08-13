package com.socialapp.domain.entity;

import com.socialapp.domain.valueobject.Email;
import com.socialapp.domain.valueobject.Password;

import java.util.Objects;

public class User {

    private final Email email;
    private final Password password;

    public User(Email email, Password password) {
        if (email == null) {
            throw new IllegalArgumentException("El email del usuario no puede ser nulo.");
        }
        if (password == null) {
            throw new IllegalArgumentException("La contraseña del usuario no puede ser nula.");
        }
        this.email = email;
        this.password = password;
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
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
