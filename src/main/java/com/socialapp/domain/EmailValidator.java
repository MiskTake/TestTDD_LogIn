package com.socialapp.domain;

import com.socialapp.domain.exception.InvalidEmailException;

public class EmailValidator {

    public void validate(String email) {
        if (email == null || !email.contains("@")) {
            throw new InvalidEmailException();
        }
    }
}
