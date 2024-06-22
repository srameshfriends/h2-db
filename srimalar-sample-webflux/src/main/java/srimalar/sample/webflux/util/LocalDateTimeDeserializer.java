package srimalar.sample.webflux.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.boot.jackson.JsonComponent;
import srimalar.sample.webflux.model.FormatConstant;

@JsonComponent
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    public LocalDateTimeDeserializer() {
    }

    public LocalDateTime deserialize(JsonParser jp, DeserializationContext context) throws IOException {
        String text = jp.getText();
        return text != null ? LocalDateTime.parse(text, FormatConstant.DATE_TIME_FORMATTER) : null;
    }
}
