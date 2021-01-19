package ru.greenatom.demo.service.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.greenatom.demo.domain.User;

@Service
public class NotificationMailSender implements MailSender {
    @Value("${spring.mail.username}")
    private String username;

    private final JavaMailSender mailSender;

    public NotificationMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(User from, User to, String subject, String message, MailType type) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(to.getEmail());
        mailMessage.setSubject(subject);

        // Constructing an E-mail message:
        switch (type) {
            case SIGN_UP: {
                mailMessage.setText(prepareRegistrationMessage(to));
                break;
            }
            case DOCUMENT_CREATED: {
                mailMessage.setText(prepareDocumentCreationMessage(to, message));
                break;
            }
            case DIGITAL_SIGNATURE_REQUEST: {
                mailMessage.setText(prepareSignatureRequestMessage(from, to, message));
                break;
            }
            case ACCESS_GRANTED:
            case MESSAGE:
            default: {
                mailMessage.setText(prepareNotificationMessage(from, to, message));
                break;
            }
        }

        mailSender.send(mailMessage);
    }

    private String prepareNotificationMessage(User from, User to, String message) {
        return String.format("Hello, %s!\n\n", to.getName()) +
                String.format("User %s %s have a notification for you:\n", from.getSurname(), from.getName()) +
                String.format("%s\n\nThis E-mail was generated automatically.", message);
    }

    private String prepareRegistrationMessage(User to) {
        return String.format("Hello, %s!\n\n", to.getName()) +
                "Thank you for your registration!" +
                "\n\nThis E-mail was generated automatically.";
    }

    private String prepareDocumentCreationMessage(User to, String message) {
        return String.format("Hello, %s!\n\n", to.getName()) + message +
                "\n\nThis E-mail was generated automatically.";
    }

    private String prepareSignatureRequestMessage(User from, User to, String message) {
        return String.format("Hello, %s!\n\n", to.getName()) +
                String.format("User %s %s requests your digital signature:\n", from.getSurname(), from.getName()) +
                String.format("%s\n\nThis E-mail was generated automatically.", message);
    }
}
