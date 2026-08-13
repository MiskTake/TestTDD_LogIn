package com.socialapp.domain.valueobject;

import com.socialapp.domain.exception.WeakPasswordException;

public record Password(String value) {

    public Password {
        if (value == null || value.length() < 8) {
            throw new WeakPasswordException();
        }
    }

    public boolean matches(Password other) {
        return this.equals(other);
    }
}
