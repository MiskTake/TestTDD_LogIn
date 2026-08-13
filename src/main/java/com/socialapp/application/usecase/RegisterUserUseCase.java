package com.socialapp.application.usecase;

import com.socialapp.domain.entity.User;
import com.socialapp.domain.exception.EmailAlreadyRegisteredException;
import com.socialapp.domain.repository.UserRepository;
import com.socialapp.domain.valueobject.Email;
import com.socialapp.domain.valueobject.Password;

import java.util.UUID;

public class RegisterUserUseCase {

    private final UserRepository userRepository;

    public RegisterUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(String rawEmail, String rawPassword) {
        Email email = new Email(rawEmail);
        Password password = new Password(rawPassword);

        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyRegisteredException();
        }

        String id = UUID.randomUUID().toString();
        userRepository.save(new User(id, email, password));
    }
}
