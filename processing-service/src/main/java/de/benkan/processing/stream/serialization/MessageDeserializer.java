package de.benkan.processing.stream.serialization;

import de.benkan.data.models.Message;
import de.benkan.shared.kafka.serialization.JsonDeserializer;

public class MessageDeserializer extends JsonDeserializer<Message> {
    public MessageDeserializer() {
        super(Message.class);
    }
}
