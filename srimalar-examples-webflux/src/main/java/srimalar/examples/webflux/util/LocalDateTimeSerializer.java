package srimalar.examples.webflux.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.time.LocalDateTime;
import org.springframework.boot.jackson.JsonComponent;
import srimalar.examples.webflux.model.FormatConstant;

@JsonComponent
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    public LocalDateTimeSerializer() {
    }

    public void serialize(LocalDateTime value, JsonGenerator generator, SerializerProvider provider) {
        if (value != null) {
            try {
                generator.writeString(FormatConstant.DATE_TIME_FORMATTER.format(value));
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

    }
}
