package com.autodebitapi.itau.core.port.out;

public interface NotificationPort {
    void sendEmail(String to, String subject, String body);
    void sendSms(String to, String message);
}
