package com.socialapp.domain;

import com.socialapp.domain.exception.EmailAlreadyRegisteredException;

public class RegistrationService {

    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;
    private final UserRepository userRepository;

    public RegistrationService(EmailValidator emailValidator, PasswordValidator passwordValidator, UserRepository userRepository) {
        this.emailValidator = emailValidator;
        this.passwordValidator = passwordValidator;
        this.userRepository = userRepository;
    }

    public void register(String email, String password) {
        emailValidator.validate(email);
        passwordValidator.validate(password);

        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyRegisteredException();
        }

        userRepository.save(new User(email, password));
    }
}
