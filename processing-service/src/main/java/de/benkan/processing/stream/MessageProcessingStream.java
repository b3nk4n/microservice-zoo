package de.benkan.processing.stream;

import de.benkan.data.models.Message;
import de.benkan.shared.kafka.consumer.ReactiveKafkaReader;
import io.dropwizard.lifecycle.Managed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.Disposable;
import reactor.core.Disposables;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Locale;

@Singleton
public class MessageProcessingStream implements Managed {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProcessingStream.class);

    private final ReactiveKafkaReader<Message> reactiveMessageReader;
    private final Disposable.Composite disposable = Disposables.composite();

    @Inject
    public MessageProcessingStream(ReactiveKafkaReader<Message> reactiveMessageReader) {
        this.reactiveMessageReader = reactiveMessageReader;
    }

    @Override
    public void start() {
        reactiveMessageReader.start();

        disposable.add(
                reactiveMessageReader.getFlux()
                        .map(message -> message.toString().toUpperCase(Locale.ROOT))
                        .subscribe(message -> LOGGER.info("Processed message: {}", message)));
    }

    @Override
    public void stop() {
        reactiveMessageReader.close();
        disposable.dispose();
    }
}
