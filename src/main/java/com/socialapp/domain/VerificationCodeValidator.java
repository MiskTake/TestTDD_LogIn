package com.socialapp.domain;

import com.socialapp.domain.exception.InvalidVerificationCodeException;

public class VerificationCodeValidator {

    public void validate(String submittedCode, String actualCode) {
        if (submittedCode == null || !submittedCode.equals(actualCode)) {
            throw new InvalidVerificationCodeException();
        }
    }
}
