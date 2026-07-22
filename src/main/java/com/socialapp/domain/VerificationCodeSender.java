package com.socialapp.domain;

public class VerificationCodeSender {

    private final EmailNotifier notifier;

    public VerificationCodeSender(EmailNotifier notifier) {
        this.notifier = notifier;
    }

    public void sendVerificationCode(String email, String code) {
        notifier.sendEmail(email, "Tu código de verificación es: " + code);
    }
}
