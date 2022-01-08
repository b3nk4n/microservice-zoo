package de.benkan.frontend.stream.serialization;

import de.benkan.data.models.Message;
import de.benkan.shared.kafka.serialization.JsonSerializer;

public class MessageSerializer extends JsonSerializer<Message> {
    public MessageSerializer() {
        super(Message.class);
    }
}
