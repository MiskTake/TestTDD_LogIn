package com.socialapp.application.usecase;

import com.socialapp.application.port.EmailNotifier;

public class SendVerificationCodeUseCase {

    private final EmailNotifier notifier;

    public SendVerificationCodeUseCase(EmailNotifier notifier) {
        this.notifier = notifier;
    }

    public void execute(String email, String code) {
        notifier.sendEmail(email, "Tu código de verificación es: " + code);
    }
}
