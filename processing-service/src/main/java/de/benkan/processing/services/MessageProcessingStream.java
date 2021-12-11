package de.benkan.processing.services;

import de.benkan.data.models.Message;
import de.benkan.shared.kafka.consumer.ReactiveKafkaReader;
import io.dropwizard.lifecycle.Managed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MessageProcessingStream implements Managed {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessingStream.class);

    private final ReactiveKafkaReader<Message> reactiveMessageReader;

    @Inject
    public MessageProcessingStream(ReactiveKafkaReader<Message> reactiveMessageReader) {
        this.reactiveMessageReader = reactiveMessageReader;
    }

    @Override
    public void start() {
        reactiveMessageReader.start();

        reactiveMessageReader.getFlux()
                .subscribe(message -> LOGGER.debug("Processed message: {}", message));
    }

    @Override
    public void stop() {
        reactiveMessageReader.close();
    }
}
