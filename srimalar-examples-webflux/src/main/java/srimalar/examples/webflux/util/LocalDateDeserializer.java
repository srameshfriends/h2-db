package srimalar.examples.webflux.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import org.springframework.boot.jackson.JsonComponent;
import srimalar.examples.webflux.model.FormatConstant;

@JsonComponent
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {
    public LocalDateDeserializer() {
    }

    public LocalDate deserialize(JsonParser jp, DeserializationContext context) throws IOException {
        String text = jp.getText();
        if (text != null) {
            try {
                return LocalDate.parse(text, FormatConstant.DATE_FORMATTER);
            } catch (DateTimeParseException var5) {
                var5.printStackTrace();
            }
        }

        return null;
    }
}