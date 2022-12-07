package com.example;

import com.example.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class LibraryappApplication {
    @Autowired
    private EmailSenderService service;
    public static void main(String[] args) {
        SpringApplication.run(LibraryappApplication.class, args);
    }
    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail(){
        service.sendSimpleEmail("shashikumarkushwaha03@gmail.com","mail","mail");

    }

}
