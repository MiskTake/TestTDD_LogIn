package com.socialapp.domain;

import com.socialapp.domain.exception.WeakPasswordException;

public class PasswordValidator {

    public void validate(String password) {
        if (password == null || password.length() < 8) {
            throw new WeakPasswordException();
        }
    }
}
