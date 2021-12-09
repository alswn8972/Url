package com.alive.backend.common.mail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailService {
    private final JavaMailSender javaMailSender;

    public MailService(final JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public MimeMessage makeMail(MailWrapper mailWrapper) {
        MimeMessage mail = javaMailSender.createMimeMessage();

        try {
            mail.setFrom(mailWrapper.getSender());
            mail.setRecipients(Message.RecipientType.TO, mailWrapper.getReceiver());
            mail.setSubject(mailWrapper.getSubject());
            mail.setText(mailWrapper.getContent(), "UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return mail;
    }

    @Transactional
    public void sendMail(MimeMessage mail) {
        javaMailSender.send(mail);
    }
}
