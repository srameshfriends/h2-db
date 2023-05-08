package srimalar.examples.webflux;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.time.LocalDate;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class LocalDateSerializer extends JsonSerializer<LocalDate> {
    public LocalDateSerializer() {
    }

    public void serialize(LocalDate value, JsonGenerator generator, SerializerProvider provider) {
        if (value != null) {
            try {
                generator.writeString(FormatConstant.DATE_FORMATTER.format(value));
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

    }
}
