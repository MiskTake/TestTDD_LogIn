package com.socialapp.domain;

import com.socialapp.domain.exception.InvalidCredentialsException;
import com.socialapp.domain.exception.UserNotFoundException;

public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void login(String email, String password) {
        if (!userRepository.existsByEmail(email)) {
            throw new UserNotFoundException();
        }

        User user = userRepository.findByEmail(email);

        if (!user.password().equals(password)) {
            throw new InvalidCredentialsException();
        }
    }
}
