package de.benkan.shared.kafka.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class JsonSerializer<T> implements Serializer<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonSerializer() {
        // needed by Kafka
    }

    @Override
    public byte[] serialize(String topic, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing JSON data", e);
        }
    }

}
