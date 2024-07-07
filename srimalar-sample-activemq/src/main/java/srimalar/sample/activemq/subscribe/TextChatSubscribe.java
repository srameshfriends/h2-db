package srimalar.sample.activemq.subscribe;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import srimalar.sample.activemq.model.TextChat;

@Component
public class TextChatSubscribe {

    @JmsListener(destination = "TextChat", containerFactory = "myFactory")
    public void textChatReceived(TextChat textChat) {
        System.out.println("Received <" + textChat + ">");
    }
}
