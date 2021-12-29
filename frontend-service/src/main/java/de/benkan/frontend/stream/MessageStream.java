package de.benkan.frontend.stream;

import de.benkan.data.models.Message;
import de.benkan.shared.kafka.producer.KafkaWriter;
import io.dropwizard.lifecycle.Managed;
import reactor.core.Disposable;
import reactor.core.scheduler.Schedulers;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MessageStream implements Managed {

    private final MessageDownstream messageDownstream;
    private final KafkaWriter<Message> kafkaWriter;
    private Disposable disposable;

    @Inject
    public MessageStream(MessageDownstream messageDownstream, KafkaWriter<Message> kafkaWriter) {
        this.messageDownstream = messageDownstream;
        this.kafkaWriter = kafkaWriter;
    }

    @Override
    public void start() {
        disposable = messageDownstream.getFlux()
                .publish()
                .autoConnect()
                .subscribeOn(Schedulers.newSingle("messages-scheduler"))
                .subscribe(message -> kafkaWriter.write(message.channel(), message));
    }

    @Override
    public void stop() {
        kafkaWriter.close();
        disposable.dispose();
    }
}
