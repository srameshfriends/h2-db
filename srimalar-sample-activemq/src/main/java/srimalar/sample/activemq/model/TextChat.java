package srimalar.sample.activemq.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.lang.NonNull;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.HashMap;
import java.util.Map;

@JsonRootName("text_chat")
public class TextChat implements Message<TextChat> {

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("details")
    private String details;

    @JsonProperty("created_by")
    private String createdBy;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    @NonNull
    public TextChat getPayload() {
        return TextChat.this;
    }

    @Override
    @NonNull
    public MessageHeaders getHeaders() {
        Map<String, Object> commonMap = new HashMap<>();
        commonMap.put("X-Auth", "admin");
        return new MessageHeaders(commonMap);
    }

    @Override
    public String toString() {
        return "{" +
                "subject: '" + subject + '\'' +
                ", details: '" + details + '\'' +
                ", createdBy: '" + createdBy + '\'' +
                '}';
    }
}
