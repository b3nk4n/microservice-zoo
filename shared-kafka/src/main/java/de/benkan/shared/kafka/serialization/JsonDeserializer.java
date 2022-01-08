package de.benkan.shared.kafka.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public abstract class JsonDeserializer<T> implements Deserializer<T> {
    private final ObjectReader objectReader;

    protected JsonDeserializer(Class<T> clazz) {
        objectReader = new ObjectMapper()
                .readerFor(clazz);
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("Data cannot be null or empty");
        }

        try {
            return objectReader.readValue(data);
        } catch (IOException e) {
            throw new SerializationException("Error deserializing binary data");
        }
    }
}
