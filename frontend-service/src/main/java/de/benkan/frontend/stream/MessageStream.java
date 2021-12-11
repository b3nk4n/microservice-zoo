package de.benkan.frontend.stream;

import de.benkan.data.models.Message;
import de.benkan.shared.kafka.KafkaWriter;
import de.benkan.shared.kafka.Topics;
import io.dropwizard.lifecycle.Managed;

import javax.inject.Inject;

public class MessageStream implements Managed {

    private final MessageDownstream messageDownstream;
    private final KafkaWriter<Message> kafkaWriter;

    @Inject
    public MessageStream(MessageDownstream messageDownstream, KafkaWriter<Message> kafkaWriter) {
        this.messageDownstream = messageDownstream;
        this.kafkaWriter = kafkaWriter;
    }

    @Override
    public void start() {
        messageDownstream.getFlux()
                .subscribe(message ->
                        kafkaWriter.send(
                                Topics.INGRESS_MESSAGES,
                                message.channel(),
                                message));
    }

    @Override
    public void stop() {
        kafkaWriter.close();
    }
}
