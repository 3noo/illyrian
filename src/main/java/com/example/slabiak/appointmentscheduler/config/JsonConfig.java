package com.example.slabiak.appointmentscheduler.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class JsonConfig {

    @Bean
    public ObjectMapper jsonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalTime.class, new LocalTimeSerializer());
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer());
        objectMapper.registerModule(module);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static class LocalTimeSerializer extends com.fasterxml.jackson.databind.JsonSerializer<LocalTime> {
        @Override
        public void serialize(LocalTime localTime, com.fasterxml.jackson.core.JsonGenerator jsonGenerator, com.fasterxml.jackson.databind.SerializerProvider serializerProvider) throws java.io.IOException {
            jsonGenerator.writeString(localTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
        }
    }

    public static class LocalTimeDeserializer extends com.fasterxml.jackson.databind.JsonDeserializer<LocalTime> {
        @Override
        public LocalTime deserialize(com.fasterxml.jackson.core.JsonParser jsonParser, com.fasterxml.jackson.databind.DeserializationContext deserializationContext) throws java.io.IOException, com.fasterxml.jackson.core.JsonProcessingException {
            return LocalTime.parse(jsonParser.getText(), DateTimeFormatter.ISO_LOCAL_TIME);
        }
    }
}
