package com.socialapp.infrastructure.notification;

import com.socialapp.application.port.EmailNotifier;

public class ConsoleEmailNotifier implements EmailNotifier {

    @Override
    public void sendEmail(String destination, String content) {
        System.out.println("[EMAIL a " + destination + "] " + content);
    }
}
