package com.alive.backend.common.mail.service;

import com.alive.backend.common.mail.dtos.MailCheckRequest;
import com.alive.backend.common.mail.dtos.MailCodeWrapper;
import com.alive.backend.common.mail.dtos.MailDto;
import com.alive.backend.common.mail.dtos.MailWrapper;
import com.alive.backend.common.mail.repository.AuthCodeEntity;
import com.alive.backend.common.mail.repository.AuthCodeRepository;
import com.alive.backend.mailGroup.repository.MailGroupEntity;
import com.alive.backend.mailGroup.repository.MailGroupRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service("MailService")
public class AuthCodeService {
    private final JavaMailSender javaMailSender;
    private final AuthCodeRepository authCodeRepository;
    private final MailGroupRepository mailGroupRepository;
    public AuthCodeService(final JavaMailSender javaMailSender, final AuthCodeRepository authCodeRepository, MailGroupRepository mailGroupRepository) {
        this.javaMailSender = javaMailSender;
        this.authCodeRepository = authCodeRepository;
        this.mailGroupRepository = mailGroupRepository;
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


    public MimeMessage makeMail(MailWrapper mailWrapper, Long id) throws AddressException {
        MimeMessage mail = javaMailSender.createMimeMessage();
        List<MailGroupEntity> res = mailGroupRepository.findByUrlId(id);
        InternetAddress[] receiver = new InternetAddress[res.size()+1];
        receiver[0]=new InternetAddress(mailWrapper.getReceiver());
        for(int i = 1; i<res.size()+1;i++){
            receiver[i]=new InternetAddress(res.get(i-1).getEmailGroup());
        }
        try {
            mail.setFrom(mailWrapper.getSender());
            mail.setRecipients(Message.RecipientType.TO, receiver);
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
        AuthCodeEntity authCodeEntity = new AuthCodeEntity();
        authCodeEntity.setCode(mailDto.getCode());
        authCodeEntity.setUserEmail(mailDto.getUserEmail());
        if(authCodeRepository.findByUserEmail(mailDto.getUserEmail())!=null){
            authCodeRepository.deleteByUserEmail(authCodeEntity.getUserEmail());
        }
        authCodeRepository.save(authCodeEntity);
    }
    @Transactional
    public String checkCode(MailCheckRequest mailCheckRequest){
        String code = authCodeRepository.findByUserEmailAndCode(mailCheckRequest.getEmail(),mailCheckRequest.getCode()).get().getCode();
        return code;
    }
}
