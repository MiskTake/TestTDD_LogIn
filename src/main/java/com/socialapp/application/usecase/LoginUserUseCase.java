package com.socialapp.application.usecase;

import com.socialapp.domain.entity.User;
import com.socialapp.domain.exception.InvalidCredentialsException;
import com.socialapp.domain.exception.UserNotFoundException;
import com.socialapp.domain.repository.UserRepository;
import com.socialapp.domain.valueobject.Email;
import com.socialapp.domain.valueobject.Password;

public class LoginUserUseCase {

    private final UserRepository userRepository;

    public LoginUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(String rawEmail, String rawPassword) {
        Email email = new Email(rawEmail);

        if (!userRepository.existsByEmail(email)) {
            throw new UserNotFoundException();
        }

        User user = userRepository.findByEmail(email);

        if (!user.authenticatesWith(new Password(rawPassword))) {
            throw new InvalidCredentialsException();
        }
    }
}
