package com.alive.backend.common.mail.service;

import com.alive.backend.common.mail.dtos.MailCheckRequest;
import com.alive.backend.common.mail.dtos.MailCodeWrapper;
import com.alive.backend.common.mail.dtos.MailDto;
import com.alive.backend.common.mail.dtos.MailWrapper;
import com.alive.backend.common.mail.repository.MailEntity;
import com.alive.backend.common.mail.repository.MailRepository;
import com.alive.backend.scheduler.dtos.ReserveMailResponse;
import com.alive.backend.scheduler.dtos.ReserveSendMailResponse;
import com.alive.backend.scheduler.repository.ReservationEntity;
import com.alive.backend.scheduler.repository.ReservationRepository;
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
public class MailService {
    private final JavaMailSender javaMailSender;
    private final MailRepository mailRepository;
    private final ReservationRepository reservationRepository;
    public MailService(final JavaMailSender javaMailSender, final MailRepository mailRepository, ReservationRepository reservationRepository) {
        this.javaMailSender = javaMailSender;
        this.mailRepository = mailRepository;
        this.reservationRepository = reservationRepository;
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
        List<ReservationEntity> res = reservationRepository.findByUrlId(id);
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
