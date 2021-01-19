package ru.greenatom.demo.service.mail;

import ru.greenatom.demo.domain.User;

public interface MailSender {
    void sendEmail(User from, User to, String subject, String message, MailType type);
}
