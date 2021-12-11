package de.benkan.frontend.stream;

import de.benkan.data.models.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class MessageDownstream {
    private final Sinks.Many<Message> messagesSink;

    public MessageDownstream() {
        this.messagesSink = Sinks.many()
                .multicast()
                .directBestEffort();
    }

    public boolean emit(Message message) {
        return messagesSink.tryEmitNext(message)
                .isSuccess();
    }

    public Flux<Message> getFlux() {
        return messagesSink.asFlux();
    }
}
