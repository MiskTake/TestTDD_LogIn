package com.socialapp.domain.valueobject;

import com.socialapp.domain.exception.InvalidEmailException;

public record Email(String value) {

    public Email {
        if (value == null || !value.contains("@")) {
            throw new InvalidEmailException();
        }
    }
}
