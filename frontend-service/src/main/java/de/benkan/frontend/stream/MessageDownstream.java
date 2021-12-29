package de.benkan.frontend.stream;

import de.benkan.data.models.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import javax.inject.Singleton;

@Singleton
public class MessageDownstream {
    private final Sinks.Many<Message> messagesSink;

    public MessageDownstream() {
        messagesSink = Sinks.many()
                .unicast()
                .onBackpressureBuffer();
    }

    public boolean emit(Message message) {
        return messagesSink.tryEmitNext(message)
                .isSuccess();
    }

    public Flux<Message> getFlux() {
        return messagesSink.asFlux();
    }
}
