package com.socialapp;

import com.socialapp.application.usecase.LoginUserUseCase;
import com.socialapp.application.usecase.RegisterUserUseCase;
import com.socialapp.application.usecase.SendVerificationCodeUseCase;
import com.socialapp.domain.repository.UserRepository;
import com.socialapp.infrastructure.notification.ConsoleEmailNotifier;
import com.socialapp.infrastructure.persistence.InMemoryUserRepository;

/**
 * Composition root: aquí, y solo aquí, se conectan las implementaciones
 * concretas de infrastructure con los casos de uso de application.
 */
public class Main {

    public static void main(String[] args) {
        UserRepository userRepository = new InMemoryUserRepository();

        RegisterUserUseCase registerUserUseCase = new RegisterUserUseCase(userRepository);
        LoginUserUseCase loginUserUseCase = new LoginUserUseCase(userRepository);
        SendVerificationCodeUseCase sendVerificationCodeUseCase =
                new SendVerificationCodeUseCase(new ConsoleEmailNotifier());

        registerUserUseCase.execute("usuario@gmail.com", "contraseñaSegura123");
        sendVerificationCodeUseCase.execute("usuario@gmail.com", "123456");
        loginUserUseCase.execute("usuario@gmail.com", "contraseñaSegura123");

        System.out.println("Usuario registrado y autenticado correctamente.");
    }
}
