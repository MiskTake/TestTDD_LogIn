package com.socialapp.domain;

public interface EmailNotifier {
    void sendEmail(String destination, String content);
}
