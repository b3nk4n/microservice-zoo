package de.benkan.shared.kafka.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public abstract class JsonSerializer<T> implements Serializer<T> {
    private final ObjectWriter objectWriter;

    protected JsonSerializer(Class<T> clazz) {
       objectWriter = new ObjectMapper().writerFor(clazz);
    }

    @Override
    public byte[] serialize(String topic, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        try {
            return objectWriter.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing JSON data", e);
        }
    }
}
