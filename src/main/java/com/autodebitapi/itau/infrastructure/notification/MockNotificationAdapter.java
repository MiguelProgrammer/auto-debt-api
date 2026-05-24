package com.autodebitapi.itau.infrastructure.notification;

import com.autodebitapi.itau.core.port.out.NotificationPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MockNotificationAdapter implements NotificationPort {

    private static final Logger log = LoggerFactory.getLogger(MockNotificationAdapter.class);

    @Override
    public void sendEmail(String to, String subject, String body) {
        log.info("MOCK_EMAIL_SENT to={} subject={} body={}", to, subject, body);
    }

    @Override
    public void sendSms(String to, String message) {
        log.info("MOCK_SMS_SENT to={} message={}", to, message);
    }
}
