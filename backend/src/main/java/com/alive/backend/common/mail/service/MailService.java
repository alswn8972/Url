package com.alive.backend.common.mail.service;

import com.alive.backend.common.mail.dtos.MailCheckRequest;
import com.alive.backend.common.mail.dtos.MailCodeWrapper;
import com.alive.backend.common.mail.dtos.MailDto;
import com.alive.backend.common.mail.dtos.MailWrapper;
import com.alive.backend.common.mail.repository.MailEntity;
import com.alive.backend.common.mail.repository.MailRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;

@Service("MailService")
public class MailService {
    private final JavaMailSender javaMailSender;
    private final MailRepository mailRepository;
    public MailService(final JavaMailSender javaMailSender, final MailRepository mailRepository) {
        this.javaMailSender = javaMailSender;
        this.mailRepository = mailRepository;
    }
    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
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
    public MimeMessage makeCodeMail(MailCodeWrapper mailWrapper) {
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
    @Transactional
    public void saveCode(MailDto mailDto) {
        MailEntity mailEntity = new MailEntity();
        mailEntity.setCode(mailDto.getCode());
        mailEntity.setUserEmail(mailDto.getUserEmail());
        if(mailRepository.findByUserEmail(mailDto.getUserEmail())!=null){
            mailRepository.deleteByUserEmail(mailEntity.getUserEmail());
        }
        mailRepository.save(mailEntity);
    }
    @Transactional
    public String checkCode(MailCheckRequest mailCheckRequest){
        String code = mailRepository.findByUserEmailAndCode(mailCheckRequest.getEmail(),mailCheckRequest.getCode()).get().getCode();
        return code;
    }
}
