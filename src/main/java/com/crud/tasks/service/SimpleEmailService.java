package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimpleEmailService {

//    private final JavaMailSender javaMailSender;

    public void send(final Mail mail){
        log.info("Starting email preparation...");
//        try {
//            var mailMessage = createMailMessage(mail);
//            javaMailSender.send(mailMessage);
//            log.info("Email has been sent.");
//        }catch (MailException e){
//            log.error("Failed to process email sending: " + e.getMessage(), e);
//        }
    }

    private SimpleMailMessage createMailMessage(final Mail mail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        if (Optional.ofNullable(mail.getToCc()).isPresent())
            mailMessage.setCc(mail.getToCc());
        return mailMessage;
    }

}
