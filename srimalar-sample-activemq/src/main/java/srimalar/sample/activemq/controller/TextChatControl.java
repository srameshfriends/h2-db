package srimalar.sample.activemq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import srimalar.sample.activemq.model.TextChat;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/chat")
public class TextChatControl {
    private final JmsTemplate jmsTemplate;

    @Autowired
    public TextChatControl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @GetMapping("/generate")
    private TextChat generate(@RequestParam(name = "name") String name) {
        TextChat textChat = new TextChat();
        textChat.setSubject("Welcome to onboard");
        textChat.setDetails("Hello, This is welcome message to on board our services : " + LocalDateTime.now());
        textChat.setCreatedBy(name);
        jmsTemplate.convertAndSend("TextChat", textChat);
        return textChat;
    }
}
