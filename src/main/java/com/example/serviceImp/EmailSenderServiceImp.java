package com.example.serviceImp;

import com.example.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImp implements EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendSimpleEmail(String toEmail, String body, String subject) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("shashiandtechnology3@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
        System.out.println(" mail send");
    }
}
