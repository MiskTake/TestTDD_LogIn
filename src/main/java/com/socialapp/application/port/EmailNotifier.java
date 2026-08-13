package com.socialapp.application.port;

public interface EmailNotifier {
    void sendEmail(String destination, String content);
}
